package com.market.servicemarket.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter @Getter
public class LoginRequest extends BaseRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;


}
