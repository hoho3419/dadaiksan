package com.eanswer.dadaiksan.Service;

import com.eanswer.dadaiksan.Dto.CommentDto;
import com.eanswer.dadaiksan.Dto.EventDto;
import com.eanswer.dadaiksan.Entity.Article;
import com.eanswer.dadaiksan.Entity.Comment;
import com.eanswer.dadaiksan.Entity.Event;
import com.eanswer.dadaiksan.Entity.Member;
import com.eanswer.dadaiksan.Repository.ArticleRepository;
import com.eanswer.dadaiksan.Repository.CommentRepository;
import com.eanswer.dadaiksan.Repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    private CommentDto commentDto;
    @Autowired
    private AuthService authService;

    public boolean newComment(Long id, CommentDto commentDto, HttpServletRequest request, UserDetails userDetails) throws ParseException {

        Member member = authService.validateTokenAndGetUser(request, userDetails);

        Article article = articleRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글이 없습니다."))

        Comment comment = new Comment();

        comment.setArticle(article);
        comment.setMember(member);
        comment.setContents(commentDto.getContents());
        comment.setImgUrl(commentDto.getImgUrl());
        comment.setPassword(commentDto.getPassword());
        comment.setRegDate(LocalDateTime.now());
        Comment saveComment = commentRepository.save(comment);
        return saveComment != null;
    }

}
