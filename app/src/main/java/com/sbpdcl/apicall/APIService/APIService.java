package com.sbpdcl.apicall.APIService;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIService {
    @GET("/pincode/{pincode}")
    Call<Object> GetData(@Path("pincode") String pincode);
}
