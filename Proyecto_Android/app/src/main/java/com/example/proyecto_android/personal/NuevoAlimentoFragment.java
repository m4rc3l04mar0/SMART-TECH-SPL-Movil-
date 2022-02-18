package com.example.proyecto_android.personal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.proyecto_android.Alimento;
import com.example.proyecto_android.AlimentosService;
import com.example.proyecto_android.R;
import com.example.proyecto_android.TipoUnidad;
import com.example.proyecto_android.Unidade;
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

public class NuevoAlimentoFragment extends Fragment {

    private AlimentosService service;
    private Spinner spinner_TipoUnidad;
    private EditText et_nombre, et_costoporunidad, et_cantidaddisponible;
    private Button btn_Enviar;
    private String nombre;
    private String costoporunidad_String;
    private String cantidaddisponible_String;
    private BigDecimal costoporunidad_BigDecimal;
    private BigDecimal cantidaddisponible_BigDecimal;
    private String seleccion;
    private List<Alimento> idAlimento;
    private ArrayList<Long> combo = new ArrayList<>();
    private long id_unidad;
    private Unidade uni;
    private Alimento alimento;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Nuevo Alimento");

        View vista=inflater.inflate(R.layout.fragment_nuevoalimento,container,false);



        et_nombre = (EditText)vista.findViewById(R.id.et_Nombre_AA);
        et_costoporunidad = (EditText)vista.findViewById(R.id.et_CostoPorUnidad_AA);
        et_cantidaddisponible = (EditText)vista.findViewById(R.id.et_CantidadDisponible_AA);
        spinner_TipoUnidad=(Spinner)vista.findViewById(R.id.sp_Unidad);

        ArrayAdapter adapter= ArrayAdapter.createFromResource(getActivity(), R.array.tipounidad, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner_TipoUnidad.setAdapter(adapter);


            btn_Enviar=(Button)vista.findViewById(R.id.btn_Enviar_AA);


                 btn_Enviar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(et_nombre.getText().toString().isEmpty() || et_costoporunidad.getText().toString().isEmpty() || et_cantidaddisponible.getText().toString().isEmpty()){
                        Toast.makeText(getActivity(), "Ingrese un valor en cada campo", Toast.LENGTH_LONG).show();
                    }else {

                        nombre = et_nombre.getText().toString();
                        costoporunidad_String = et_costoporunidad.getText().toString();
                        cantidaddisponible_String = et_cantidaddisponible.getText().toString();

                        costoporunidad_BigDecimal = new BigDecimal(costoporunidad_String);
                        cantidaddisponible_BigDecimal = new BigDecimal(cantidaddisponible_String);

                        seleccion = spinner_TipoUnidad.getSelectedItem().toString();


                        alimento = new Alimento();
                        alimento.setNombre(nombre);
                        alimento.setCostoUnidad(costoporunidad_BigDecimal);
                        alimento.setCantidad(cantidaddisponible_BigDecimal);
                        TipoUnidad tipouni = TipoUnidad.valueOf(seleccion);

                        String tipounidad = tipouni.toString();

                        if (tipounidad.equals("KG")) {
                            id_unidad = 1;
                        } else if (tipounidad.equals("L")) {
                            id_unidad = 2;
                        }


                        uni = new Unidade();
                        uni.setIdUnidad(id_unidad);


                        alimento.setUnidade(uni);
                        ingresarAlimento(alimento);
                    }

            }
        });




        return vista;
    }



    public void ingresarAlimento(Alimento alimento){


        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.4:8081/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AlimentosService service = retrofit.create(AlimentosService.class);
        Call<Void> call = service.addAlimento(alimento);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println("Alimento creado: "+response.message());
                Toast.makeText(getActivity(), "Alimento creado correctamente!", Toast.LENGTH_LONG).show();
                et_nombre.setText("");
                et_costoporunidad.setText("");
                et_cantidaddisponible.setText("");
                et_nombre.requestFocus();

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println(t.getMessage());
                Toast.makeText(getActivity(), "Error!", Toast.LENGTH_LONG).show();
            }
        });

    }
}
