package com.rest.ws.primaryApp.security;

// Used for token details.

import com.rest.ws.primaryApp.SpringApplicationContext;

public class SecurityConstants {

    public static final long EXPIRATION_TIME = 864000000;        // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users";
    //public static final String TOKEN_SECRET = "a2b3c4d5";

    public static String getTokenSecret(){

        ApplicationProperties appProperties = (ApplicationProperties) SpringApplicationContext.getBean("ApplicationProperties");
        return appProperties.getTokenSecret();

    }
}
