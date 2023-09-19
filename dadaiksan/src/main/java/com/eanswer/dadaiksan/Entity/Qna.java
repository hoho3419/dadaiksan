package com.eanswer.dadaiksan.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Qna")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Qna {

    @Id
    @Column(name = "Qna_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String contents;

    @Column(nullable = false)
    private String imgUrl;

    private boolean isOpen;

    private Date regDate;
}
