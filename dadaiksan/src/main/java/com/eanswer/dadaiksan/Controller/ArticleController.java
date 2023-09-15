package com.eanswer.dadaiksan.Controller;

import com.eanswer.dadaiksan.Dto.ArticleDto;
import com.eanswer.dadaiksan.Service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

  @Autowired
  private ArticleService articleService;

  @GetMapping("/read-all") // 게시판 목록 조회
  public ResponseEntity<List<ArticleDto>> getAllArticle() {
    List<ArticleDto> articles = articleService.getAllArticle();
    return new ResponseEntity<>(articles, HttpStatus.OK);
  }

  @PostMapping("/new")
  public ResponseEntity<?> newArticle(@RequestBody ArticleDto articleDto, HttpServletRequest request,
                                      @AuthenticationPrincipal UserDetails userDetails) throws ParseException{

    return new ResponseEntity<>("새로운 게시판 등록 성공",HttpStatus.OK);
  }
}
