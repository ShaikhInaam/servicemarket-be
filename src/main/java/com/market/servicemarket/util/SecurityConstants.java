package com.market.servicemarket.util;

public interface SecurityConstants {

    public static final String SECRET = "SECRET_KEY";
    public static final long EXPIRATION_TIME = 900_000; // 15 mins
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/auth/login";
    public static final String REGISTRATION_URL = "/user/register";
    public static final String JOB_POST_URL = "/portal/job/job-post";
    public static final String JOB_TYPE_URL = "/portal/job/job-type";
}
