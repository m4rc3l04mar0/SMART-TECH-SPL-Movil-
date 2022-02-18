package com.example.proyecto_android;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ModificarUsuarioFragment extends Fragment {

    private Spinner sp_idUsuarios;
    private EditText et_Password;
    private Button btn_Confirmar;
    private String seleccion;
    private Perfil perfil;
    private UsuariosService service;
    List<Usuario> idUsuarios;
    ListView lvResult;
    ArrayList<Long> combo = new ArrayList<>();
    ArrayAdapter adapter_idUsuarios;
    int numeroInt;
    Long numeroLongId;
    String password;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Modificar Usuario");


        View vista = inflater.inflate(R.layout.fragment_modificarusuario, container, false);


        sp_idUsuarios = (Spinner) vista.findViewById(R.id.spinner_id);
        et_Password = (EditText) vista.findViewById(R.id.et_password);
        btn_Confirmar = (Button) vista.findViewById(R.id.btn_Confirmar);


        btn_Confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Seguro que quieres modificar el usuario?");
                builder.setTitle("Confirmar Modificar");
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(et_Password.getText().toString().isEmpty()){
                            Toast.makeText(getActivity(), "Ingrese un valor en el campo", Toast.LENGTH_LONG).show();
                        }else {

                            if(et_Password.length()<8||et_Password.length()>16){
                                Toast.makeText(getActivity(), "La contraseña debe tener al menos 8 digitos y no superar los 16, por favor revise el dato ingresado", Toast.LENGTH_LONG).show();
                            }else {
                                numeroLongId = combo.get(numeroInt);
                                password = et_Password.getText().toString();


                                Usuario usuario = new Usuario();
                                usuario.setIdUsuario(numeroLongId);
                                usuario.setContraseña(password);

                                modificarUsuario(usuario);
                            }
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog =builder.create();
                dialog.show();



            }
        });






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
                for (int i = 0; i < idUsuarios.size(); i++) {

                    combo.add(idUsuarios.get(i).getIdUsuario());


                }
                adapter_idUsuarios = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, combo);
                adapter_idUsuarios.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                sp_idUsuarios.setAdapter(adapter_idUsuarios);

                sp_idUsuarios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                System.out.println(t.getMessage());
                Toast.makeText(getActivity(), "Error!", Toast.LENGTH_LONG).show();

            }
        });


        return vista;
    }

    public void modificarUsuario(Usuario usuario) {


        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.4:8081/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(UsuariosService.class);

        Call<Void> call = service.updateUsuario(numeroLongId,usuario);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println("Usuario modificado: " + response.message());
                Toast.makeText(getActivity(), "Usuario modificado correctamente!", Toast.LENGTH_LONG).show();
                et_Password.setText("");
                et_Password.requestFocus();

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println(t.getMessage());
                Toast.makeText(getActivity(), "Error!", Toast.LENGTH_LONG).show();


            }
        });
    }
}