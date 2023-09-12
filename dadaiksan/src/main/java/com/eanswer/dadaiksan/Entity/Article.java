package com.eanswer.dadaiksan.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Article")
public class Article {
    @Id
    @Column(name = "Article_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private int viewCount = 0;

    @Column
    private int likeCount = 0;

    @Column
    private String articleType;

    @Column
    private String title;

    @Column
    private String contents;

    @Column(nullable = false)
    private String imgUrl;

    @Column(nullable = false)
    private String vidUrl;

    @Column
    private boolean status = true;

    @Column
    private boolean isAdmin = false;

    @Column
    private Date regDate;

    @Column(nullable = false)
    private Date updateDate;

    @OneToMany(mappedBy = "article")
    private List<Comment> comments;

    @OneToMany(mappedBy = "article")
    private List<Like> like;
}
