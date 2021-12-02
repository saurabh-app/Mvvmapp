package com.example.mvvmapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mvvmapp.R;
import com.example.mvvmapp.model.ProductModel;
import com.example.mvvmapp.model.RatingModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.MyViewHolder> {
    private Context context;
    private List<ProductModel> productModel;

    public ProductListAdapter(Context context, List<ProductModel> productModelList) {
        this.context = context;
        this.productModel = productModelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvTitle.setText("Name"+" :  "+this.productModel.get(position).getTitle().toString());
        holder.category.setText("Category"+" :  "+this.productModel.get(position).getCategory().toString());
        holder.price.setText("Price"+" :  "+this.productModel.get(position).getPrice().toString());
        holder.titleViewd.setText("Description"+" :  "+this.productModel.get(position).getDescription().toString());
        RatingModel rating= productModel.get(position).getRating();
       Float rt=rating.getRate().floatValue();
        holder.rating_bar.setRating(rt);
        holder.rating_bar.setFocusableInTouchMode(false);
        holder.rating_bar.setClickable(false);
        holder.rating_bar.setFocusable(false);
//        holder.rating_bar.setBackgroundColor(Integer.parseInt("FFDF00"));
        Glide.with(context)
                .load(this.productModel.get(position).getImage())
                .apply(RequestOptions.centerCropTransform())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if(this.productModel != null) {
            return this.productModel.size();
        }
        return 0;
    }
    public void setProductList(List<ProductModel> productModelList) {
        this.productModel = productModelList;
        notifyDataSetChanged();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle,category,price,titleViewd;
        ImageView imageView;
        RatingBar rating_bar;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView)itemView.findViewById(R.id.titleView);
            category = (TextView)itemView.findViewById(R.id.category);
            price = (TextView)itemView.findViewById(R.id.price);
            titleViewd = (TextView)itemView.findViewById(R.id.titleViewd);
            rating_bar = (RatingBar)itemView.findViewById(R.id.rating_bar);
            imageView = (ImageView)itemView.findViewById(R.id.imageView);

        }
    }
}
