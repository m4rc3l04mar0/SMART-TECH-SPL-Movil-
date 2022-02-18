package com.example.proyecto_android;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UsuariosService {

    String API_ROUTE = "Cliente_Proyecto/rest/usuarios";

    @GET(API_ROUTE)
    Call<List<Usuario>> getUsuarios();



    @DELETE (API_ROUTE+"/eliminar/{idUsuario}")
    Call<Void> delUsuario(@Path("idUsuario") long idUsuario);


    @POST(API_ROUTE+"/agregar/")
    Call<Void> addUsuario(@Body Usuario usuario);


    @PUT(API_ROUTE+"/modificar/{idUsuario}")
    Call<Void> updateUsuario(@Path("idUsuario") long idUsuario, @Body Usuario usuario);



   @GET(API_ROUTE+"/login/{usuario}/{contrasenia}")
   Call<String> loginUsuario(@Path("usuario")String usuario, @Path("contrasenia")String contrasenia);


}
