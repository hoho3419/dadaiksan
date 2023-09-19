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
    System.out.println(articles);
    return new ResponseEntity<>(articles, HttpStatus.OK);
  }

  @GetMapping("/read/{id}") // 조회
  public ResponseEntity<ArticleDto> readArticle(@PathVariable Long id){
    ArticleDto articleDto = articleService.readArticle(id);
    return new ResponseEntity<>(articleDto,HttpStatus.OK);
  }

}