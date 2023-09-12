package com.eanswer.dadaiksan.Controller;

import com.eanswer.dadaiksan.Dto.MemberRequestDto;
import com.eanswer.dadaiksan.Dto.MemberResponseDto;
import com.eanswer.dadaiksan.Dto.TokenDto;
import com.eanswer.dadaiksan.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
//    private final EmailService emailService;

    /**
     * 회원가입
     */

    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(authService.signup(memberRequestDto));
    }

    /**
     * 로그인
     */
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(authService.login(memberRequestDto));
    }

    @PostMapping("/refresh-token")
    public TokenDto refreshToken(@RequestParam String refreshToken) {
        TokenDto newAccessToken = authService.refreshToken(refreshToken);
        return newAccessToken;
    }

//    /**
//     * 이메일이 데이터베이스에 존재하는지 확인
//     */
//    @GetMapping("/{email}")
//    public ResponseEntity<Boolean> findMemberByEmail(@PathVariable String email) throws Exception {
//        boolean isValid = authService.isValidEmail(email);
//        return ResponseEntity.ok(isValid);
//    }


}