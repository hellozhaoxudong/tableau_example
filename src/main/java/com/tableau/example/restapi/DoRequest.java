package com.tableau.example.restapi;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DoRequest {


    public String doPost(String url, String requestData){

        // 构造请求
        RestTemplate restTemplate =  new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> request = new HttpEntity<>(requestData, headers);

        // 发送请求
        String response = restTemplate.postForEntity(url, request, String.class).getBody();

        return response;
    }
}
