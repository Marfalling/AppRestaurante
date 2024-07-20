package com.example.acuario.activities;

import com.example.acuario.R;
import com.example.acuario.adapters.CartAdapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private List<Product> cartItems;
    private TextView profileName, profileSubtitle, totalAmount;
    private Button finalizePurchaseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_carrito);

        recyclerView = findViewById(R.id.recycler_view_cart);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        profileName = findViewById(R.id.profile_name);
        profileSubtitle = findViewById(R.id.profile_subtitle);
        totalAmount = findViewById(R.id.total_amount);
        finalizePurchaseButton = findViewById(R.id.finalize_purchase_button);

        // Cargar la información del usuario desde SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "N/A");

        profileName.setText(username);
        profileSubtitle.setText("Bienvenido a Acuario");

        cartItems = Cart.getInstance().getCartItems();
        cartAdapter = new CartAdapter(this, cartItems, this::calculateTotalAmount);
        recyclerView.setAdapter(cartAdapter);

        calculateTotalAmount();

        finalizePurchaseButton.setOnClickListener(v -> {
            // Lógica para finalizar la compra
            Cart.getInstance().clearCart();
            cartAdapter.notifyDataSetChanged();
            Intent intent = new Intent(CartActivity.this, QRCompra.class);
            startActivity(intent);
        });
    }

    private void calculateTotalAmount() {
        double total = 0;
        for (Product product : cartItems) {
            total += product.getPrice();
        }
        totalAmount.setText("Total: S/" + String.format("%.2f", total));
    }
}