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
import com.example.acuario.activities.Cart;
import com.example.acuario.activities.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;
    private List<Product> productList;
    private List<Product> productListFull;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
        this.productListFull = new ArrayList<>(productList);
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.productName.setText(product.getName());
        holder.productPrice.setText("S/." + product.getPrice());
        holder.productImage.setImageResource(product.getImageResource());

        holder.addToCartButton.setOnClickListener(v -> {
            Cart.getInstance().addItem(product);
            Toast.makeText(context, product.getName() + " añadido al carrito", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void filter(String text) {
        productList.clear();
        if (text.isEmpty()) {
            productList.addAll(productListFull);
        } else {
            text = text.toLowerCase();
            for (Product product : productListFull) {
                if (product.getName().toLowerCase().contains(text)) {
                    productList.add(product);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void filterByCategory(String category) {
        productList.clear();
        if (category.isEmpty()) {
            productList.addAll(productListFull);
        } else {
            for (Product product : productListFull) {
                if (product.getCategory().equalsIgnoreCase(category)) {
                    productList.add(product);
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName, productPrice;
        ImageButton addToCartButton;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            productName = itemView.findViewById(R.id.product_name);
            productPrice = itemView.findViewById(R.id.product_price);
            addToCartButton = itemView.findViewById(R.id.add_to_cart_button);
        }
    }
}
