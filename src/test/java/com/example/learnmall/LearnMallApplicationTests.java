package com.example.learnmall;

import com.example.learnmall.mbg.model.UmsAdmin;
import com.example.learnmall.service.UmsAdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LearnMallApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    UmsAdminService adminService;

    @Test
    public void testLogin(){
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setPassword("123456");
        umsAdmin.setUsername("testAdming");
        String token=adminService.login(umsAdmin.getUsername(),umsAdmin.getPassword());
        System.out.println("token=="+token);
    }

}
