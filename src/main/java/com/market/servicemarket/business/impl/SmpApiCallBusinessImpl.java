package com.market.servicemarket.business.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.market.servicemarket.dto.JobShiftJsonRequest;
import com.market.servicemarket.dto.SmpGenericApiCallJsonResponse;
import com.market.servicemarket.request.BaseRequest;
import com.market.servicemarket.response.BaseResponse;
import com.market.servicemarket.util.ConfigurationUtil;
import com.market.servicemarket.util.Constants;
import com.market.servicemarket.util.RestServiceUtility;
import com.market.servicemarket.util.TransactionLoggerBEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

import java.util.LinkedHashMap;

public class SmpApiCallBusinessImpl {




    @Autowired
    ConfigurationUtil configurationUtil;

    @Autowired
    RestServiceUtility utility;

    @Autowired
    TransactionLoggerBEService transactionLoggerBEService;



    public BaseResponse postCallApiRequest(BaseRequest request, String authToken, Class<?> jsonRequest, String apiUrl) throws Exception{


        ObjectMapper mapper = new ObjectMapper();
        HttpHeaders header = new HttpHeaders();
        header.add("ContentType", "application/json");
        header.add("Authorization", "Basic "+authToken);


        LinkedHashMap response = (LinkedHashMap) utility.callPostJson(apiUrl, header, jsonRequest, SmpGenericApiCallJsonResponse.class);
        //transactionLoggerBEService.log(jsonRequest.getTransactionId(), configurationUtil.getMessage(Constants.JOBS_PORTAL_BASE_URL)+configurationUtil.getMessage(Constants.JOBS_PORTAL_JOB_SHIFT_API),
        //      jsonRequest, response, configurationUtil.getMessage(Constants.POST_REQUEST_RESPONSE_CODE+" Jobs Portal Job Shift"));

        SmpGenericApiCallJsonResponse jsonResponse = null;
        if(response !=null){

            jsonResponse = mapper.convertValue(response, new TypeReference<SmpGenericApiCallJsonResponse>(){});
            if(jsonResponse.getResponseCode().equals(Constants.SUCCESS_RESPONSE_CODE) &&
                    jsonResponse.getResponseMessage().equalsIgnoreCase(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE))){

                BaseResponse baseResponse = BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                        .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(jsonResponse.getResponse()).build();

                return baseResponse;
            }


        }else{

            BaseResponse baseResponse = BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                    .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(null).build();

            return baseResponse;
        }


        return  BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(null).build();

    }



}
