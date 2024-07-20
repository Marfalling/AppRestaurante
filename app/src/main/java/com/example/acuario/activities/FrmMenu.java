package com.example.acuario.activities;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.acuario.R;
import com.example.acuario.fragments.CarritoFragment;
import com.example.acuario.fragments.HomeFragment;
import com.example.acuario.fragments.ReservacionFragment;
import com.example.acuario.fragments.UsuarioFragment;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class FrmMenu extends AppCompatActivity {

    private MeowBottomNavigation btnNavView;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_menu);
        btnNavView = findViewById(R.id.btnNavView);
        frameLayout = findViewById(R.id.frameLayout);

        // Agrega los ítems al MeowBottomNavigation
        setupMeowBottomNavigation();

        btnNavView.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // Aquí puedes manejar el clic en el menú si es necesario
                return null;
            }
        });

        btnNavView.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                int itemId = model.getId();

                if (itemId == 1) {
                    cargarFragment(new HomeFragment(), false);
                } else if (itemId == 2) {
                    cargarFragment(new ReservacionFragment(), false);
                } else if (itemId == 3) {
                    cargarFragment(new CarritoFragment(), false);
                } else if (itemId == 4) {
                    cargarFragment(new UsuarioFragment(), false);
                }

                return null;
            }
        });

        // Inicializar con el fragmento Home
        if (savedInstanceState == null) {
            btnNavView.show(1, true); // Mostrar el primer ítem, que en este caso es el Home
        }
    }

    private void setupMeowBottomNavigation() {
        btnNavView.add(new MeowBottomNavigation.Model(1, R.drawable.home_icon));
        btnNavView.add(new MeowBottomNavigation.Model(2, R.drawable.calendar_checkmark_icon));
        btnNavView.add(new MeowBottomNavigation.Model(3, R.drawable.carrito_icon));
        btnNavView.add(new MeowBottomNavigation.Model(4, R.drawable.my_account_icon));
    }
        
    private void cargarFragment(Fragment fragment, boolean siInicia) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (siInicia) {
            fragmentTransaction.add(R.id.frameLayout, fragment);
        } else {
            fragmentTransaction.replace(R.id.frameLayout, fragment);
        }

        fragmentTransaction.commit();
    }
}
