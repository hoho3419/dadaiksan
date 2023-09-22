package com.eanswer.dadaiksan.Controller;

import com.eanswer.dadaiksan.Entity.Member;
import com.eanswer.dadaiksan.Service.LoginService;
import com.eanswer.dadaiksan.kakao.KakaoToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/kakao")
public class KakaoController {

    @Autowired
    private LoginService loginService;
    @RequestMapping("/login")
    public String kakaoLogin(@RequestParam(value = "code",required = false) String code){
        if(code!=null){//카카오측에서 보내준 code가 있다면 출력합니다
            System.out.println("code = " + code);

            //추가됨: 카카오 토큰 요청
            KakaoToken kakaoToken = loginService.requestToken(code);
            log.info("kakoToken = {}", kakaoToken);

//            추가됨: 유저정보 요청
            Member member = loginService.requestUser(kakaoToken.getAccess_token());
            log.info("member = {}",member);
        }
        return "redirect:http://localhost:3000/loginfinish";
    }
}
