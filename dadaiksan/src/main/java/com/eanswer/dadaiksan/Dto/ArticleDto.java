package com.eanswer.dadaiksan.Dto;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Article")
public class ArticleDto {
    private Long id;
    private int viewCount = 0;
    private int likeCount = 0;
    private String articleType;
    private String title;
    private String contents;
    private String imgUrl;
    private String vidUrl;
    private boolean status = true;
    private boolean isAdmin = false;
    private LocalDateTime regDate;
    private String updateDate;
}
