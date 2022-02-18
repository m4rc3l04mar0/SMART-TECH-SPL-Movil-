package com.example.proyecto_android.personal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface AlimentosService {

    String API_ROUTE = "Cliente_Proyecto/rest/alimentos/";

    @GET(API_ROUTE)
    Call<List<Alimento>> getAlimentos();



    @POST(API_ROUTE+"agregar/")
    Call<Void> addAlimento(@Body Alimento alimento);


    @PUT(API_ROUTE+"modificar/")
    Call<Void> updateAlimento(@Body Alimento alimento);

}

