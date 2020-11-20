package com.market.servicemarket;

import com.market.servicemarket.service.base.ResponseConstantsService;
import com.market.servicemarket.usage_analysis_entity.UsageAnalysisEntity;
import com.market.servicemarket.usage_analysis_repository.UsageAnalysisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
public class ServicemarketApplication {

    public static int usageEntityIdSquence = 0;

    @Autowired
    UsageAnalysisRepository usageAnalysisRepository;

    @Autowired
    ResponseConstantsService constantsService;

    public static void main(String[] args) {
        SpringApplication.run(ServicemarketApplication.class, args);
    }

    @PostConstruct
    private void init() {
        UsageAnalysisEntity usage = usageAnalysisRepository.findTopByOrderByIdDesc();
        if(usage !=null){
            usageEntityIdSquence = usage.getId();
            System.err.println("Usage Entity Sequence = "+usageEntityIdSquence);
        }

        constantsService.updateConstants();
    }
}
