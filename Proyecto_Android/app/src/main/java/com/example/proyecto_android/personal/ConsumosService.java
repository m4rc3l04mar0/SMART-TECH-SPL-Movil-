package com.example.proyecto_android.personal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ConsumosService {

    String API_ROUTE = "Cliente_Proyecto/rest/consumos/";

    @GET(API_ROUTE)
    Call<List<Consumo>> getConsumos();



    @POST(API_ROUTE)
    Call<Void> addConsumo(@Body Consumo consumo);
}
