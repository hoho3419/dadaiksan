package com.eanswer.dadaiksan.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    private int viewCount = 0;

    @Builder.Default
    private int likeCount = 0;

    private String articleType;

    private String title;

    private String contents;

    @Column(nullable = false)
    private String imgUrl;

    @Column(nullable = false)
    private String vidUrl;

    @Builder.Default
    private boolean status = true;

    @Builder.Default
    private boolean isAdmin = false;

    private LocalDateTime regDate;

    @Column(nullable = false)
    private String updateDate;

    @OneToMany(mappedBy = "article")
    private List<Comment> comments;

    @OneToMany(mappedBy = "article")
    private List<Likes> like;
}
