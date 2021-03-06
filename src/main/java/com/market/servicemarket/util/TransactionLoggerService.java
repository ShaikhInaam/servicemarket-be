package com.market.servicemarket.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.market.servicemarket.entity.TransactionLoggerEntity;
import com.market.servicemarket.repository.TransactionLoggerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class TransactionLoggerService {

    @Autowired
    TransactionLoggerRepository repository;

    public void log(String trxId, String url, Object request, Object response, String method){


        try{

            ObjectMapper Obj = new ObjectMapper();
            String requestStr = "{}";
            if(request !=null){
                requestStr = Obj.writeValueAsString(request);
            }

            String responseStr = "{}";
            if(response != null){
                responseStr = Obj.writeValueAsString(response);
            }

            TransactionLoggerEntity entity = TransactionLoggerEntity.builder()
                    .requestJson(requestStr).responseJson(responseStr).transactionId(trxId)
                    .time(new Timestamp(System.currentTimeMillis())).method(method).url(url).build();

            repository.save(entity);

        }catch (Exception ex){
            ex.printStackTrace();
        }



    }




}
