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

import com.example.proyecto_android.Perfil;
import com.example.proyecto_android.R;
import com.example.proyecto_android.Usuario;
import com.example.proyecto_android.UsuariosService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class NuevoUsuarioFragment extends Fragment {
    private UsuariosService service;
    private Spinner spinner1;
    private EditText et1, et2, et3, et4, et5;
    private Button btn_enviar;
    private String nombre1_String;
    private String nombre2_String;
    private String apellido1_String;
    private String apellido2_String;
    private String seleccion;
    private String password_String;
    private Perfil perfil;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Nuevo Usuario");

        View vista=inflater.inflate(R.layout.fragment_nuevousuario,container,false);

        et1=(EditText)vista.findViewById(R.id.txt_primernombre);
        et2=(EditText)vista.findViewById(R.id.txt_segundonombre);
        et3=(EditText)vista.findViewById(R.id.txt_primerapellido);
        et4=(EditText)vista.findViewById(R.id.txt_segundoapellido);
        et5=(EditText)vista.findViewById(R.id.txt_password);
        spinner1=(Spinner)vista.findViewById(R.id.spinner);
        btn_enviar=(Button)vista.findViewById(R.id.btn_Enviar);

        ArrayAdapter adapter= ArrayAdapter.createFromResource(getActivity(), R.array.perfil, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner1.setAdapter(adapter);


        btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if(et1.getText().toString().isEmpty() || et2.getText().toString().isEmpty() || et3.getText().toString().isEmpty() || et4.getText().toString().isEmpty() || et5.getText().toString().isEmpty()){
                        Toast.makeText(getActivity(), "Ingrese un valor en cada campo", Toast.LENGTH_LONG).show();
                    }else {

                        nombre1_String = et1.getText().toString();
                        nombre2_String = et2.getText().toString();
                        apellido1_String = et3.getText().toString();
                        apellido2_String = et4.getText().toString();
                        password_String = et5.getText().toString();


                        seleccion = spinner1.getSelectedItem().toString();

                        perfil = Perfil.valueOf(seleccion);

                        String nombre = nombre1_String + " " + nombre2_String;
                        String apellido = apellido1_String + " " + apellido2_String;
                        String usuario_String = nombre1_String + "." + apellido1_String;

                        Usuario usuario = new Usuario();
                        usuario.setNombre(nombre);
                        usuario.setApellido(apellido);
                        usuario.setUsuario(usuario_String);
                        usuario.setPerfil(perfil);
                        usuario.setContraseña(password_String);

                        ingresarUsuario(usuario);
                    }

            }
        });








        return vista;



    }



    public void ingresarUsuario(Usuario usuario){




        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.4:8081/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(UsuariosService.class);

        Call<Void> call = service.addUsuario(usuario);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println("Usuario creado: "+response.message());
                Toast.makeText(getActivity(), "Usuario creado correctamente!", Toast.LENGTH_LONG).show();
                et1.setText("");
                et2.setText("");
                et3.setText("");
                et4.setText("");
                et5.setText("");
                et1.requestFocus();



            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println(t.getMessage());
                Toast.makeText(getActivity(), "Error!", Toast.LENGTH_LONG).show();


            }
        });
    }

}
