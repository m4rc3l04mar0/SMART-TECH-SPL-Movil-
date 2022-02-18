package com.example.proyecto_android.personal;

import java.util.List;

public class Guachera {

    private String idGuachera;
    private List<Ternera> terneras;
    private List<Usuario> usuarios;

    public String getIdGuachera() {
        return idGuachera;
    }

    public void setIdGuachera(String idGuachera) {
        this.idGuachera = idGuachera;
    }

    public List<Ternera> getTerneras() {
        return terneras;
    }

    public void setTerneras(List<Ternera> terneras) {
        this.terneras = terneras;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
