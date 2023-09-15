package com.eanswer.dadaiksan.Service;

import com.eanswer.dadaiksan.Dto.ArticleDto;
import com.eanswer.dadaiksan.Dto.EventDto;
import com.eanswer.dadaiksan.Entity.Article;
import com.eanswer.dadaiksan.Entity.Event;
import com.eanswer.dadaiksan.Repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
      SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
      String upDateStr = dateFormat.format(article.getUpdateDate());

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
      articleDto.setUpdateDate(upDateStr);
      articleDtos.add(articleDto);
    }
    return articleDtos;
  }
}
