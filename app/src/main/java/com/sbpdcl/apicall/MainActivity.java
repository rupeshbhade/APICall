package com.sbpdcl.apicall;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sbpdcl.apicall.APIService.APIService;
import com.sbpdcl.apicall.APIService.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button btnCallAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCallAPI = findViewById(R.id.btnCallAPI);

        btnCallAPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ApiClient.createService(APIService.class, "Const.username_api", "Const.password_api").GetData("480001").enqueue(new Callback() {
                    public void onResponse(Call call, Response response) {
                        if (response.isSuccessful()) {

                        }
                    }
                    public void onFailure(Call call, Throwable t) {
                        call.cancel();
                    }
                });
            };

        });
    }
}