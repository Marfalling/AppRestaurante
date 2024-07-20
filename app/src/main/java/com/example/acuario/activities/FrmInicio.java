package com.example.acuario.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.acuario.Database.DBHelper;
import com.example.acuario.R;

public class FrmInicio extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private Button loginButton;
    private TextView registerText;
    private DBHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_inicio);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        registerText = findViewById(R.id.registerText);

        databaseHelper = new DBHelper(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAndLogin();
            }
        });

        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FrmInicio.this, FrmRegistro.class);
                startActivity(intent);
            }
        });
    }

    private void validateAndLogin() {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            Cursor cursor = databaseHelper.getUser(username, password);
            if (cursor != null && cursor.moveToFirst()) {
                SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("firstName", cursor.getString(cursor.getColumnIndexOrThrow("firstName")));
                editor.putString("lastName", cursor.getString(cursor.getColumnIndexOrThrow("lastName")));
                editor.putString("username", cursor.getString(cursor.getColumnIndexOrThrow("username")));
                editor.putString("password", cursor.getString(cursor.getColumnIndexOrThrow("password")));
                editor.putString("phone", cursor.getString(cursor.getColumnIndexOrThrow("phone")));
                editor.putString("address", cursor.getString(cursor.getColumnIndexOrThrow("address")));
                editor.putString("email", cursor.getString(cursor.getColumnIndexOrThrow("email")));
                editor.apply();

                Toast.makeText(this, "Éxito al iniciar sesión", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(FrmInicio.this, FrmMenu.class);
                intent.putExtra("isLoggedIn", true);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Usuario no existe o credenciales incorrectas", Toast.LENGTH_SHORT).show();
            }
        }
    }
}