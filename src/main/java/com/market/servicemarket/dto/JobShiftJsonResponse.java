package com.market.servicemarket.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class JobShiftJsonResponse {

    private String[] response;

    private String responseMessage;

    private String responseCode;



}
