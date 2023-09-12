package com.eanswer.dadaiksan.Repository;

import com.eanswer.dadaiksan.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
