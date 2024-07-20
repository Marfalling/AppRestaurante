package com.example.acuario.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acuario.R;
import com.example.acuario.activities.Product;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private List<Product> cartItems;
    private OnProductRemovedListener onProductRemovedListener;

    public interface OnProductRemovedListener {
        void onProductRemoved();
    }

    public CartAdapter(Context context, List<Product> cartItems, OnProductRemovedListener onProductRemovedListener) {
        this.context = context;
        this.cartItems = cartItems;
        this.onProductRemovedListener = onProductRemovedListener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart_product, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Product product = cartItems.get(position);
        holder.productName.setText(product.getName());
        holder.productPrice.setText("S/." + product.getPrice());
        holder.productImage.setImageResource(product.getImageResource());

        holder.deleteProductButton.setOnClickListener(v -> {
            cartItems.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, cartItems.size());
            Toast.makeText(context, product.getName() + " retirado del carro", Toast.LENGTH_SHORT).show();
            if (onProductRemovedListener != null) {
                onProductRemovedListener.onProductRemoved();
            }
        });
    }
    
    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName, productPrice;
        ImageButton deleteProductButton;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            productName = itemView.findViewById(R.id.product_name);
            productPrice = itemView.findViewById(R.id.product_price);
            deleteProductButton = itemView.findViewById(R.id.delete_product_button);
        }
    }
}