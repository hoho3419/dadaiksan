package com.eanswer.dadaiksan.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Likes")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean status;

    @ManyToOne
    @JoinColumn(name = "Article")
    private Article article;
    
    @OneToOne
    @JoinColumn(name = "Member",unique = true)
    private Member member;
}
