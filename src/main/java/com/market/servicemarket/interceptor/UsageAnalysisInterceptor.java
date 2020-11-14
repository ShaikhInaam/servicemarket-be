package com.market.servicemarket.interceptor;



import com.market.servicemarket.entity.InceptorInfo;
import com.market.servicemarket.service.InceptorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class UsageAnalysisInterceptor implements HandlerInterceptor {

    @Autowired(required = true)
    InceptorService inceptorService;

   // @Autowired
    //InceptorRepository inceptorRepository;
    private static Logger log = LoggerFactory.getLogger(UsageAnalysisInterceptor.class);
    String actionName="";
    String id= "";
    String url="";
    String ip_1;
    String ip_2;
    String method_type="";
    List<InceptorInfo> inceptorlist = new ArrayList<>();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.info("BookInterceptor - preHandle");
        boolean flag = true;
        String method = request.getMethod();
        log.info("Mehtod is "+ method);
        log.info("uri "+request.getRequestURI());
        log.info("urL "+request.getRequestURL());
        log.info("ip "+ request.getRemoteAddr());
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
/*        //log.info("Name: "+actionName);
        HttpServletRequest requests = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        		int contentLength =request.getContentLength();
        if(method.equalsIgnoreCase("post") || method.equalsIgnoreCase("put")) {
            String contentType = request.getContentType();
            if(contentType != null && !contentType.equalsIgnoreCase("application/json")) {
                flag = false;
            }
			else if(contentLength <= 2) {
				flag = false;
			}
        }
        if(!flag) {
            response.sendRedirect("/rest/books/invalid");
        }
        return flag;

        return HandlerInterceptor.super.preHandle(request, response, handler);

  */
        saveInceptorInfo();
        sendInceptorData();
        return  true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        log.info("post - postHandle");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        log.info("after - afterCompletion");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }


    public  void saveInceptorInfo(){

        InceptorInfo inceptorInfo = new InceptorInfo();
        inceptorInfo.setId(0);
        inceptorInfo.setMethodName(actionName);
        inceptorInfo.setMethodType(method_type);
        inceptorInfo.setUrl(url);
        inceptorInfo.setIp_one(ip_1);
        inceptorInfo.setIp_two(ip_2);
        inceptorlist.add(inceptorInfo);
        //inceptorRepository.save(inceptorInfo);


    }

    public List<InceptorInfo> sendInceptorData() {

        /*  List<Customer> customerList = customerRepositry.findByCustomername(name);
          System.out.println("sizeoflist: "+inceptorlist.size());
        for(InceptorInfo inceptors : inceptorlist){
            System.out.println(inceptors.getMethodName());
            System.out.println(inceptors.getMethodType());
            System.out.println(inceptors.getIp_one());
            System.out.println(inceptors.getUrl());
            System.out.println(inceptors.getIp_two());
        }*/
        return inceptorlist;
    }




}
