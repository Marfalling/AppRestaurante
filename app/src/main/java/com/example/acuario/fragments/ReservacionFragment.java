package com.example.acuario.fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.acuario.Database.DBHelper;
import com.example.acuario.R;

import java.util.Calendar;

public class ReservacionFragment extends Fragment {

    private EditText etNombre, etFecha, etHora, etNotas;
    private Button btnFecha, btnHora, btnConfirmar;
    private Spinner spPersonas;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_reservacion, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etNombre = view.findViewById(R.id.et_nombre);
        etFecha = view.findViewById(R.id.et_fecha);
        etHora = view.findViewById(R.id.et_hora);
        etNotas = view.findViewById(R.id.et_notas);
        btnFecha = view.findViewById(R.id.btn_fecha);
        btnHora = view.findViewById(R.id.btn_hora);
        btnConfirmar = view.findViewById(R.id.btn_confirmar);
        spPersonas = view.findViewById(R.id.sp_personas);

        // Configurar el Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getContext(), R.array.numero_personas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPersonas.setAdapter(adapter);

        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        btnHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarReservacion();
            }
        });
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                (view, year1, month1, dayOfMonth) -> etFecha.setText(dayOfMonth + "/" + (month1 + 1) + "/" + year1),
                year, month, day);
        datePickerDialog.show();
    }

    private void showTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                getContext(),
                (view, hourOfDay, minute1) -> etHora.setText(hourOfDay + ":" + minute1),
                hour, minute, true);
        timePickerDialog.show();
    }

    private void guardarReservacion() {
        DBHelper acuario = new DBHelper(getContext());

        String nombre = etNombre.getText().toString();
        String fecha = etFecha.getText().toString();
        String hora = etHora.getText().toString();
        int personas = Integer.parseInt(spPersonas.getSelectedItem().toString());
        String notas = etNotas.getText().toString();

        int id_user = 1;

        long result = acuario.addReservacion(nombre, fecha, hora, personas, notas, id_user);

        if (result != -1) {
            Toast.makeText(getContext(), "Reservación confirmada y guardada.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Error al guardar la reservación.", Toast.LENGTH_SHORT).show();
        }
    }
}
