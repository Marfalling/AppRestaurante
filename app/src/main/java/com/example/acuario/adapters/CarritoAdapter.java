package com.example.acuario.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acuario.R;
import com.example.acuario.activities.Product;

import java.util.List;

public class CarritoAdapter extends RecyclerView.Adapter<CarritoAdapter.CarritoViewHolder> {

    private List<Product> productList;

    public CarritoAdapter(List<Product> productList) {
        this.productList = productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public CarritoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carrito, parent, false);
        return new CarritoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarritoViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.textViewProductName.setText(product.getName());
        holder.textViewProductPrice.setText(String.valueOf(product.getPrice()));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class CarritoViewHolder extends RecyclerView.ViewHolder {
        TextView textViewProductName;
        TextView textViewProductPrice;

        public CarritoViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewProductName = itemView.findViewById(R.id.textViewProductName);
            textViewProductPrice = itemView.findViewById(R.id.textViewProductPrice);
        }
    }
}

