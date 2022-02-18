package com.example.proyecto_android;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EventosClinicosFragment extends Fragment {
    private TextView textViewResult;
    private EventosClinicosService service;
    private  List<TieneEnfermedade> enfermedades;
    private ArrayList<String> combo = new ArrayList<>();
    ArrayAdapter<String> adapterU;
    ListView lvResult;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Eventos Clinicos");


        View vista = inflater.inflate(R.layout.fragment_eventosclinicos, container, false);


        lvResult = (ListView)vista.findViewById(R.id.lv_eventosClinicos);


        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.4:8081/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(EventosClinicosService.class);
        Call<List<TieneEnfermedade>> call = service.getEnfermedades();

        call.enqueue(new Callback<List<TieneEnfermedade>>() {
            @Override
            public void onResponse(Call<List<TieneEnfermedade>> call, Response<List<TieneEnfermedade>> response) {
                enfermedades=response.body();
                DateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yy");
                for (int i = 0; i < enfermedades.size(); i++) {


                    combo.add("ID TERNERA" + enfermedades.get(i).getTernera().getIdTernera()+ "\n");
                    combo.add("ENFERMEDAD: "+ enfermedades.get(i).getEnfermedade().getNombre() + "\n");
                    String fechaHasta=formatoDelTexto.format(enfermedades.get(i).getFecHasta());
                    combo.add("FECHA INCIO ENFERMEDAD: "+ fechaHasta + "\n");
                    combo.add("FECHA FIN ENFERMEDAD: "+ enfermedades.get(i).getFecHasta() + "\n");
                    combo.add(" ----------------------------------------------------------- " +"\n");




                }
                adapterU = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,  combo);
                lvResult.setAdapter(adapterU);


            }
            @Override
            public void onFailure(Call<List<TieneEnfermedade>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
        return vista;
    }
}
