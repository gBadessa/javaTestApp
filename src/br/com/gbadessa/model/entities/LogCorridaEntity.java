package br.com.gbadessa.model.entities;

import java.time.LocalTime;

/**
 * LogCorridaEntity
 * Classe que representa uma entidade do sistema relacionada ao Log da Corrida
 */
public class LogCorridaEntity {

    //region Construtor

    public LogCorridaEntity(LocalTime hora, String piloto, Integer numVolta, LocalTime tempoVolta, Double velocidadeMediaVolta) {
        this.hora = hora;
        this.piloto = piloto;
        this.numVolta = numVolta;
        this.tempoVolta = tempoVolta;
        VelocidadeMediaVolta = velocidadeMediaVolta;
    }

    //endregion Construtor

    //region Atributos

    private LocalTime hora;
    private String piloto;
    private Integer numVolta;
    private LocalTime tempoVolta;
    private Double VelocidadeMediaVolta;

    //endregion Atributos

    //region Getter's/ Setter's

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

    //endregion //region Getter's/ Setter's

    //region toString

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

    //endregion toString
}
