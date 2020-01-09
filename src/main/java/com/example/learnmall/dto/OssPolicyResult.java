package com.example.learnmall.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Author liaozhenglong
 * @Date 2019/11/22 16:03
 *  获取OSS上传文件授权的返回结果,前端直接上传文件时所需的参数
 **/
public class OssPolicyResult {
    @ApiModelProperty("访问身份验证中用到的身份标识")
    private String accessKeyId;
    @ApiModelProperty("用户表单上传策略，经过base64编码过的字符串策略")
    private String policy;
    @ApiModelProperty("policy签名后的字符串")
    private String signature;
    @ApiModelProperty("上传文件夹路径前缀")
    private String dir;
    @ApiModelProperty("OSS对外服务的访问域名")
    private String host;
    @ApiModelProperty("上传成功后的回调设置")
    private String callback;

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }
}
