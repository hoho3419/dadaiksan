package com.eanswer.dadaiksan.Entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString

public class ShopImage {
    @Id
    @Column(name = "shop_image_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String imgUrl;

}
