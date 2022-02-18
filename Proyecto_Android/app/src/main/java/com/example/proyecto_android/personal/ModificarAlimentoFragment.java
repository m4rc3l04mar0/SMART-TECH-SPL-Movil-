package com.example.proyecto_android.personal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.proyecto_android.Alimento;
import com.example.proyecto_android.AlimentosService;
import com.example.proyecto_android.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ModificarAlimentoFragment extends Fragment {


    private Spinner sp_nomAlimento;
    private EditText et_CostoUnidad_ModAlimento, et_CantidadDisponible_ModAlimento;
    private Button btn_Confirmar_ModAlimento;
    private AlimentosService service;
    private ArrayList<String> combo = new ArrayList<>();
    private List<Alimento> nombresAlimento;
    private ArrayAdapter adapter_NomAlimento;
    private int numeroInt;
    private String nombre,costoUnidad_String, cantidadDisponible_String;
    private BigDecimal costoUnidad, cantidadDisponible;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Modificar Alimento");

        View vista = inflater.inflate(R.layout.fragment_modificaralimento, container, false);


        sp_nomAlimento = (Spinner) vista.findViewById(R.id.sp_NomAlimento);
        et_CostoUnidad_ModAlimento = (EditText) vista.findViewById(R.id.txt_CostoPorUnidad_ModAl);
        et_CantidadDisponible_ModAlimento = (EditText) vista.findViewById(R.id.txt_CantidadDisponible_ModAl);
        btn_Confirmar_ModAlimento = (Button) vista.findViewById(R.id.btn_Confirmar_ModAl);


        btn_Confirmar_ModAlimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(et_CostoUnidad_ModAlimento.getText().toString().isEmpty() || et_CantidadDisponible_ModAlimento.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Ingrese un valor en cada campo", Toast.LENGTH_LONG).show();
                }else {

                    nombre = combo.get(numeroInt);
                    costoUnidad_String = et_CostoUnidad_ModAlimento.getText().toString();
                    cantidadDisponible_String = et_CantidadDisponible_ModAlimento.getText().toString();

                    costoUnidad = new BigDecimal(costoUnidad_String);
                    cantidadDisponible = new BigDecimal(cantidadDisponible_String);


                    Alimento ali = new Alimento();
                    ali.setNombre(nombre);
                    ali.setCostoUnidad(costoUnidad);
                    ali.setCantidad(cantidadDisponible);

                    modificarAlimento(ali);
                }
            }
        });


        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.4:8081/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(AlimentosService.class);


        Call<List<Alimento>> call = service.getAlimentos();

        call.enqueue(new Callback<List<Alimento>>() {
            @Override
            public void onResponse(Call<List<Alimento>> call, Response<List<Alimento>> response) {
                nombresAlimento = response.body();
                for (int i = 0; i < nombresAlimento.size(); i++) {

                    combo.add(nombresAlimento.get(i).getNombre());


                }
                adapter_NomAlimento = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, combo);
                adapter_NomAlimento.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                sp_nomAlimento.setAdapter(adapter_NomAlimento);

                sp_nomAlimento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        numeroInt = position;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onFailure(Call<List<Alimento>> call, Throwable t) {

            }
        });

        return vista;
    }


    public void modificarAlimento(Alimento alimento) {


        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.4:8081/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(AlimentosService.class);

        Call<Void> call = service.updateAlimento(alimento);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println("Alimento modificado: " + response.message());
                Toast.makeText(getActivity(), "Alimento modificado correctamente!", Toast.LENGTH_LONG).show();
                et_CostoUnidad_ModAlimento.setText("");
                et_CantidadDisponible_ModAlimento.setText("");
                et_CostoUnidad_ModAlimento.requestFocus();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println(t.getMessage());
                Toast.makeText(getActivity(), "Error!", Toast.LENGTH_LONG).show();
            }
        });
    }
}