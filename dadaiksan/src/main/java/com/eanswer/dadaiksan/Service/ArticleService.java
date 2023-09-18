package com.eanswer.dadaiksan.Service;

import com.eanswer.dadaiksan.Dto.ArticleDto;
import com.eanswer.dadaiksan.Dto.EventDto;
import com.eanswer.dadaiksan.Entity.Article;
import com.eanswer.dadaiksan.Entity.Event;
import com.eanswer.dadaiksan.Entity.Member;
import com.eanswer.dadaiksan.Repository.ArticleRepository;
import com.eanswer.dadaiksan.constant.Authority;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.eanswer.dadaiksan.constant.Authority.ROLE_ADMIN;

@Service
@RequiredArgsConstructor
public class ArticleService {
  @Autowired
  private ArticleRepository articleRepository;
  private ArticleDto articleDto;
  @Autowired
  private AuthService authService;


  public List<ArticleDto> getAllArticle() {
    List<ArticleDto> articleDtos = new ArrayList<>();
    List<Article> articles = articleRepository.findAll();

    for (Article article : articles) {
      ArticleDto articleDto = new ArticleDto();

      if(article.getUpdateDate() == null){
        articleDto.setUpdateDate(null);
      }else{
        articleDto.setUpdateDate(article.getUpdateDate());
      }

      articleDto.setId(article.getId());
      articleDto.setViewCount(article.getViewCount());
      articleDto.setLikeCount(article.getLikeCount());
      articleDto.setTitle(article.getTitle());
      articleDto.setArticleType(article.getArticleType());
      articleDto.setContents(article.getContents());
      articleDto.setImgUrl(article.getImgUrl());
      articleDto.setVidUrl(article.getVidUrl());
      articleDto.setStatus(article.isStatus());
      articleDto.setAdmin(article.isAdmin());
      articleDto.setRegDate(article.getRegDate());
      articleDtos.add(articleDto);
    }
    return articleDtos;
  }

  public boolean newArticle(ArticleDto articleDto, HttpServletRequest request, UserDetails userDetails) throws ParseException {

    Member member = authService.validateTokenAndGetUser(request,userDetails);
    Authority isAdmin = member.getAuthority();

    if(!isAdmin.name().equals("ROLE_ADMIN")){
      return false;
    }

    Article article = new Article();

    article.setTitle(articleDto.getTitle());
    article.setArticleType(articleDto.getArticleType());
    article.setContents(articleDto.getContents());
    article.setImgUrl(articleDto.getImgUrl());
    article.setVidUrl(articleDto.getVidUrl());
    article.setRegDate(LocalDateTime.now());
    Article saveArticle = articleRepository.save(article);
    System.out.println(saveArticle);
    return true;
  }

  public ArticleDto readArticle(Long id){
    Article article = articleRepository.findById(id).orElseThrow(() -> new RuntimeException("찾는 게시물이 없습니다."));

    ArticleDto articleDto1 = new ArticleDto();

    articleDto1.setId(article.getId());
    articleDto1.setViewCount(article.getViewCount());
    articleDto1.setLikeCount(article.getLikeCount());
    articleDto1.setTitle(article.getTitle());
    articleDto1.setArticleType(article.getArticleType());
    articleDto1.setContents(article.getContents());
    articleDto1.setImgUrl(article.getImgUrl());
    articleDto1.setVidUrl(article.getVidUrl());
    articleDto1.setStatus(article.isStatus());
    articleDto1.setAdmin(article.isAdmin());
    articleDto1.setRegDate(article.getRegDate());
    articleDto1.setUpdateDate(article.getUpdateDate());
    return  articleDto1;
  }

  @Transactional
  public boolean updateArticle(Long id, ArticleDto articleDto, HttpServletRequest request, UserDetails userDetails) throws ParseException{
    Member member = authService.validateTokenAndGetUser(request,userDetails);
    Authority isAdmin = member.getAuthority();

    if (!isAdmin.name().equals("ROLE_ADMIN")) {
      return false;
    }
    Article article = articleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다."));

    article.setTitle(articleDto.getTitle());
    article.setArticleType(articleDto.getArticleType());
    article.setContents(articleDto.getContents());
    article.setImgUrl(articleDto.getImgUrl());
    article.setVidUrl(articleDto.getVidUrl());
    article.setUpdateDate(LocalDateTime.now());
    Article saveArticle = articleRepository.save(article);
    return saveArticle != null;
  }

  @Transactional
  public boolean deleteArticle(Long id, HttpServletRequest request, UserDetails userDetails){

    Member member = authService.validateTokenAndGetUser(request,userDetails);
    Authority isAdmin = member.getAuthority();

    if (!isAdmin.name().equals("ROLE_ADMIN")) {
      return false;
    }

    Article article = articleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다."));
    articleRepository.delete(article);
    return true;
  }
}
