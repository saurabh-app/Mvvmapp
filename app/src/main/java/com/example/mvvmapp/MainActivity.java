package com.example.mvvmapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvvmapp.adapter.ProductListAdapter;
import com.example.mvvmapp.model.ProductModel;
import com.example.mvvmapp.viewmodel.ProductViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ProductListAdapter adapter;
    private List<ProductModel> productModels;

    public ProductViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        final TextView noresult = findViewById(R.id.noResultTv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ProductListAdapter(this, productModels);
        recyclerView.setAdapter(adapter);


            viewModel = ViewModelProviders.of(this).get(ProductViewModel.class);

        viewModel.getProductListObserver().observe(this, new Observer<List<ProductModel>>() {
            @Override
            public void onChanged(List<ProductModel> productModel) {
                if (productModel != null) {
                    productModels = productModel;
                    adapter.setProductList(productModel);
                    noresult.setVisibility(View.GONE);
                } else {
                    noresult.setVisibility(View.VISIBLE);
                }
            }
        });
        try {
            viewModel.makeApiCall();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    @Override
//    public void onMovieClick(ProductModel productModel) {
//        Toast.makeText(this, "Clicked Movie Name is : " +productModel.getTitle(), Toast.LENGTH_SHORT).show();
//    }
}