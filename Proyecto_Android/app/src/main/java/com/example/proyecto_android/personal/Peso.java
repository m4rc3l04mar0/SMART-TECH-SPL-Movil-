package com.example.proyecto_android.personal;

import java.math.BigDecimal;

public class Peso {

    private long idPeso;
    private String fecha;
    private BigDecimal peso;
    private TipoRegistro tipoRegistro;
    private Ternera ternera;

    public long getIdPeso() {
        return idPeso;
    }

    public void setIdPeso(long idPeso) {
        this.idPeso = idPeso;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public TipoRegistro getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(TipoRegistro tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public Ternera getTernera() {
        return ternera;
    }

    public void setTernera(Ternera ternera) {
        this.ternera = ternera;
    }
}
