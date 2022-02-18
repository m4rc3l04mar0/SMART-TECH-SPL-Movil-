package com.example.proyecto_android;

import java.math.BigDecimal;
import java.util.List;

public class Padre {

    private long idPadre;
    private BigDecimal nroCaravana;
    private List<Ternera> terneras;

    public long getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(long idPadre) {
        this.idPadre = idPadre;
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
