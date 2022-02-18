package com.example.proyecto_android.personal;

import java.util.Date;

public class TieneEnfermedade {
    private static final long serialVerionUID = 1L;

    TieneEnfermedadePK tieneEnfermedadePK;

    private Date fecHasta;
    private String observacion;


    private Enfermedade enfermedade;
    private Ternera ternera;


    public TieneEnfermedadePK getTieneEnfermedadePK() {
        return tieneEnfermedadePK;
    }

    public void setTieneEnfermedadePK(TieneEnfermedadePK tieneEnfermedadePK) {
        this.tieneEnfermedadePK = tieneEnfermedadePK;
    }

    public Date getFecHasta() {
        return fecHasta;
    }

    public void setFecHasta(Date fecHasta) {
        this.fecHasta = fecHasta;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Enfermedade getEnfermedade() {
        return enfermedade;
    }

    public void setEnfermedade(Enfermedade enfermedade) {
        this.enfermedade = enfermedade;
    }

    public Ternera getTernera() {
        return ternera;
    }

    public void setTernera(Ternera ternera) {
        this.ternera = ternera;
    }


    public static long getSerialVerionUID(){
        return serialVerionUID;
    }





}
