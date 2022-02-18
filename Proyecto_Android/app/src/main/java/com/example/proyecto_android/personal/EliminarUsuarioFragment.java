package com.example.proyecto_android.personal;

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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto_android.R;
import com.example.proyecto_android.Usuario;
import com.example.proyecto_android.UsuariosService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class EliminarUsuarioFragment extends Fragment {

    private UsuariosService service;
    private Spinner sp_idUsuarios;
    private Button btnEliminar;
    private TextView textViewResult;
    ArrayAdapter adapter_idUsuarios;
    String content = "";
    List<Usuario> idUsuarios;
    ArrayList<Long> combo = new ArrayList<>();
    ListView lvIdUsuarios;
    String numeroString;
    int numeroInt;
    TextView textView;
    Long numeroLong;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Eliminar Usuario");


        View vista = inflater.inflate(R.layout.fragment_eliminarusuario, container, false);

        sp_idUsuarios = (Spinner) vista.findViewById(R.id.sp_Id_EU);



        btnEliminar = (Button) vista.findViewById(R.id.btn_Eliminar);
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Seguro que quieres eliminar el usuario?");
                builder.setTitle("Confirmar Eliminar");
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delUsuario();
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


    public void delUsuario() {


        Call<Void> call = service.delUsuario(combo.get(numeroInt));


        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getActivity(), "Usuario eliminado Correctamente", Toast.LENGTH_SHORT).show();
                   EliminarUsuarioFragment fragment = new EliminarUsuarioFragment();
                   getFragmentManager().beginTransaction().replace(R.id.fragment_container, new EliminarUsuarioFragment()).commit();


            }


            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getActivity(), "Error al eliminar Usuario", Toast.LENGTH_SHORT).show();


            }
        });




    }
}