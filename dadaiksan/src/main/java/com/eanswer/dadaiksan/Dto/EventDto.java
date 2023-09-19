package com.eanswer.dadaiksan.Dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class EventDto {
    private Long id;
    private String startDate;  // 230910
    private String finDate;
    private String eventName;
    private String eventContents;
    private LocalDateTime regDate;  // 등록일자
    // 이벤트 수정날짜 dto 작성
    private String eventImg;

}
