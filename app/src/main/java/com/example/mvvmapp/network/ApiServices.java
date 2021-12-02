package com.example.mvvmapp.network;

import com.example.mvvmapp.model.ProductModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServices {

    @GET("products/")
    Call<List<ProductModel>> getProductList();
}
