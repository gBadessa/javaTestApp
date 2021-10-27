package br.com.gbadessa.model.entities;

import java.time.LocalTime;

public class LogCorridaEntity {

    public LogCorridaEntity(LocalTime hora, String piloto, Integer numVolta, LocalTime tempoVolta, Double velocidadeMediaVolta) {
        this.hora = hora;
        this.piloto = piloto;
        this.numVolta = numVolta;
        this.tempoVolta = tempoVolta;
        VelocidadeMediaVolta = velocidadeMediaVolta;
    }

    private LocalTime hora;
    private String piloto;
    private Integer numVolta;
    private LocalTime tempoVolta;
    private Double VelocidadeMediaVolta;

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getPiloto() {
        return piloto;
    }

    public void setPiloto(String piloto) {
        this.piloto = piloto;
    }

    public Integer getNumVolta() {
        return numVolta;
    }

    public void setNumVolta(Integer numVolta) {
        this.numVolta = numVolta;
    }

    public LocalTime getTempoVolta() {
        return tempoVolta;
    }

    public void setTempoVolta(LocalTime tempoVolta) {
        this.tempoVolta = tempoVolta;
    }

    public Double getVelocidadeMediaVolta() {
        return VelocidadeMediaVolta;
    }

    public void setVelocidadeMediaVolta(Double velocidadeMediaVolta) {
        VelocidadeMediaVolta = velocidadeMediaVolta;
    }

    @Override
    public String toString() {
        return "" +
                "hora=" + hora +
                ", piloto='" + piloto + '\'' +
                ", numVolta=" + numVolta +
                ", tempoVolta=" + tempoVolta +
                ", VelocidadeMediaVolta=" + VelocidadeMediaVolta +
                "";
    }
}
