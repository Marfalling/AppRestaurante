package com.example.acuario.activities;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.acuario.Database.DBHelper;
import com.example.acuario.R;
import com.google.android.material.textfield.TextInputEditText;


public class FrmRegistro extends AppCompatActivity {

    private TextInputEditText firstNameEditText, lastNameEditText, usernameEditText, passwordEditText, phoneEditText, addressEditText, emailEditText;
    private Button registerButton, loginButton;
    private DBHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_registro);

        firstNameEditText = findViewById(R.id.firstName);
        lastNameEditText = findViewById(R.id.lastName);
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        phoneEditText = findViewById(R.id.phone);
        addressEditText = findViewById(R.id.address);
        emailEditText = findViewById(R.id.email);
        registerButton = findViewById(R.id.registerButton);
        loginButton = findViewById(R.id.loginButton);

        databaseHelper = new DBHelper(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAndRegister();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FrmRegistro.this,FrmInicio.class);
                startActivity(intent);
            }
        });
    }

    private void validateAndRegister() {
        String firstName = firstNameEditText.getText().toString().trim();
        String lastName = lastNameEditText.getText().toString().trim();
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String phone = phoneEditText.getText().toString().trim();
        String address = addressEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();

        if (TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || TextUtils.isEmpty(username) ||
                TextUtils.isEmpty(password) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(address) ||
                TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
        } else if (password.length() < 6) {
            Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
        } else {
            long userId = databaseHelper.addUser(firstName, lastName, username, password, phone, address, email);
            if (userId > 0) {
                SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("firstName", firstName);
                editor.putString("lastName", lastName);
                editor.putString("username", username);
                editor.putString("password", password);
                editor.putString("phone", phone);
                editor.putString("address", address);
                editor.putString("email", email);
                editor.apply();

                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(FrmRegistro.this, FrmInicio.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Error en el registro", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
