package com.sbpdcl.apicall.APIService;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class AuthenticationInterceptor implements Interceptor {
    private String authToken;

    public AuthenticationInterceptor(String token) {
        this.authToken = token;
    }

    public Response intercept(Chain chain) throws IOException {
        return chain.proceed(chain.request().newBuilder().header("Authorization", this.authToken).build());
    }
}
