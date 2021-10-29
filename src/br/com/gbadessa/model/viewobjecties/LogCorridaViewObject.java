package br.com.gbadessa.model.viewobjecties;

import java.time.LocalTime;

/**
 * LogCorridaViewObject
 * Classe que representa uma variação do objeto LogCorridaEntity que será "apresentado" para visualizacao
 */
public class LogCorridaViewObject {

    //region Construtor

    public LogCorridaViewObject(Integer posicaoChegada, String codPiloto, String nomePiloto, Integer numVoltasCompletadas, LocalTime tempoTotalProva, LocalTime melhorVoltaPiloto, Boolean isMelhorVoltaCorrida, Double velocidadeMediaPiloto, LocalTime tempoAposVencedor) {
        this.posicaoChegada = posicaoChegada;
        this.codPiloto = codPiloto;
        this.nomePiloto = nomePiloto;
        this.numVoltasCompletadas = numVoltasCompletadas;
        this.tempoTotalProva = tempoTotalProva;
        this.melhorVoltaPiloto = melhorVoltaPiloto;
        this.isMelhorVoltaCorrida = isMelhorVoltaCorrida;
        this.velocidadeMediaPiloto = velocidadeMediaPiloto;
        this.tempoAposVencedor = tempoAposVencedor;
    }

    //endregion Construtor

    //region Atributos

    private Integer posicaoChegada;
    private String codPiloto;
    private String nomePiloto;
    private Integer numVoltasCompletadas;
    private LocalTime tempoTotalProva;
    //
    private LocalTime melhorVoltaPiloto;
    private Boolean isMelhorVoltaCorrida;
    private Double velocidadeMediaPiloto;
    private LocalTime tempoAposVencedor;

    //endregion Atributos

    //region Getter's/ Setter's

    public Integer getPosicaoChegada() {
        return posicaoChegada;
    }

    public void setPosicaoChegada(Integer posicaoChegada) {
        this.posicaoChegada = posicaoChegada;
    }

    public String getCodPiloto() {
        return codPiloto;
    }

    public void setCodPiloto(String codPiloto) {
        this.codPiloto = codPiloto;
    }

    public String getNomePiloto() {
        return nomePiloto;
    }

    public void setNomePiloto(String nomePiloto) {
        this.nomePiloto = nomePiloto;
    }

    public Integer getNumVoltasCompletadas() {
        return numVoltasCompletadas;
    }

    public void setNumVoltasCompletadas(Integer numVoltasCompletadas) {
        this.numVoltasCompletadas = numVoltasCompletadas;
    }

    public LocalTime getTempoTotalProva() {
        return tempoTotalProva;
    }

    public void setTempoTotalProva(LocalTime tempoTotalProva) {
        this.tempoTotalProva = tempoTotalProva;
    }

    public LocalTime getMelhorVoltaPiloto() {
        return melhorVoltaPiloto;
    }

    public void setMelhorVoltaPiloto(LocalTime melhorVoltaPiloto) {
        this.melhorVoltaPiloto = melhorVoltaPiloto;
    }

    public Boolean getMelhorVoltaCorrida() {
        return isMelhorVoltaCorrida;
    }

    public void setMelhorVoltaCorrida(Boolean melhorVoltaCorrida) {
        isMelhorVoltaCorrida = melhorVoltaCorrida;
    }

    public Double getVelocidadeMediaPiloto() {
        return velocidadeMediaPiloto;
    }

    public void setVelocidadeMediaPiloto(Double velocidadeMediaPiloto) {
        this.velocidadeMediaPiloto = velocidadeMediaPiloto;
    }

    public LocalTime getTempoAposVencedor() {
        return tempoAposVencedor;
    }

    public void setTempoAposVencedor(LocalTime tempoAposVencedor) {
        this.tempoAposVencedor = tempoAposVencedor;
    }

    //endregion //region Getter's/ Setter's

    //region toString

    @Override
    public String toString() {
        return "DetalheCorridaEntity{" +
                "posicaoChegada=" + posicaoChegada +
                ", codPiloto='" + codPiloto + '\'' +
                ", nomePiloto='" + nomePiloto + '\'' +
                ", numVoltasCompletadas=" + numVoltasCompletadas +
                ", tempoTotalProva=" + tempoTotalProva +
                ", melhorVoltaPiloto=" + melhorVoltaPiloto +
                ", isMelhorVoltaCorrida=" + isMelhorVoltaCorrida +
                ", velocidadeMediaPiloto=" + velocidadeMediaPiloto +
                ", tempoAposVencedor=" + tempoAposVencedor +
                '}';
    }

    //endregion toString
}
