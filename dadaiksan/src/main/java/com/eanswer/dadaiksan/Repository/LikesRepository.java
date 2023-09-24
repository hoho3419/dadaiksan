package com.eanswer.dadaiksan.Repository;

import com.eanswer.dadaiksan.Entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LikesRepository extends JpaRepository<Likes,Long> {

  @Modifying
  @Query(value = "INSERT INTO likes (member, article, status) " +
        "VALUES (:memberId, :articleId, 1) " +
        "ON DUPLICATE KEY UPDATE status = 1 - status",nativeQuery = true)
  int likesToggle(@Param("articleId") Long articleId,@Param("memberId") Long MemberId);
}
