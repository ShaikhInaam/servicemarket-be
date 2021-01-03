package com.market.servicemarket.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sub_menu")
public class SubMenuEntity {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "menu_id")
    private Integer menuId;

    private String value;

}
