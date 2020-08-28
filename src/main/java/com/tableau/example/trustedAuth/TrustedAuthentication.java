package com.tableau.example.trustedAuth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import javax.servlet.ServletException;

/**
 * @ClassName TrustedAuthentication
 * @description tableau单点登录验证：可信认证
 * @author zhaoxudong
 * @date 2020/8/19 11:02
 */
@Component
public class TrustedAuthentication {
    // tableau server的地址
    @Value("${tableau.ip:127.0.0.1}")
    private String tableauIp;

    // 站点ID
    @Value("${tableau.site_id}")
    private String siteId;

    /**
     * getTicket : 从Tabeau Server获取票证(ticket)
     *
     * @author zhaoxudong
     * @date 2020/8/19 13:35
     * @param username 用户名
     */
    public String getTicket(String username) throws ServletException {
        String url = "http://" + tableauIp + "/trusted";

        System.out.println("----- 单点登录: 获取ticket");
        System.out.printf("- 参数: url: %s ,站点id: %s ,用户名: %s %n", url, siteId, username);

        // 请求参数
        MultiValueMap<String, String> requestBody= new LinkedMultiValueMap<>();
        requestBody.add("username", username);
        if(null !=  siteId && !"".equals(siteId)){
            requestBody.add("target_site", siteId);
        }

        RestTemplate restTemplate =  new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        System.out.println("- 请求成功: " + response.getBody());
        System.out.println("-------------------------");
        return response.getBody();
    }
}
