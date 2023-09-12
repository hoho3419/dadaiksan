package com.eanswer.dadaiksan.Dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {
    private Long id;
    private Long articleId;
    private Long eventId;
    private Long memberId;
    private String nickName;
    private String password;
    private String imgUrl;
    private String contents;
    private Date regDate;
    private Date updateDate;
}
