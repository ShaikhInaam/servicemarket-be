package com.market.servicemarket.entity;

import org.springframework.context.annotation.Profile;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

//@Component
//@Document(collection = "inceptor")

public class InceptorInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "method_Name")
    private String methodName;

    @Column(name = "method_Type")
    private String methodType;
    @Column(name = "api")
    private String  url;
    @Column(name = "ip_address_one")
    private String ip_one;
    @Column(name = "ip_address_two")
    private String ip_two;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIp_one() {
        return ip_one;
    }

    public void setIp_one(String ip_one) {
        this.ip_one = ip_one;
    }

    public String getIp_two() {
        return ip_two;
    }

    public void setIp_two(String ip_two) {
        this.ip_two = ip_two;
    }
}
