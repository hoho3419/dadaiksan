package com.eanswer.dadaiksan.Service;

import com.eanswer.dadaiksan.Dto.ArticleDto;
import com.eanswer.dadaiksan.Entity.Article;
import com.eanswer.dadaiksan.Entity.Member;
import com.eanswer.dadaiksan.Repository.ArticleRepository;
import com.eanswer.dadaiksan.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ArticleService {
  @Autowired
  private ArticleRepository articleRepository;
  @Autowired
  private MemberRepository memberRepository;
  @Autowired
  private AuthService authService;


  public List<ArticleDto> getAllArticle() {
    List<ArticleDto> articleDtos = new ArrayList<>();
    List<Object[]> articles = articleRepository.findByAllArticleAndLikes();

    for (Object[] arr : articles) {
      ArticleDto articleDto = new ArticleDto();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");

      Long id = arr[0] != null ? Long.valueOf(arr[0].toString()) : null;
      String Type = arr[1] != null ? (String)arr[1] : null;
      String contents = arr[2] != null ? (String)arr[2] : null;
      String imgUrl = arr[3] != null ? (String)arr[3] : null;
      Optional<String> regString = (arr[4] != null) ? Optional.of(arr[4].toString()) : Optional.empty();
      Optional<LocalDateTime> regDate = regString.map(str -> str != null ? LocalDateTime.parse(str, formatter) : null);
      boolean status = arr[5] != null ? (boolean)arr[5] : true;
      String title = arr[6] != null ? (String)arr[6] : null;
      Optional<String> updateString = (arr[7] != null) ? Optional.of(arr[7].toString()) : Optional.empty();
      Optional<LocalDateTime> updateDate = updateString.map(str -> str != null ? LocalDateTime.parse(str, formatter) : null);
      String vidUrl = arr[8] != null ? (String)arr[8] : null;
      int viewCount = arr[9] != null ? (int)arr[9] : null;
      BigInteger likeCount = arr[10] != null ? (BigInteger)arr[10] : null;

      articleDto.setId(id);
      articleDto.setArticleType(Type);
      articleDto.setContents(contents);
      articleDto.setImgUrl(imgUrl);
      articleDto.setRegDate(regDate.orElse(null));
      articleDto.setStatus(status);
      articleDto.setTitle(title);
      articleDto.setUpdateDate(updateDate.orElse(null));
      articleDto.setVidUrl(vidUrl);
      articleDto.setViewCount(viewCount);
      articleDto.setLikeCounts(likeCount);

      articleDtos.add(articleDto);
    }
    return articleDtos;
  }

  public boolean newArticle(ArticleDto articleDto, HttpServletRequest request, UserDetails userDetails) throws ParseException {

    Member member = authService.validateTokenAndGetUser(request,userDetails);

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

  public ArticleDto readArticle(Long id,HttpServletRequest request,UserDetails userDetails){
    ArticleDto articleDto1 = new ArticleDto();

    Member member = authService.validateTokenAndGetUser(request,userDetails);
    if(member != null){
      String memberNickName = articleRepository.findByArticleAndLikes(id,member.getId());
      articleDto1.setNickName(memberNickName);
    }

    Article article = articleRepository.findById(id).orElseThrow(() -> new RuntimeException("찾는 게시물이 없습니다."));
    articleDto1.setId(article.getId());
    articleDto1.setViewCount(article.getViewCount());
    articleDto1.setTitle(article.getTitle());
    articleDto1.setArticleType(article.getArticleType());
    articleDto1.setContents(article.getContents());
    articleDto1.setImgUrl(article.getImgUrl());
    articleDto1.setVidUrl(article.getVidUrl());
    articleDto1.setStatus(article.isStatus());
    articleDto1.setRegDate(article.getRegDate());
    articleDto1.setUpdateDate(article.getUpdateDate());
    return articleDto1;
  }

  @Transactional
  public boolean updateArticle(Long id, ArticleDto articleDto, HttpServletRequest request, UserDetails userDetails) throws ParseException{
    Member member = authService.validateTokenAndGetUser(request,userDetails);

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

    Article article = articleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다."));
    articleRepository.delete(article);
    return true;
  }

  @Transactional
  public boolean viewCountInc(Long id, HttpServletRequest request, HttpServletResponse response){
    Cookie oldCookie = null;
    Cookie[] cookies = request.getCookies();
    int result = 0;

    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals("postView")) {
          oldCookie = cookie;
        }
      }
    }

    if (oldCookie != null) {
      if (!oldCookie.getValue().contains("[" + id.toString() + "]")) {
        result = articleRepository.viewCountUp(id);
        oldCookie.setValue(oldCookie.getValue() + "_[" + id + "]");
        oldCookie.setPath("/");
        oldCookie.setMaxAge(60 * 60 * 24);
        response.addCookie(oldCookie);
      }
    } else {
      result = articleRepository.viewCountUp(id);
      Cookie newCookie = new Cookie("postView","[" + id + "]");
      newCookie.setPath("/");
      newCookie.setMaxAge(60 * 60 * 24);
      response.addCookie(newCookie);
    }

    return result > 0;
  }
}
