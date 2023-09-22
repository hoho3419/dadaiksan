package com.eanswer.dadaiksan.Repository;

import com.eanswer.dadaiksan.Dto.ArticleDto;
import com.eanswer.dadaiksan.Entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article,Long> {
  @Query("SELECT m.nickName " +
      "FROM Article a " +
      "INNER JOIN Likes l " +
      "ON a.id = l.article " +
      "INNER JOIN Member m " +
      "ON l.member = m.id " +
      "WHERE a.id = :articleId AND m.id = :memberId")
  String findByArticleAndLikes(@Param("articleId") Long articleId,@Param("memberId") Long memberId);

  @Query(value = "SELECT a.article_id, " +
      "a.article_type, " +
      "a.contents, " +
      "a.img_url, " +
      "a.reg_date, " +
      "a.status, " +
      "a.title, " +
      "a.update_date, " +
      "a.vid_url, " +
      "a.view_count, count(l.article) AS like_counts " +
      "FROM Article a " +
      "LEFT JOIN Likes l ON a.article_id = l.article " +
      "GROUP BY a.article_id",nativeQuery = true)
  List<Object[]> findByAllArticleAndLikes();

//  @Query(value = "SELECT a.article_id, " +
//      "a.article_type, " +
//      "a.contents, " +
//      "a.img_url, " +
//      "a.reg_date, " +
//      "a.status, " +
//      "a.title, " +
//      "a.like_count, " +
//      "a.update_date, " +
//      "a.vid_url, " +
//      "a.view_count, count(l.article) AS like_counts " +
//      "FROM Article a " +
//      "LEFT JOIN Likes l ON a.article_id = l.article " +
//      "GROUP BY a.article_id",nativeQuery = true)
//  List<Object[]> findByAllArticleAndLikes();
}
