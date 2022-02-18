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

import com.example.proyecto_android.Peso;
import com.example.proyecto_android.PesosService;
import com.example.proyecto_android.R;
import com.example.proyecto_android.Ternera;
import com.example.proyecto_android.TernerasService;
import com.example.proyecto_android.TipoRegistro;
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

public class GananciadePesoFragment extends Fragment {

    private Spinner sp_idTernera, sp_tipoRegistro;
    private EditText et_FechaRegistro, et_Peso;
    private Button btn_Enviar;
    private PesosService serviceP;
    private TernerasService service;
    private List<Ternera> idTerneras;
    private ArrayList<Long> combo = new ArrayList<>();
    private ArrayAdapter adapter_idTerneras;
    private ArrayAdapter adapter;
    private int numeroInt;
    private String seleccion;
    private TipoRegistro tiporegistro;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Ganancia de Peso");
        View vista=inflater.inflate(R.layout.fragment_gananciadepeso,container,false);

        sp_idTernera=(Spinner)vista.findViewById(R.id.sp_IdTernera_GP);
        sp_tipoRegistro=(Spinner)vista.findViewById(R.id.sp_TipoRegistro_GP);
        et_FechaRegistro=(EditText)vista.findViewById(R.id.et_FechaRegistro_GP);
        et_Peso=(EditText)vista.findViewById(R.id.et_Peso_GP);

        btn_Enviar=(Button)vista.findViewById(R.id.btn_Enviar_GP);
        btn_Enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(et_FechaRegistro.getText().toString().isEmpty()||et_Peso.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Ingrese un valor en cada campo", Toast.LENGTH_LONG).show();
                }else {

                    String fecha_String = et_FechaRegistro.getText().toString();


                    String peso_String = et_Peso.getText().toString();
                    seleccion = sp_tipoRegistro.getSelectedItem().toString();

                    tiporegistro = TipoRegistro.valueOf(seleccion);
                    BigDecimal peso_BD = new BigDecimal(peso_String);
                    Long numeroLongIdTernera = combo.get(numeroInt);

                    Peso peso = new Peso();

                    Ternera ternera = new Ternera();
                    ternera.setIdTernera(numeroLongIdTernera);
                    peso.setTernera(ternera);
                    peso.setTipoRegistro(tiporegistro);
                    peso.setFecha(fecha_String);
                    peso.setPeso(peso_BD);

                    agregarPeso(peso);
                }

            }
        });






        adapter= ArrayAdapter.createFromResource(getActivity(), R.array.tiporegistro, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        sp_tipoRegistro.setAdapter(adapter);


        listarIdTerneras();


        return vista;
    }


    public void listarIdTerneras(){

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.4:8081/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(TernerasService.class);


        Call<List<Ternera>> call = service.getTerneras();

        call.enqueue(new Callback<List<Ternera>>() {
            @Override
            public void onResponse(Call<List<Ternera>> call, Response<List<Ternera>> response) {
                idTerneras = response.body();
                for (int i = 0; i < idTerneras.size(); i++) {

                    combo.add(idTerneras.get(i).getIdTernera());


                }
                adapter_idTerneras = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, combo);
                adapter_idTerneras.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                sp_idTernera.setAdapter(adapter_idTerneras);

                sp_idTernera.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
            public void onFailure(Call<List<Ternera>> call, Throwable t) {
                System.out.println(t.getMessage());
                Toast.makeText(getActivity(), "Error!", Toast.LENGTH_LONG).show();
            }
        });
    }


    public void agregarPeso(Peso peso){

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.4:8081/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        serviceP = retrofit.create(PesosService.class);

        Call<Void> call = serviceP.addPeso(peso);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println("Peso agregado: " + response.message());
                Toast.makeText(getActivity(), "Peso Agregado correctamente!", Toast.LENGTH_LONG).show();

                et_FechaRegistro.setText("");
                et_Peso.setText("");
                et_FechaRegistro.requestFocus();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println(t.getMessage());
                Toast.makeText(getActivity(), "Error!", Toast.LENGTH_LONG).show();
            }
        });


    }
}
