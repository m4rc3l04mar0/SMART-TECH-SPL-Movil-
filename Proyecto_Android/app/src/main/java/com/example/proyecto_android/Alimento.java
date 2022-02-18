package com.example.proyecto_android;

import java.math.BigDecimal;

public class Alimento {

    private long idAlimento;
    private BigDecimal cantidad;
    private BigDecimal costoUnidad;
    private String nombre;
    private Consumo consumo;
    private Unidade unidade;

    public long getIdAlimento() {
        return idAlimento;
    }

    public void setIdAlimento(long idAlimento) {
        this.idAlimento = idAlimento;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getCostoUnidad() {
        return costoUnidad;
    }

    public void setCostoUnidad(BigDecimal costoUnidad) {
        this.costoUnidad = costoUnidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Consumo getConsumo() {
        return consumo;
    }

    public void setConsumo(Consumo consumo) {
        this.consumo = consumo;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }
}





