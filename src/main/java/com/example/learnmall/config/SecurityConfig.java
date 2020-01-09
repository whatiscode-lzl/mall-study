package com.example.learnmall.config;

import com.example.learnmall.component.JwtAuthenticationTokenFilter;
import com.example.learnmall.component.RestAuthenticationEntryPoint;
import com.example.learnmall.component.RestfulAccessDeniedHandler;
import com.example.learnmall.dto.AdminUserDetails;
import com.example.learnmall.mbg.model.UmsAdmin;
import com.example.learnmall.mbg.model.UmsPermission;
import com.example.learnmall.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

/**
 * @Author liaozhenglong
 * @Date 2019/11/20 16:30
 * SpringSecurity的配置类
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UmsAdminService umsAdminService;
    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()//由于使用JWT不需要csrf
                .disable()
                .sessionManagement()//基于token不需要session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,//允许对于静态网站资源的无授权访问
                        "/",
                        "/*.html",
                        "/favition.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/swagger-resources/**",
                        "/v2/api-docs/**")
                .permitAll()
                .antMatchers("/admin/login","/admin/register")//对登入注册要允许匿名访问
                .permitAll()
                .antMatchers(HttpMethod.OPTIONS)//跨域请求会进行一次OPTIONS的请求
                .permitAll()
                //.antMatchers()//测试时全部允许访问
                //.permitAll()
                .anyRequest()//除了上面的所有请求外全要进行鉴权访问
                .authenticated();
        //禁用缓存
        http.headers().cacheControl();
        //添加jwt Filter
        http.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        //添加自定义未授权和未登入返回的结果
        http.exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
       //获取登入用户信息
        return username -> {
            UmsAdmin umsAdmin = umsAdminService.getAdminByUserName(username);
            if (umsAdmin!=null){
                List<UmsPermission> permissions = umsAdminService.getPermissionList(umsAdmin.getId());
                return new AdminUserDetails(umsAdmin,permissions);
            }
            throw new UsernameNotFoundException("用户名或者密码错误");
        };
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
