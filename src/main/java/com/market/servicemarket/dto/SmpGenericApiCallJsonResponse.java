package com.market.servicemarket.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SmpGenericApiCallJsonResponse {

    private Object response;

    private String responseMessage;

    private String responseCode;



}
