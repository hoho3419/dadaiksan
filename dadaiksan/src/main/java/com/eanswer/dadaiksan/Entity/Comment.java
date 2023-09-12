package com.eanswer.dadaiksan.Entity;

import lombok.*;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String nickName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String imgUrl;

    @Column
    private String contents;

    @Column
    private Date regDate;

    @Column(nullable = false)
    private Date updateDate;

    @ManyToOne
    @JoinColumn(name = "Article",nullable = false)
    private Article article;

    @ManyToOne
    @JoinColumn(name = "Member",nullable = false)
    private Member member;

//    @ManyToOne
//    @JoinColumn(name = "Event",nullable = false)
//    private Event event;
}
