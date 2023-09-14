package com.eanswer.dadaiksan.Service;

import com.eanswer.dadaiksan.Dto.MemberRequestDto;
import com.eanswer.dadaiksan.Dto.MemberResponseDto;
import com.eanswer.dadaiksan.Dto.TokenDto;
import com.eanswer.dadaiksan.Entity.Member;
import com.eanswer.dadaiksan.Repository.MemberRepository;
import com.eanswer.dadaiksan.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenProvider tokenProvider;


    @Transactional
    public MemberResponseDto signup(MemberRequestDto memberRequestDto) {
        if (memberRepository.existsByEmail(memberRequestDto.getEmail())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다");
        }

        if (memberRequestDto.getNickname().equals("admin")) {
            Member member = memberRequestDto.toAdmin(passwordEncoder);
            return MemberResponseDto.of(memberRepository.save(member));
        } else {
            Member member = memberRequestDto.toMember(passwordEncoder);
            return MemberResponseDto.of(memberRepository.save(member));
        }
    }

    @Transactional
    public TokenDto login(MemberRequestDto memberRequestDto) {
        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = memberRequestDto.toAuthentication();

        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        return tokenDto;
    }

    @Transactional
    public TokenDto refreshToken(String refreshToken) {
        // 1. Refresh 토큰의 유효성 검사
        if (!tokenProvider.validateToken(refreshToken)) {
            throw new RuntimeException("유효하지 않은 Refresh 토큰입니다.");
        }

        // 2. Refresh 토큰을 파싱하여 사용자 정보를 추출
        Authentication authentication = tokenProvider.getAuthentication(refreshToken);

        // 3. 새로운 Access 토큰 생성
        TokenDto newAccessToken = tokenProvider.generateTokenDto(authentication);

        return newAccessToken;
    }

    public Member validateTokenAndGetUser(HttpServletRequest request, UserDetails userDetails) {
        String accessToken = request.getHeader("Authorization");
        if (accessToken != null && accessToken.startsWith("Bearer ")) {
            accessToken = accessToken.substring(7);
        }
        if (accessToken != null && tokenProvider.validateToken(accessToken)) {
            Long Id = Long.valueOf(userDetails.getUsername());
            Member member = memberRepository.findById(Id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다"));
            return member;
        } else {
            throw new IllegalArgumentException("토큰이 만료됐습니다. Refresh Token을 보내주세요.");
        }

    }

}