package com.example.proyecto_android;

import java.math.BigDecimal;
import java.util.List;

public class Madre {
    private long idMadre;
    private BigDecimal nroCaravana;
    private List<Ternera> terneras;

    public long getIdMadre() {
        return idMadre;
    }

    public void setIdMadre(long idMadre) {
        this.idMadre = idMadre;
    }

    public BigDecimal getNroCaravana() {
        return nroCaravana;
    }

    public void setNroCaravana(BigDecimal nroCaravana) {
        this.nroCaravana = nroCaravana;
    }

    public List<Ternera> getTerneras() {
        return terneras;
    }

    public void setTerneras(List<Ternera> terneras) {
        this.terneras = terneras;
    }
}
