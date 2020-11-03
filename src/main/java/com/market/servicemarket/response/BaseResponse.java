package com.market.servicemarket.response;

import lombok.Data;

@Data
public class BaseResponse {

    private String responseCode;
    private String responseMessage;
    private Object response;
}
