package com.example.proyecto_android.personal;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Ternera {
    private long idTernera;
    private String causaMuerte;
    private Date fecBaja;
    private Date fecMuerte;
    private Date fecNacimiento;
    private String motivoBaja;
    private BigDecimal nroCaravana;
    private TipoParto parto;
    private BigDecimal pesoNacimeinto;
    private Raza raza;
    private List<Consumo> consumos;
    private List<Dosi> dosis;
    private List<Peso> pesos;
    private Guachera guachera;
    private Madre madre;
    private Padre padre;
    private List<TieneEnfermedade> tieneEnfermedades;

    public long getIdTernera() {
        return idTernera;
    }

    public void setIdTernera(long idTernera) {
        this.idTernera = idTernera;
    }

    public String getCausaMuerte() {
        return causaMuerte;
    }

    public void setCausaMuerte(String causaMuerte) {
        this.causaMuerte = causaMuerte;
    }

    public Date getFecBaja() {
        return fecBaja;
    }

    public void setFecBaja(Date fecBaja) {
        this.fecBaja = fecBaja;
    }

    public Date getFecMuerte() {
        return fecMuerte;
    }

    public void setFecMuerte(Date fecMuerte) {
        this.fecMuerte = fecMuerte;
    }

    public Date getFecNacimiento() {
        return fecNacimiento;
    }

    public void setFecNacimiento(Date fecNacimiento) {
        this.fecNacimiento = fecNacimiento;
    }

    public String getMotivoBaja() {
        return motivoBaja;
    }

    public void setMotivoBaja(String motivoBaja) {
        this.motivoBaja = motivoBaja;
    }

    public BigDecimal getNroCaravana() {
        return nroCaravana;
    }

    public void setNroCaravana(BigDecimal nroCaravana) {
        this.nroCaravana = nroCaravana;
    }

    public TipoParto getParto() {
        return parto;
    }

    public void setParto(TipoParto parto) {
        this.parto = parto;
    }

    public BigDecimal getPesoNacimeinto() {
        return pesoNacimeinto;
    }

    public void setPesoNacimeinto(BigDecimal pesoNacimeinto) {
        this.pesoNacimeinto = pesoNacimeinto;
    }

    public Raza getRaza() {
        return raza;
    }

    public void setRaza(Raza raza) {
        this.raza = raza;
    }

    public List<Consumo> getConsumos() {
        return consumos;
    }

    public void setConsumos(List<Consumo> consumos) {
        this.consumos = consumos;
    }

    public List<Dosi> getDosis() {
        return dosis;
    }

    public void setDosis(List<Dosi> dosis) {
        this.dosis = dosis;
    }

    public List<Peso> getPesos() {
        return pesos;
    }

    public void setPesos(List<Peso> pesos) {
        this.pesos = pesos;
    }

    public Guachera getGuachera() {
        return guachera;
    }

    public void setGuachera(Guachera guachera) {
        this.guachera = guachera;
    }

    public Madre getMadre() {
        return madre;
    }

    public void setMadre(Madre madre) {
        this.madre = madre;
    }

    public Padre getPadre() {
        return padre;
    }

    public void setPadre(Padre padre) {
        this.padre = padre;
    }

    public List<TieneEnfermedade> getTieneEnfermedades() {
        return tieneEnfermedades;
    }

    public void setTieneEnfermedades(List<TieneEnfermedade> tieneEnfermedades) {
        this.tieneEnfermedades = tieneEnfermedades;
    }
}
