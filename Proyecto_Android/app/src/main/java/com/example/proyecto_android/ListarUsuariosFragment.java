package com.example.proyecto_android;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListarUsuariosFragment extends Fragment {
    private UsuariosService service;
    List<Usuario> idUsuarios;
    ListView lvResult;
    ArrayList<String> combo= new ArrayList<>();
    ArrayAdapter<String> adapterU;
    EditText et_buscar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Listar Usuarios");

        View vista = inflater.inflate(R.layout.fragment_listarusuarios, container, false);

        lvResult = (ListView)vista.findViewById(R.id.usuarios_list);
        et_buscar = (EditText)vista.findViewById(R.id.txt_Buscar);



        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.4:8081/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(UsuariosService.class);


        Call<List<Usuario>> call = service.getUsuarios();

        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                idUsuarios = response.body();


                for (int i = 0; i < idUsuarios.size(); i++){

                    combo.add("ID: "+idUsuarios.get(i).getIdUsuario() + "\n");
                    combo.add("NOMBRE: "+idUsuarios.get(i).getNombre() + "\n");
                    combo.add("APELLIDO: "+idUsuarios.get(i).getApellido() + "\n");
                    combo.add("PASSWORD: "+idUsuarios.get(i).getContraseña() + "\n");
                    combo.add("USUARIO: "+idUsuarios.get(i).getUsuario() + "\n");
                    combo.add("PERFIL: "+idUsuarios.get(i).getPerfil() + "\n");
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
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                System.out.println(t.getMessage());
                Toast.makeText(getActivity(), "Error!", Toast.LENGTH_LONG).show();
            }
        });

        return vista;
        }
        }
