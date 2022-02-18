package com.example.proyecto_android;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface EventosClinicosService {

    String API_ROUTE = "Cliente_Proyecto/rest/eventosClinicos";

    @GET(API_ROUTE)
    Call<List<TieneEnfermedade>> getEnfermedades();
}
