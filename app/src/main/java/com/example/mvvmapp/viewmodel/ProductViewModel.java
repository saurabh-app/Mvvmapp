package com.example.mvvmapp.viewmodel;

import com.example.mvvmapp.model.ProductModel;
import com.example.mvvmapp.network.ApiServices;
import com.example.mvvmapp.network.RetroInstance;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductViewModel extends ViewModel {

    private MutableLiveData<List<ProductModel>> productList;

    public ProductViewModel() {
        productList = new MutableLiveData<>();
    }

    public LiveData<List<ProductModel>> getProductListObserver() {
        return productList;
    }

    public void makeApiCall() {
        ApiServices apiService = RetroInstance.getRetroClient().create(ApiServices.class);
        Call<List<ProductModel>> call = apiService.getProductList();
        call.enqueue(new Callback<List<ProductModel>>() {
            @Override
            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
                productList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ProductModel>> call, Throwable t) {
                productList.postValue(null);
            }
        });
    }
}
