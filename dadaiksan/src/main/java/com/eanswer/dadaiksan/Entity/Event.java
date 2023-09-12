package com.eanswer.dadaiksan.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "event")
public class Event {
    @Id
    @Column(name = "event_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date finDate;

    @Column(nullable = false)
    private String eventName;

    @Column(nullable = false)
    private String eventContents;

    @Column(nullable = false)
    private Date regDate;

    private String eventImg;

//    @OneToMany(mappedBy = "event")
//    private List<Comment> comments; // 이벤트 게시글의 댓글 리스트

}
