package com.tableau.example.dto;

/**
 * @ClassName CredentialsToken
 * @description 凭证令牌信息
 * @author zhaoxudong
 * @date 2020/8/28 15:52
 * @version 1.0
 * @since JDK 1.8
 */
public class CredentialsToken {

    // 站点ID: credentials.site.id
    private String siteId;

    // 站点URL: credentials.site.contentUrl
    private String siteUrl;

    // 用户ID: credentials.user.id
    private String userId;

    // 凭证: credentials.token
    private String token;


    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
