package com.sbpdcl.apicall.APIService;

import com.sbpdcl.apicall.model.PincodeResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIService {
    @GET("/pincode/{pincode}")
    Call<List<PincodeResponseModel>> GetData(@Path("pincode") String pincode);
}
