package com.example.acuario.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.acuario.R;
import com.example.acuario.activities.FrmInicio;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UsuarioFragment extends Fragment {

    private TextView profileName, profileLastName, profileUsername, profilePassword, profilePhone, profileAddress, profileEmail;
    private Button editProfileButton, salirbutton;

    public UsuarioFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_usuario, container, false);

        profileName = view.findViewById(R.id.profile_name);
        profileLastName = view.findViewById(R.id.profile_lastname);
        profileUsername = view.findViewById(R.id.profile_username);
        profilePassword = view.findViewById(R.id.profile_password);
        profilePhone = view.findViewById(R.id.profile_phone);
        profileAddress = view.findViewById(R.id.profile_address);
        profileEmail = view.findViewById(R.id.profile_email);
        salirbutton = view.findViewById(R.id.exitButton);

        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String firstName = sharedPreferences.getString("firstName", "N/A");
        String lastName = sharedPreferences.getString("lastName", "N/A");
        String username = sharedPreferences.getString("username", "N/A");
        String password = sharedPreferences.getString("password", "N/A");
        String phone = sharedPreferences.getString("phone", "N/A");
        String address = sharedPreferences.getString("address", "N/A");
        String email = sharedPreferences.getString("email", "N/A");

        profileName.setText(firstName);
        profileLastName.setText(lastName);
        profileUsername.setText(username);
        profilePassword.setText(password);
        profilePhone.setText(phone);
        profileAddress.setText(address);
        profileEmail.setText(email);

        salirbutton.setOnClickListener(v -> {
            startActivity(new Intent(requireContext(), FrmInicio.class));
        });

        return view;
    }
}