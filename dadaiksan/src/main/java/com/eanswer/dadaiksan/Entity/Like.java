package com.eanswer.dadaiksan.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Like")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Like {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    @ManyToOne
    @JoinColumn(name = "Artcle")
    private Article article;

    @Column
    @OneToOne
    @JoinColumn(name = "Member")
    private Member member;
}
