package com.example.proyecto_android.personal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface EventosClinicosService {

    String API_ROUTE = "Cliente_Proyecto/rest/eventosClinicos";

    @GET(API_ROUTE)
    Call<List<TieneEnfermedade>> getEnfermedades();
}
