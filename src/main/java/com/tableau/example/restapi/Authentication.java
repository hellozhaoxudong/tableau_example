package com.tableau.example.restapi;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tableau.example.dto.CredentialsToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName Authentication
 * @description RestApi-用户认证
 * @author zhaoxudong
 * @date 2020/8/21 10:51
 */
@Component
public class Authentication {

    private final static Logger logger = LoggerFactory.getLogger(Authentication.class);

    // tableau server的地址
    @Value("${tableau.ip:127.0.0.1}")
    private String tableauIp;

    // 站点
    @Value("${tableau.site_id}")
    private String siteId;

    // api版本
    @Value("${tableau.api_version}")
    private String apiVersion;

    @Autowired
    private DoRequest doRequest;

    /**
     * getCredentialsTocken: 获取凭证令牌(用户名密码模式)
     *
     * @author zhaoxudong
     * @date 2020/8/21 11:07
     * @param username 用户名
     * @param password 密码
     */
    public CredentialsToken getCredentialsToken(String username, String password){
        logger.info("-- 获取凭证令牌(用户名密码模式)");

        String url = "http://" + tableauIp + "/api/" +apiVersion +"/auth/signin";

        // 构造登录请求字符串
        String requestData = "{\"credentials\": { \"name\": \"${username}\", \"password\": \"${password}\", \"site\": { \"contentUrl\": \"${siteId}\"}}}";
        requestData = requestData.replace("${username}", username).replace("${password}", password).replace("${siteId}", siteId);
        logger.info("-       请求地址: {}   请求内容: {}", url, requestData);

        // 发送请求
        String response =  doRequest.doPost(url, requestData);

        logger.info("-       返回结果: {}", response);
        logger.info("-- 结束");
        return resolve(response);
    }

    /**
     * getCredentialsTocken: 获取凭证令牌(个人令牌模式), Tableau Server 2019.4版本及以后有效
     *
     * @author zhaoxudong
     * @date 2020/8/21 11:10
     * @param personalAccessTocken 个人访问令牌
     */
    public String getCredentialsToken(String personalAccessTocken){
        System.out.println("-- 获取凭证令牌(个人令牌模式)");

        return null;
    }

    /**
     * resolve : 解析凭证令牌信息
     *
     * @author zhaoxudong
     * @date 2020/8/28 15:57
     */
    private CredentialsToken resolve(String rep){
        JsonObject json = new JsonParser().parse(rep).getAsJsonObject();

        CredentialsToken token = new CredentialsToken();
        token.setSiteId(json.getAsJsonObject("credentials").getAsJsonObject("site").getAsJsonObject("id").getAsString());
        token.setSiteUrl(json.getAsJsonObject("credentials").getAsJsonObject("site").getAsJsonObject("contentUrl").getAsString());
        token.setUserId(json.getAsJsonObject("credentials").getAsJsonObject("user").getAsJsonObject("id").getAsString());
        token.setUserId(json.getAsJsonObject("credentials").getAsJsonObject("token").getAsString());

        return token;
    }
}