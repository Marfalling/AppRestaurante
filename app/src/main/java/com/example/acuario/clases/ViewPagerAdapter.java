package com.example.acuario.clases;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new PlatosFragment(); // Primer fragmento (Platos)
            case 1:
                return new BebidasFragment(); // Segundo fragmento (Bebidas)
            case 2:
                return new PostresFragment(); // Tercer fragmento (Postres)
            default:
                return new PlatosFragment(); // Fragmento por defecto (Platos)
        }
    }

    @Override
    public int getItemCount() {
        return 3; // Número total de pestañas
    }
}
