package br.com.gbadessa.model.repositories;

import br.com.gbadessa.model.entities.LogCorridaEntity;

import java.util.List;

public interface ILogCorridaRepository {
    /**
     * setLogsCorrida
     * Definicao da assinatura do método responsável pela gravação dos objetos no repositório
     * @param listEntity
     * @return
     */
    boolean setLogsCorrida(List<LogCorridaEntity> listEntity);

    /**
     * getLogsCorrida
     * Definicao da assinatura do método responsável pela busca dos objetos no repositório
     * @return
     */
    List<LogCorridaEntity> getLogsCorrida();

    /**
     * getPosicaoChegadaPilotos
     * Definicao da assinatura do método responsável por retornar a lista distinta de pilotos na ordem de chegada
     * @return
     */
    List<LogCorridaEntity> getPosicaoChegadaPilotos();

}
