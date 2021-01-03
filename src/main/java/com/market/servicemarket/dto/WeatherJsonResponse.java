package com.market.servicemarket.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class WeatherJsonResponse {

    //private Current current;

    private String timezone;

    private String timezone_offset;

    private String lon;

    //private Minutely[] minutely;

    private String lat;




}
