package com.eanswer.dadaiksan.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Comment")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String imgUrl;

    private String contents;

    private LocalDateTime regDate;

    @Column(nullable = false)
    private Date updateDate;

    @ManyToOne
    @JoinColumn(name = "Article",nullable = false)
    private Article article;

    @ManyToOne
    @JoinColumn(name = "Member",nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "Event",nullable = false)
    private Event event;
}
