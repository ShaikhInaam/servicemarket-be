package com.market.servicemarket.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class MenuJsonResponse {

    private String level;

    private String name;

    private String isActive;

    private MenuJsonResponse[] nextLevel;

}
