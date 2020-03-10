package com.example.cryptomoneyretrofit.service;

import com.example.cryptomoneyretrofit.model.CryptoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CryptoAPI {

    //https://api.nomics.com/v1/prices?key=75bc7e4f25514e0baef6e6316bbe81ac

    @GET("prices?key=75bc7e4f25514e0baef6e6316bbe81ac")
    Call<List<CryptoModel>> getData();


}
