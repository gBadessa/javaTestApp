package br.com.gbadessa.model.repositories;

import br.com.gbadessa.model.entities.LogCorridaEntity;

import java.util.List;

/**
 * ILogCorridaRepository
 * Interface que define os métodos de inserção/busca no repositório LogCorrida
 */
public interface ILogCorridaRepository {
    /**
     * setLogsCorrida
     * Definicao da assinatura do método responsável pela "gravação" (alocados em memória) dos objetos no repositório
     * @param listEntity - List<LogCorridaEntity> - Lista de objetos da entidade LogCorrida que serão alocados em memória
     * @return boolean - Retorna verdadeiro/falso
     */
    boolean setLogsCorrida(List<LogCorridaEntity> listEntity);

    /**
     * getLogsCorrida
     * Definicao da assinatura do método responsável pela busca dos objetos no repositório
     * @return List<LogCorridaEntity> - Retorna lista de objetos da entidade LogCorrida
     */
    List<LogCorridaEntity> getLogsCorrida();
}
