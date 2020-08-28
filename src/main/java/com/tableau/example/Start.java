package com.tableau.example;

import com.tableau.example.restapi.Authentication;
import com.tableau.example.trustedAuth.TrustedAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @ClassName Start
 * @description 开始测试tableau集成接口
 * @author zhaoxudong
 * @date 2020/8/19 13:54
 * @version 1.0
 * @since JDK 1.8
 */
@Component
public class Start implements ApplicationRunner{

    @Autowired
    private TrustedAuthentication auth;

    // 用户认证
    @Autowired
    private Authentication authentication;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        //auth.getTicket("tableau");

        authentication.getCredentialsToken("tableau", "tableau");
    }
}
