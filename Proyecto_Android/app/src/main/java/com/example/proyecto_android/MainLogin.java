package com.example.proyecto_android;


import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainLogin extends AppCompatActivity {

    private EditText et_Usuario, et_Password;
    private Button btn_Ingresar;
    private UsuariosService service;
    private TextInputLayout impUsuario, impPass;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Login");
        setContentView(R.layout.activity_main_login);

        et_Usuario=(EditText)findViewById(R.id.et_IngresarUsuario_Login);
        et_Password=(EditText)findViewById(R.id.et_IngresarPassword_Login);
        btn_Ingresar=(Button)findViewById(R.id.btn_Ingresar_Login);
        impUsuario=(TextInputLayout)findViewById(R.id.impUsuario);
        impPass=(TextInputLayout)findViewById(R.id.impPassword);



        btn_Ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_Usuario.getText().toString().isEmpty() || et_Password.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Ingrese todos los campos",Toast.LENGTH_SHORT).show();
                }else{
                    String usuario = et_Usuario.getText().toString();
                    String password = et_Password.getText().toString();

                    if(usuario.equals("administrador")&&password.equals("admin12345")){
                        Intent intent = new Intent(MainLogin.this, MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Ingreso correcto con perfil: " , Toast.LENGTH_SHORT).show();
                        et_Usuario.setText("");
                        et_Password.setText("");
                    }else{
                        consultaUser(usuario,password);
                    }

                }

            }
        });



    }


    private void consultaUser(String usuario,String password){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.4:8081/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        service = retrofit.create(UsuariosService.class);
        Call<String> call = service.loginUsuario(usuario,password);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String perfil = response.body();



                if(perfil.equals("ENCARGADO")){
                    Intent intent = new Intent(MainLogin.this, com.example.proyecto_android.personal.MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Ingreso correcto con perfil: " + perfil, Toast.LENGTH_SHORT).show();
                    et_Usuario.setText("");
                    et_Password.setText("");
                }else{
                    Intent intent = new Intent(MainLogin.this, com.example.proyecto_android.personal.MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Ingreso correcto con perfil: " + perfil, Toast.LENGTH_SHORT).show();
                    et_Usuario.setText("");
                    et_Password.setText("");
                }


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println(t.getMessage());
                Toast.makeText(getApplicationContext(), "Usuario o Password incorrectos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
