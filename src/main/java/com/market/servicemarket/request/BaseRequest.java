package com.market.servicemarket.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BaseRequest {

    private String latitude;
    private String longitude;
    @NotBlank
    private String transactionId;


}
