package com.example.proyecto_android;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TernerasService {

    String API_ROUTE = "Cliente_Proyecto/rest/terneras/";

    @GET(API_ROUTE)
    Call<List<Ternera>> getTerneras();
}
