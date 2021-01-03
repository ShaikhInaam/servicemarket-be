package com.market.servicemarket.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UserToken{

    private String token;
    private String validity;

}
