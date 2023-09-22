package com.eanswer.dadaiksan.Repository;

import com.eanswer.dadaiksan.Entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LikesRepository extends JpaRepository<Likes,Long> {

  @Modifying
  @Query(value = "REPLACE INTO likes (member, article) " +
      "VALUES (:memberId, :articleId)",nativeQuery = true)
  int likesToggle(@Param("memberId") Long MemberId, @Param("articleId") Long articleId);
}
