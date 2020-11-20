package com.market.servicemarket.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.market.servicemarket.dto.WeatherJsonResponse;
import com.market.servicemarket.util.RestServiceUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;

@RestController
public class DefaultController {

    @Autowired
    RestServiceUtility utility;


    @GetMapping("/")
    String greetings() {
        return "Greetings! Service Market pakistan";
    }


    @GetMapping("/weather")
    String getWeather(){

        ObjectMapper mapper = new ObjectMapper();
        HttpHeaders header = new HttpHeaders();
        header.add("ContentType", "application/json");

        LinkedHashMap response = (LinkedHashMap) utility.callGetJson("https://api.openweathermap.org/data/2.5/onecall?lat=33.441792&lon=-94.037689&exclude=hourly,daily&appid=3ed14bbc7011ef0cb338d3a022f7145f", WeatherJsonResponse.class, header);
        WeatherJsonResponse weatherResponse = null;
        if(response !=null){
            weatherResponse = mapper.convertValue(response, new TypeReference<WeatherJsonResponse>(){});

            String st = "Timezone : "+weatherResponse.getTimezone() +" \nLatitude : "+weatherResponse.getLat()+" \nLongitude : "+weatherResponse.getLon();
            return st;
        }

       return null;

    }

}
