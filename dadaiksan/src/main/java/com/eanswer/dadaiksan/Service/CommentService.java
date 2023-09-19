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
import com.eanswer.dadaiksan.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private MemberRepository memberRepository;

    private CommentDto commentDto;
    @Autowired
    private AuthService authService;

    public boolean newComment(Long id, String type, CommentDto commentDto, HttpServletRequest request, UserDetails userDetails) throws ParseException {

        Member member = authService.validateTokenAndGetUser(request, userDetails);
        Comment comment = new Comment();

        if (type.equals("event")) {
            Event event = eventRepository.findById(id).orElseThrow(() -> new RuntimeException("이벤트가 없습니다."));
            comment.setEvent(event);
        }

        else {
            Article article = articleRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글이 없습니다."));
            comment.setArticle(article);
        }

            comment.setMember(member);
            comment.setContents(commentDto.getContents());
            comment.setImgUrl(commentDto.getImgUrl());
            comment.setRegDate(LocalDateTime.now());
            Comment saveComment = commentRepository.save(comment);
            return saveComment != null;

    }

    public List<CommentDto> getAllComments(Long id, String type) {

        List<Comment> comments = new ArrayList<>();

        if (type.equals("event")) {
            Event event = eventRepository.findById(id).orElseThrow(() -> new RuntimeException("이벤트가 없습니다."));
            comments = commentRepository.findByEvent(event);
        }

        else {
            Article article = articleRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글이 없습니다."));
            comments = commentRepository.findByArticle(article);
        }

        List<CommentDto> commentDtos = new ArrayList<>();

        for (Comment comment : comments) {
            CommentDto commentDto = new CommentDto();
            commentDto.setMemberName(comment.getMember().getNickName());
            commentDto.setId(comment.getId());
            commentDto.setContents(comment.getContents());
            commentDto.setImgUrl(comment.getImgUrl());
            commentDto.setRegDate(comment.getRegDate());
            commentDto.setUpdateDate(comment.getUpdateDate());
            commentDtos.add(commentDto);
        }
        return commentDtos;
    }

}
