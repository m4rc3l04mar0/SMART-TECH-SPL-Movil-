package com.example.proyecto_android.personal;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UnidadesService {
    String API_ROUTE = "Cliente_Proyecto/rest/unidades";

    @GET(API_ROUTE+"/id/{idUnidad}")
    Call<Unidade> buscarUnidadPorId(@Path("IdUnidad") Long idUnidad);
}
