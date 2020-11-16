package com.market.servicemarket.interceptor;



import com.market.servicemarket.ServicemarketApplication;
import com.market.servicemarket.service.InceptorService;
import com.market.servicemarket.usage_analysis_entity.UsageAnalysisEntity;
import com.market.servicemarket.usage_analysis_repository.UsageAnalysisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class UsageAnalysisInterceptor implements HandlerInterceptor {

    @Autowired
    UsageAnalysisRepository usageAnalysisRepository;


    private static Logger log = LoggerFactory.getLogger(UsageAnalysisInterceptor.class);
    String actionName="";
    String id= "";
    String url="";
    String ip_1;
    String ip_2;
    String method_type="";
    List<UsageAnalysisEntity> usagelist = new ArrayList<>();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String method = request.getMethod();
        String s = request.getRequestURL().toString();
        method_type = method;
        url = request.getRequestURL().toString();
        ip_1 = request.getRemoteAddr();

        try {
            // Fetch IP address by getByName()
            InetAddress ip = InetAddress.getByName(new URL(s)
                    .getHost());

            // Print the IP address
           // System.out.println("Public IP Address of: " + ip);
            ip_2 = ip.toString();
        }catch (MalformedURLException e) {
            // It means the URL is invalid
            System.out.println("Invalid URL");
            return  false;
        }
        if( handler instanceof HandlerMethod) {
            // there are cases where this handler isn't an instance of HandlerMethod, so the cast fails.
            HandlerMethod handlerMethod = (HandlerMethod) handler;

            //controllerName = handlerMethod.getBean().getClass().getSimpleName().replace("Controller", "");
            // controllerName  = handlerMethod.getBeanType().getSimpleName().replace("Controller", "");
            actionName = handlerMethod.getMethod().getName();
        }
        saveInceptorInfo();

        return  true;
    }




    public  void saveInceptorInfo(){

        UsageAnalysisEntity usage = new UsageAnalysisEntity();
        int id = ++ServicemarketApplication.usageEntityIdSquence;
        usage.setId(id);
        usage.setIp(ip_1);
        usage.setMethod(method_type);
        usage.setOperation(actionName);
        usage.setUrl(url);
        usageAnalysisRepository.save(usage);


    }


}
