package com.market.servicemarket.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class Menu {

    private String menu;
    private String isActive;
    private List<MenuJsonResponse> subMenu;


}
