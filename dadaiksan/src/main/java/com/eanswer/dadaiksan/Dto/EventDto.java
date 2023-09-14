package com.eanswer.dadaiksan.Dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class EventDto {
    private Long id;
    private String startDate;
    private String finDate;
    private String eventName;
    private String eventContents;
    private Date regDate;
    private String eventImg;

}
