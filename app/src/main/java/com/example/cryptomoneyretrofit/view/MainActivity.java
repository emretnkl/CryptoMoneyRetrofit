package com.example.cryptomoneyretrofit.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.cryptomoneyretrofit.R;
import com.example.cryptomoneyretrofit.model.CryptoModel;
import com.example.cryptomoneyretrofit.service.CryptoAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    //https://api.nomics.com/v1/prices?key=75bc7e4f25514e0baef6e6316bbe81ac

    ArrayList<CryptoModel> cryptoModels;
    private String BASE_URL = "https://api.nomics.com/v1/";
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Retrofit & JSON

        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        loadData();
    }

    private void loadData() {

        CryptoAPI cryptoAPI = retrofit.create(CryptoAPI.class);

        Call<List<CryptoModel>> call = cryptoAPI.getData();

        call.enqueue(new Callback<List<CryptoModel>>() {
            @Override
            public void onResponse(Call<List<CryptoModel>> call, Response<List<CryptoModel>> response) {

                if (response.isSuccessful()) {
                    List<CryptoModel> responseList = response.body();
                    cryptoModels = new ArrayList<>(responseList);

                    for (CryptoModel cryptoModel : cryptoModels) {
                        System.out.println(cryptoModel.currency);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<CryptoModel>> call, Throwable t) {
                t.printStackTrace();

            }
        });

    }
}
