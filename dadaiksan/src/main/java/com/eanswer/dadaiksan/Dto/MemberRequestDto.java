package com.eanswer.dadaiksan.Dto;

import com.eanswer.dadaiksan.Entity.Member;
import com.eanswer.dadaiksan.constant.Authority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberRequestDto {
    private String email;
    private String password;
    private String nickname;

    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .nickName(nickname)
                .authority(Authority.ROLE_USER)
                .build();
    }

    public Member toAdmin(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .nickName(nickname)
                .authority(Authority.ROLE_ADMIN)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}