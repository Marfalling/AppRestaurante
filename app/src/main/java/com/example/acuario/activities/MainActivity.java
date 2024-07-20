package com.example.acuario.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.acuario.R;
import com.example.acuario.activities.FrmInicio;
import com.example.acuario.activities.FrmRegistro;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void IniciarSesion(View view){
        Intent x= new Intent(this, FrmInicio.class);
        startActivity(x);
    }

    public void Registrarse(View view){
        Intent x= new Intent(this, FrmRegistro.class);
        startActivity(x);
    }
}