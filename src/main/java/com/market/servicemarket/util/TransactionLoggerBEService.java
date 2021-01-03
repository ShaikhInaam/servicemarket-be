package com.market.servicemarket.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.market.servicemarket.entity.TransactionLoggerBeEntity;
import com.market.servicemarket.repository.TransactionLoggerBeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class TransactionLoggerBEService {

    @Autowired
    TransactionLoggerBeRepository repository;

    public void log(String trxId, String url, Object request, Object response, String method)throws Exception{


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

            TransactionLoggerBeEntity entity = TransactionLoggerBeEntity.builder()
                    .requestJson(requestStr).responseJson(responseStr).transactionId(trxId)
                    .time(new Timestamp(System.currentTimeMillis())).method(method).build();

            repository.save(entity);

        }catch (Exception ex){
            ex.printStackTrace();
        }



    }




}
