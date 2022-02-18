package com.example.proyecto_android;

import java.util.Date;

public class TieneEnfermedadePK {



    private long idEnfermedad;
    private long idTernera;
    private Date fecDesde;


    public long getIdEnfermedad() {
        return idEnfermedad;
    }

    public void setIdEnfermedad(long idEnfermedad) {
        this.idEnfermedad = idEnfermedad;
    }

    public long getIdTernera() {
        return idTernera;
    }

    public void setIdTernera(long idTernera) {
        this.idTernera = idTernera;
    }

    public Date getFecDesde() {
        return fecDesde;
    }

    public void setFecDesde(Date fecDesde) {
        this.fecDesde = fecDesde;
    }

    private static final long serialVerionUID = 1L;

    public TieneEnfermedadePK(){
        super();
    }
}
