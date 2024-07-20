package com.example.acuario.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.acuario.R;
import com.example.acuario.adapters.ProductAdapter;
import com.example.acuario.activities.Product;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;
    private TextView profileName, profileSubtitle;
    private SearchView searchView;
    private Button btnBebidas, btnPlatos, btnPostres;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        profileName = view.findViewById(R.id.profile_name);
        profileSubtitle = view.findViewById(R.id.profile_subtitle);
        searchView = view.findViewById(R.id.search_view);
        btnBebidas = view.findViewById(R.id.btn_bebidas);
        btnPlatos = view.findViewById(R.id.btn_platos);
        btnPostres = view.findViewById(R.id.btn_postres);

        // Cargar la información del usuario desde SharedPreferences
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "N/A");

        profileName.setText(username);
        profileSubtitle.setText("Bienvenid@ a Acuario");

        // Crear lista de productos
        productList = new ArrayList<>();

        // Bebidas
        productList.add(new Product("Pisco Sour de Maracuyá", 10, R.drawable.piscosour, "Bebidas"));
        productList.add(new Product("Chicha Morada", 9.50, R.drawable.chicha, "Bebidas"));
        productList.add(new Product("Jugo de Naranja", 8.50, R.drawable.jugonaranja, "Bebidas"));
        productList.add(new Product("Vaso de Cafe", 6.50, R.drawable.cafe, "Bebidas"));
        productList.add(new Product("Agua Mineral", 2.50, R.drawable.agua, "Bebidas"));
        productList.add(new Product("Limonada", 7.5, R.drawable.limonada, "Bebidas"));
        productList.add(new Product("Cerveza Pilsen", 10.5, R.drawable.cervezapilsen, "Bebidas"));
        productList.add(new Product("Copa de Vino", 15.50, R.drawable.vino, "Bebidas"));

        // Platos
        productList.add(new Product("Ceviche de Corvina", 32.50, R.drawable.ceviche, "Platos"));
        productList.add(new Product("Seco de Cordero con Frejoles", 35, R.drawable.secocordero, "Platos"));
        productList.add(new Product("Lomo Saltado de Alpaca", 29.90, R.drawable.lomo, "Platos"));
        productList.add(new Product("Tortilla de Maiz Morado", 15.50, R.drawable.taco, "Platos"));
        productList.add(new Product("Ají de Gallina con Papa Amarilla", 28.90, R.drawable.ajidegallina, "Platos"));
        productList.add(new Product("Arroz con Pollo", 32.90, R.drawable.arrozpollo, "Platos"));
        productList.add(new Product("Pescado Frito", 29.90, R.drawable.pescadofrito, "Platos"));
        productList.add(new Product("Filete de Res", 31.90, R.drawable.filete, "Platos"));

        // Postres
        productList.add(new Product("Pie de Manzana", 12.90, R.drawable.tartamanzana, "Postres"));
        productList.add(new Product("Helado de Vainilla", 9.90, R.drawable.helado, "Postres"));
        productList.add(new Product("Suspiro Limeño con Merengue de Lucuma", 11.00, R.drawable.suspiro, "Postres"));
        productList.add(new Product("Tarta de Maracuya", 14.90, R.drawable.tarta, "Postres"));
        productList.add(new Product("Picarones de Quinua con Miel de Cacao", 18.40, R.drawable.picarones, "Postres"));
        productList.add(new Product("Cheesecake de Fresa", 13.00, R.drawable.cheesecake, "Postres"));
        productList.add(new Product("Tiramisú de  Café Peruano", 12.00, R.drawable.tiramisu, "Postres"));
        productList.add(new Product("Mazamorra Morada con Arroz con leche", 17.00, R.drawable.mazamorramorada, "Postres"));


        productAdapter = new ProductAdapter(getContext(), productList);
        recyclerView.setAdapter(productAdapter);

        // Configurar el SearchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                productAdapter.filter(newText);
                return false;
            }
        });

        // Configurar botones de categoría
        btnBebidas.setOnClickListener(v -> productAdapter.filterByCategory("Bebidas"));
        btnPlatos.setOnClickListener(v -> productAdapter.filterByCategory("Platos"));
        btnPostres.setOnClickListener(v -> productAdapter.filterByCategory("Postres"));

        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottom_navigation);


        return view;
    }
}
