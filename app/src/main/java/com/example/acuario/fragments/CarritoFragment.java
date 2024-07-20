package com.example.acuario.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acuario.R;
import com.example.acuario.adapters.CartAdapter;

import com.example.acuario.activities.Product;
import com.example.acuario.activities.Cart;
import com.example.acuario.activities.QRCompra;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class CarritoFragment extends Fragment {

    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private List<Product> cartItems;
    private TextView profileName, profileSubtitle, totalAmount;
    private Button finalizePurchaseButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carrito, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_cart);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        profileName = view.findViewById(R.id.profile_name);
        profileSubtitle = view.findViewById(R.id.profile_subtitle);
        totalAmount = view.findViewById(R.id.total_amount);
        finalizePurchaseButton = view.findViewById(R.id.finalize_purchase_button);

        // Cargar la información del usuario desde SharedPreferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "N/A");

        profileName.setText(username);
        profileSubtitle.setText("Bienvenido a Acuario");

        cartItems = Cart.getInstance().getCartItems();
        cartAdapter = new CartAdapter(getContext(), cartItems, this::calculateTotalAmount);
        recyclerView.setAdapter(cartAdapter);

        calculateTotalAmount();

        finalizePurchaseButton.setOnClickListener(v -> {
            // Lógica para finalizar la compra
            Cart.getInstance().clearCart();
            cartAdapter.notifyDataSetChanged();
            Intent intent = new Intent(getActivity(), QRCompra.class);
            startActivity(intent);
        });

        return view;
    }

    private void calculateTotalAmount() {
        double total = 0;
        for (Product product : cartItems) {
            total += product.getPrice();
        }
        totalAmount.setText("Total: S/" + String.format("%.2f", total));
    }
}