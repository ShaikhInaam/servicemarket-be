package com.market.servicemarket.service;

import com.market.servicemarket.interceptor.UsageAnalysisInterceptor;
import com.market.servicemarket.usage_analysis_entity.UsageAnalysisEntity;
import com.market.servicemarket.usage_analysis_repository.UsageAnalysisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InceptorService {

    String actionName="";
    Integer id;
    String url="";
    String ip_1;
    String ip_2;
    String method_type="";

    @Autowired
    private UsageAnalysisRepository usageRepository;

    @Autowired
    UsageAnalysisInterceptor usageAnalysisInterceptor;

    @Scheduled(fixedRate = 30000)  //After every 30 seconds hit the repository
    public void add2DBJob() {
        List<UsageAnalysisEntity> inceptor_data = usageAnalysisInterceptor.sendInceptorData();

        if (inceptor_data.size() != 0)
           {
             for (UsageAnalysisEntity value : inceptor_data) {
                id = value.getId();
                actionName = value.getMethod();
                method_type = value.getOperation();
                ip_1 = value.getIp();
                url = value.getUrl();
                saveInceptorInfo();
            }
        //  System.out.println("size before"+ inceptor_data.size());
        inceptor_data.clear(); //clear the list after one hit
        //System.out.println("size after" + inceptor_data.size());

       }
    }

    public  void saveInceptorInfo(){
        UsageAnalysisEntity usageInfo = new UsageAnalysisEntity();
        usageInfo.setId(id);
        usageInfo.setMethod(actionName);
        usageInfo.setOperation(method_type);
        usageInfo.setUrl(url);
        usageInfo.setIp(ip_1);
        usageRepository.save(usageInfo);

    }

}