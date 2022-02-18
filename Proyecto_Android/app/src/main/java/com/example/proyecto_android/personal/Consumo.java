package com.example.proyecto_android.personal;

import java.math.BigDecimal;

public class Consumo {

    ConsumoPk id;
    private Alimento alimento;
    private Ternera ternera;
    private BigDecimal cantidad;

    public ConsumoPk getConsumopk() {
        return id;
    }

    public void setConsumopk(ConsumoPk consumopk) {
        this.id = consumopk;
    }

    public Alimento getAlimento() {
        return alimento;
    }

    public void setAlimento(Alimento alimento) {
        this.alimento = alimento;
    }

    public Ternera getTernera() {
        return ternera;
    }

    public void setTernera(Ternera ternera) {
        this.ternera = ternera;
    }


    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public long getTerneraId(){
        return getConsumopk().getIdTernera();
    }

    public long getAlimentoId(){
        return getConsumopk().getIdAlimento();
    }


}
