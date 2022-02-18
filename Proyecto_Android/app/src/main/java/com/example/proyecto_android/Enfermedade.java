package com.example.proyecto_android;


import java.util.List;

public class Enfermedade {


    private long idEnfermedad;
    private NombreEnfermedad nombre;
    private List<TieneEnfermedade> tieneEnfermedades;

    public long getIdEnfermedad() {
        return idEnfermedad;
    }

    public void setIdEnfermedad(long idEnfermedad) {
        this.idEnfermedad = idEnfermedad;
    }

    public NombreEnfermedad getNombre() {
        return nombre;
    }

    public void setNombre(NombreEnfermedad nombre) {
        this.nombre = nombre;
    }

    public List<TieneEnfermedade> getTieneEnfermedades() {
        return tieneEnfermedades;
    }

    public void setTieneEnfermedades(List<TieneEnfermedade> tieneEnfermedades) {
        this.tieneEnfermedades = tieneEnfermedades;
    }

}
