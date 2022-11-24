package com.sbpdcl.apicall;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sbpdcl.apicall.APIService.APIService;
import com.sbpdcl.apicall.APIService.ApiClient;
import com.sbpdcl.apicall.model.PincodeResponseModel;

import org.json.JSONArray;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView tvResult;
    private Button btnCallAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCallAPI = findViewById(R.id.btnCallAPI);
        tvResult = findViewById(R.id.result);

        btnCallAPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("Loading..");
                progressDialog.setCancelable(false);
                progressDialog.show();
                ApiClient.createService(APIService.class, "Const.username_api", "Const.password_api").GetData("480001").enqueue(new Callback() {
                    public void onResponse(Call call, Response response) {
                        if (response.isSuccessful()) {

                            String result = new Gson().toJson(response.body());
                            if (result.trim().length() != 0) {
                                try {
                                    JSONArray jsonArrayObj = new JSONArray(result);
                                    if (jsonArrayObj.length() > 0) {
                                        JSONObject jsonObject = jsonArrayObj.getJSONObject(0);

                                        Log.d("RESPONSE",jsonObject.getString("Message"));
                                        Log.d("RESPONSE",jsonObject.getString("Status"));
                                        //Log.d("RESPONSE",jsonObject.getString("PostOffice"));
                                        tvResult.setText(jsonObject.getString("Message"));
                                        progressDialog.dismiss();

                                    }
                                    progressDialog.dismiss();
                                } catch (Exception ex) {
                                    Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }
                                progressDialog.dismiss();
                            }
                            progressDialog.dismiss();
                        }
                        progressDialog.dismiss();
                    }
                    public void onFailure(Call call, Throwable t) {
                        progressDialog.dismiss();
                        call.cancel();
                    }
                });
            };

        });
    }
}