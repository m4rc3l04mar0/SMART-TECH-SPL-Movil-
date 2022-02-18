package com.example.proyecto_android.personal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.proyecto_android.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListarAlimentosFragment extends Fragment {

    private AlimentosService service;
    List<Alimento> alimentos;
    ListView lvResult;
    ArrayList<String> combo= new ArrayList<>();
    ArrayAdapter<String> adapterU;
    EditText et_buscar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Listar Alimentos");

        View vista = inflater.inflate(R.layout.fragment_listaralimentos, container, false);

        lvResult = (ListView)vista.findViewById(R.id.alimentos_list);
        et_buscar = (EditText)vista.findViewById(R.id.txt_Buscar);



        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.4:8081/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AlimentosService service = retrofit.create(AlimentosService.class);
        Call<List<Alimento>> call = service.getAlimentos();

        call.enqueue(new Callback<List<Alimento>>() {
            @Override
            public void onResponse(Call<List<Alimento>> call, Response<List<Alimento>> response) {

                alimentos = response.body();

                for (int i = 0; i < alimentos.size(); i++){

                    combo.add("ID: "+alimentos.get(i).getIdAlimento() + "\n") ;
                    combo.add("NOMBRE: "+alimentos.get(i).getNombre() + "\n");
                    combo.add("CANTIDAD: "+alimentos.get(i).getCantidad() + "\n");
                    combo.add("COSTO UNIDAD: "+alimentos.get(i).getCostoUnidad() + "\n");
                    combo.add("UNIDAD: "+alimentos.get(i).getUnidade().getIdUnidad() + "\n");
                    combo.add(" " +"\n");
                }
                adapterU = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,  combo);
                lvResult.setAdapter(adapterU);

                et_buscar.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        adapterU.getFilter().filter(s);

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }

            @Override
            public void onFailure(Call<List<Alimento>> call, Throwable t) {
                System.out.println(t.getMessage());
                Toast.makeText(getActivity(), "Error!", Toast.LENGTH_LONG).show();
            }
        });
        return vista;
    }
}