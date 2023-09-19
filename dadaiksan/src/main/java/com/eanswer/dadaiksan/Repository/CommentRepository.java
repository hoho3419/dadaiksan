package com.eanswer.dadaiksan.Repository;

import com.eanswer.dadaiksan.Dto.CommentDto;
import com.eanswer.dadaiksan.Entity.Article;
import com.eanswer.dadaiksan.Entity.Comment;
import com.eanswer.dadaiksan.Entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByArticle(Article article);
    List<Comment> findByEvent(Event event);
}
