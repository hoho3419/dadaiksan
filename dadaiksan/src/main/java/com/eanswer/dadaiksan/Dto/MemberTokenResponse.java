package com.eanswer.dadaiksan.Dto;

import com.eanswer.dadaiksan.Entity.Member;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
@Getter
@Setter

public class MemberTokenResponse {
    private Member member;
    private TokenDto tokenDto;

    public MemberTokenResponse(Member member, TokenDto tokenDto) {
        this.member = member;
        this.tokenDto = tokenDto;
    }

    // getter와 setter 메서드
}