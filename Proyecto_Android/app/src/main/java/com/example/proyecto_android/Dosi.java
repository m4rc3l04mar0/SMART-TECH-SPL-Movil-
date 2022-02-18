package com.example.proyecto_android;

import java.math.BigDecimal;

public class Dosi {

    DosiPK dosipk;
    private BigDecimal cantidad;
    private Ternera ternera;


    public DosiPK getDosipk() {
        return dosipk;
    }

    public void setDosipk(DosiPK dosipk) {
        this.dosipk = dosipk;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public Ternera getTernera() {
        return ternera;
    }

    public void setTernera(Ternera ternera) {
        this.ternera = ternera;
    }
}
