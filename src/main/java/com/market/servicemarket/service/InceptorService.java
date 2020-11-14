package com.market.servicemarket.service;

import com.market.servicemarket.entity.InceptorInfo;
import com.market.servicemarket.interceptor.UsageAnalysisInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InceptorService {

    String actionName="";
    String id= "";
    String url="";
    String ip_1;
    String ip_2;
    String method_type="";

   // @Autowired
   // private InceptorRepository inceptorRepository;

    @Autowired
    UsageAnalysisInterceptor customInterceptor;

    @Scheduled(fixedRate = 10000)  //After every 10 seconds hit the repository
    public void add2DBJob() {
        List<InceptorInfo> inceptor_data = customInterceptor.sendInceptorData();
        System.out.println(inceptor_data.size());
        if(inceptor_data.size()==0) System.out.println("No data found in Inceptor");

        else {
            System.out.println(inceptor_data.size());
            for (InceptorInfo value : inceptor_data) {
                actionName = value.getMethodName();
                method_type = value.getMethodType();
                ip_1  = value.getIp_one();
                url = value.getUrl();
                ip_2 = value.getIp_two();
                saveInceptorInfo();
            }
          //  System.out.println("size before"+ inceptor_data.size());
            inceptor_data.clear(); //clear the list after one hit
            //System.out.println("size after" + inceptor_data.size());
        }

    }

    public  void saveInceptorInfo(){
        System.out.println("hitting repository");
        InceptorInfo inceptorInfo = new InceptorInfo();
        inceptorInfo.setMethodName(actionName);
        inceptorInfo.setMethodType(method_type);
        inceptorInfo.setUrl(url);
        inceptorInfo.setIp_one(ip_1);
        inceptorInfo.setIp_two(ip_2);
       // inceptorRepository.save(inceptorInfo);

    }

}

