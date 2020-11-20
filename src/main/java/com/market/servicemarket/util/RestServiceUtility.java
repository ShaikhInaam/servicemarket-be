package com.market.servicemarket.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestServiceUtility {

    @Autowired
    RestTemplate restTemplate;

    Logger logger = LoggerFactory.getLogger(RestServiceUtility.class);

    public <T> Object callPostJson(String url, HttpHeaders headers, Object requestClass, Class<T> responseClass){

        try{
            ObjectMapper mapper = new ObjectMapper();
            restTemplate = new RestTemplate();
            Object obj = responseClass.newInstance();
            HttpEntity<?> entity = new HttpEntity<>(requestClass, headers);
            String requestJson = mapper.writeValueAsString(entity.getBody());
            logger.info("POST Request ::::::: "+requestJson);
            ResponseEntity<T> response = (ResponseEntity) this.restTemplate.postForEntity(url, entity, Object.class);
            String responseJson = mapper.writeValueAsString(response.getBody());
            logger.info("POST Response ::::::: "+responseJson);

            return response.getBody();


        }catch (Exception ex){
            return null;
        }

    }

    public <T> Object callGetJson(String url, Class<T> responseClass, HttpHeaders headers){

        try{

            HttpEntity request = new HttpEntity(headers);

            ObjectMapper mapper = new ObjectMapper();
            restTemplate = new RestTemplate();
            ResponseEntity<T> response = (ResponseEntity)this.restTemplate.exchange(url, HttpMethod.GET, request, Object.class);
            String responseJson = mapper.writeValueAsString(response.getBody());
            logger.info("GET Response ::::::: "+responseJson);

            return response.getBody();


        }catch (Exception ex){
            return null;
        }

    }

}

