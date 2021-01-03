package com.market.servicemarket;

import com.market.servicemarket.service.base.ConfigurationService;
import com.market.servicemarket.usage_analysis_entity.UsageAnalysisEntity;
import com.market.servicemarket.usage_analysis_repository.UsageAnalysisRepository;
import com.market.servicemarket.util.ConfigurationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Collections;

@SpringBootApplication
public class ServicemarketApplication {

    public static int usageEntityIdSquence = 0;

    @Autowired
    UsageAnalysisRepository usageAnalysisRepository;

    @Autowired
    ConfigurationUtil configurationUtil;


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

        configurationUtil.updateConstants();
    }


    @Bean(name = "appRestClient")
    public RestTemplate getRestClient() {
        RestTemplate restClient = new RestTemplate(
                new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));

        // Add one interceptor like in your example, except using anonymous class.
        restClient.setInterceptors(Collections.singletonList((request, body, execution) -> {

            return execution.execute(request, body);
        }));

        return restClient;
    }

}
