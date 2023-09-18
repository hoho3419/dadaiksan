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

  @PostMapping("/new") // 등록
  public ResponseEntity<?> newArticle(@RequestBody ArticleDto articleDto, HttpServletRequest request,
                                      @AuthenticationPrincipal UserDetails userDetails) throws ParseException{
    boolean isCreate = articleService.newArticle(articleDto,request,userDetails);
    if(!isCreate){
      return new ResponseEntity<>("게시물 저장 실패",HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>("새로운 게시판 등록 성공",HttpStatus.OK);
  }

  @GetMapping("/read/{id}") // 조회
  public ResponseEntity<ArticleDto> readArticle(@PathVariable Long id){
    ArticleDto articleDto = articleService.readArticle(id);
    return new ResponseEntity<>(articleDto,HttpStatus.OK);
  }

  @PostMapping("/update/{id}")
  public ResponseEntity<?> updateArticle(@PathVariable("id") Long id, @RequestBody ArticleDto articleDto, HttpServletRequest request, @AuthenticationPrincipal UserDetails userDetails) throws ParseException{
    try {
      boolean isUpdated = articleService.updateArticle(id, articleDto, request, userDetails);
      return new ResponseEntity<>(isUpdated,HttpStatus.OK);
    }catch (IllegalArgumentException e){
      return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
    }catch (IllegalStateException e){
      return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
    }
  }

  @PostMapping("/delete/{id}")
  public ResponseEntity<?> deleteArticle(@PathVariable("id") Long id, HttpServletRequest request, @AuthenticationPrincipal UserDetails userDetails) throws ParseException{
    boolean isDeleted = articleService.deleteArticle(id, request, userDetails);
    if(!isDeleted){
      return new ResponseEntity<>("해당 게시물이 없습니다.",HttpStatus.NO_CONTENT);
    }
    return  new ResponseEntity<>("해당 게시물이 삭제 되었습니다.",HttpStatus.OK);
  }
}