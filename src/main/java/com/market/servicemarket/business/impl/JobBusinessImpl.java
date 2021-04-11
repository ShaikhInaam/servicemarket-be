package com.market.servicemarket.business.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.market.servicemarket.business.base.JobBusiness;
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
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
public class JobBusinessImpl implements JobBusiness {

    @Autowired
    ConfigurationUtil configurationUtil;

    @Autowired
    RestServiceUtility utility;

    @Autowired
    TransactionLoggerBEService transactionLoggerBEService;

    private HttpHeaders creatHeaders(){

        HttpHeaders header = new HttpHeaders();
        header.add("ContentType", "application/json");
        header.add("Authorization", "Basic "+configurationUtil.getMessage(Constants.JOBS_PORTAL_SECRET));

        return header;
    }


    @Override
    public BaseResponse getJobShift(BaseRequest request){


        ObjectMapper mapper = new ObjectMapper();
        HttpHeaders header = creatHeaders();

        JobShiftJsonRequest jsonRequest = new JobShiftJsonRequest();
        jsonRequest.setTransactionId(request.getTransactionId());

        String url = configurationUtil.getMessage(Constants.JOBS_PORTAL_BASE_URL)+configurationUtil.getMessage(Constants.JOBS_PORTAL_JOB_SHIFT_API);

        LinkedHashMap response = (LinkedHashMap) utility.callPostJson(url, header, jsonRequest, SmpGenericApiCallJsonResponse.class);
        transactionLoggerBEService.log(jsonRequest.getTransactionId(), url,
                jsonRequest, response, configurationUtil.getMessage(Constants.POST_REQUEST_RESPONSE_CODE));

        SmpGenericApiCallJsonResponse jsonResponse = null;
        if(response !=null){

            jsonResponse = mapper.convertValue(response, new TypeReference<SmpGenericApiCallJsonResponse>(){});
            if(jsonResponse.getResponseCode().equals(Constants.SUCCESS_RESPONSE_CODE) &&
                    jsonResponse.getResponseMessage().equalsIgnoreCase(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE))){

                BaseResponse baseResponse = BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                        .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(jsonResponse.getResponse()).build();

                return baseResponse;
            }


        }

        return  BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(null).build();

    }



    @Override
    public BaseResponse getJobType(BaseRequest request){


        ObjectMapper mapper = new ObjectMapper();
        HttpHeaders header = creatHeaders();

        JobShiftJsonRequest jsonRequest = new JobShiftJsonRequest();
        jsonRequest.setTransactionId(request.getTransactionId());
        String url = configurationUtil.getMessage(Constants.JOBS_PORTAL_BASE_URL)+configurationUtil.getMessage(Constants.JOBS_PORTAL_JOB_TYPE_API);

        LinkedHashMap response = (LinkedHashMap) utility.callPostJson(url, header, jsonRequest, SmpGenericApiCallJsonResponse.class);
        transactionLoggerBEService.log(jsonRequest.getTransactionId(), url,
              jsonRequest, response, configurationUtil.getMessage(Constants.POST_REQUEST_RESPONSE_CODE));

        SmpGenericApiCallJsonResponse jsonResponse = null;
        if(response !=null){

            jsonResponse = mapper.convertValue(response, new TypeReference<SmpGenericApiCallJsonResponse>(){});
            if(jsonResponse.getResponseCode().equals(Constants.SUCCESS_RESPONSE_CODE) &&
                    jsonResponse.getResponseMessage().equalsIgnoreCase(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE))){

                BaseResponse baseResponse = BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                        .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(jsonResponse.getResponse()).build();

                return baseResponse;
            }


        }

        return  BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(null).build();

    }


    @Override
    public BaseResponse applyJob(BaseRequest request) {

        ObjectMapper mapper = new ObjectMapper();
        HttpHeaders header = creatHeaders();

        JobShiftJsonRequest jsonRequest = new JobShiftJsonRequest();
        jsonRequest.setTransactionId(request.getTransactionId());

        String url = configurationUtil.getMessage(Constants.JOBS_PORTAL_BASE_URL)+configurationUtil.getMessage(Constants.JOBS_PORTAL_JOB_SHIFT_API);

        LinkedHashMap response = (LinkedHashMap) utility.callPostJson(url, header, jsonRequest, SmpGenericApiCallJsonResponse.class);
        transactionLoggerBEService.log(jsonRequest.getTransactionId(), url,
                jsonRequest, response, configurationUtil.getMessage(Constants.POST_REQUEST_RESPONSE_CODE));

        SmpGenericApiCallJsonResponse jsonResponse = null;
        if(response !=null){

            jsonResponse = mapper.convertValue(response, new TypeReference<SmpGenericApiCallJsonResponse>(){});
            if(jsonResponse.getResponseCode().equals(Constants.SUCCESS_RESPONSE_CODE) &&
                    jsonResponse.getResponseMessage().equalsIgnoreCase(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE))){

                BaseResponse baseResponse = BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                        .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(jsonResponse.getResponse()).build();

                return baseResponse;
            }


        }

        return  BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(null).build();

    }


}
