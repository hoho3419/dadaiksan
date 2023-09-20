package com.eanswer.dadaiksan.Repository;

import com.eanswer.dadaiksan.Dto.ArticleDto;
import com.eanswer.dadaiksan.Entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article,Long> {
  @Query("SELECT m.nickName " +
      "FROM Article a " +
      "INNER JOIN Likes l " +
      "ON a.article_id = l.article " +
      "INNER JOIN Member m " +
      "ON l.member = m.id " +
      "WHERE a.id = :articleId AND m.member = :memberId")
  String findByArticleAndLikes(@Param("articleId") Long articleId,@Param("memberId") Long memberId);
}
