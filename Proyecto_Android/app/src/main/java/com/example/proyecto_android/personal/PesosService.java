package com.example.proyecto_android.personal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PesosService {

    String API_ROUTE = "Cliente_Proyecto/rest/pesos/";

    @GET(API_ROUTE)
    Call<List<Peso>> getPesos();



    @POST(API_ROUTE)
    Call<Void> addPeso(@Body Peso peso);
}
