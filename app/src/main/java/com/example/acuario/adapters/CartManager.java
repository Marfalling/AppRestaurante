package com.example.acuario.adapters;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.acuario.activities.Product;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance;
    private MutableLiveData<List<Product>> cartLiveData;
    private List<Product> cart;

    private CartManager() {
        cart = new ArrayList<>();
        cartLiveData = new MutableLiveData<>();
        cartLiveData.setValue(cart);
    }

    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void addProduct(Product product) {
        cart.add(product);
        cartLiveData.setValue(cart);
    }

    public LiveData<List<Product>> getCart() {
        return cartLiveData;
    }

    public void clearCart() {
        cart.clear();
        cartLiveData.setValue(cart);
    }
}

