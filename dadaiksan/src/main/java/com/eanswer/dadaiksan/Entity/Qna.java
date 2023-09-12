package com.eanswer.dadaiksan.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "member")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Qna {

    @Id
    @Column(name = "Qna_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String title;

    @Column
    private String contents;

    @Column(nullable = false)
    private String imgUrl;

    @Column
    private boolean isOpen;

    @Column
    private Date regDate;
}