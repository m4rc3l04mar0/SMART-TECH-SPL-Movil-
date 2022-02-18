package com.example.proyecto_android;

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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConsumosdeAlimentosFragment extends Fragment {

    private AlimentosService serviceA;
    private TernerasService service;
    private ConsumosService serviceC;
    private Spinner sp_idTerneras, sp_idAlimentos;
    private EditText et_cantidadconsumida, et_fecha;
    private Button btn_confirmar;
    private List<Ternera> idTerneras;
    private List<Alimento> idAlimentos;
    private ArrayList<Long> combo = new ArrayList<>();
    private ArrayList<Long> comboA = new ArrayList<>();
    private ArrayAdapter adapter_idTerneras;
    private ArrayAdapter adapter_idAlimentos;
    private int numeroInt;
    private int numeroIntA;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Consumo de Alimentos");
        View vista=inflater.inflate(R.layout.fragment_consumodealimentos,container,false);


        sp_idTerneras=(Spinner)vista.findViewById(R.id.sp_idTernera_CDA);
        sp_idAlimentos=(Spinner)vista.findViewById(R.id.sp_idAlimento_CDA);
        et_cantidadconsumida=(EditText)vista.findViewById(R.id.et_CantidadConsumida_CDA);
        et_fecha=(EditText)vista.findViewById(R.id.et_FechadeConsumo_CDA);
        btn_confirmar=(Button)vista.findViewById(R.id.btn_Confirmar_CDA);


        btn_confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(et_cantidadconsumida.getText().toString().isEmpty()||et_fecha.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Ingrese un valor en cada campo", Toast.LENGTH_LONG).show();
                }else {
                    String cantidadconsumida_String = et_cantidadconsumida.getText().toString();
                    String fecha_String = et_fecha.getText().toString();


                    Long numeroLongIdTernera = combo.get(numeroInt);
                    Long numeroLongIdAlimento = comboA.get(numeroIntA);
                    BigDecimal cantidadconsumida = new BigDecimal(cantidadconsumida_String);


                    Ternera ter = new Ternera();
                    ter.setIdTernera(numeroLongIdTernera);

                    Alimento ali = new Alimento();
                    ali.setIdAlimento(numeroLongIdAlimento);

                    Consumo consumo = new Consumo();
                    consumo.setAlimento(ali);
                    consumo.setTernera(ter);
                    consumo.setCantidad(cantidadconsumida);


                    ConsumoPk consumopk = new ConsumoPk();
                    consumopk.setFecha(fecha_String);
                    consumopk.setIdAlimento(numeroLongIdAlimento);
                    consumopk.setIdTernera(numeroLongIdTernera);

                    consumo.setConsumopk(consumopk);


                    agregarConsumo(consumo);
                }

            }
        });

        listarIdTerneras();
        listarIdAlimentos();

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
                sp_idTerneras.setAdapter(adapter_idTerneras);

                sp_idTerneras.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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


    public void listarIdAlimentos() {


        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.4:8081/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        serviceA = retrofit.create(AlimentosService.class);


        Call<List<Alimento>> call = serviceA.getAlimentos();

        call.enqueue(new Callback<List<Alimento>>() {
            @Override
            public void onResponse(Call<List<Alimento>> call, Response<List<Alimento>> response) {
                idAlimentos = response.body();
                for (int i = 0; i < idAlimentos.size(); i++) {

                    comboA.add(idAlimentos.get(i).getIdAlimento());


                }
                adapter_idAlimentos = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, comboA);
                adapter_idAlimentos.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                sp_idAlimentos.setAdapter(adapter_idAlimentos);

                sp_idAlimentos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        numeroIntA = position;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onFailure(Call<List<Alimento>> call, Throwable t) {
                System.out.println(t.getMessage());
                Toast.makeText(getActivity(), "Error!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void agregarConsumo(Consumo consumo) {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.4:8081/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        serviceC = retrofit.create(ConsumosService.class);

        Call<Void> call = serviceC.addConsumo(consumo);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println("Consumo agregado: " + response.message());
                Toast.makeText(getActivity(), "Consumo Agregado correctamente!", Toast.LENGTH_LONG).show();

                et_cantidadconsumida.setText("");
                et_fecha.setText("");
                et_cantidadconsumida.requestFocus();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println(t.getMessage());
                Toast.makeText(getActivity(), "Error!", Toast.LENGTH_LONG).show();
            }
        });

    }
    }
