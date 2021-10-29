package br.com.gbadessa.model.business;

import br.com.gbadessa.model.entities.LogCorridaEntity;
import br.com.gbadessa.model.viewobjecties.LogCorridaViewObject;
import br.com.gbadessa.util.ValidationResultUtil;

import java.util.List;

/**
 * ILogCorridaBusiness
 * Interface que define os métodos de negócio de LogCorrida
 */
public interface ILogCorridaBusiness {

    /**
     * Definição do nome do arquivo de LOG
     */
    String NOME_ARQUIVO_LOG = "log.txt";

    /**
     * geraListaLogsCorrida
     * Definicao da assinatura do método resonsável pela geração da lista de Logs da Corrida
     * @param listLinhasArquivoLog - List<String> - Lista de Strings do arquivo lido
     * @return List<LogCorridaEntity> - Lista de objetos da entidade LogCorrida
     */
    List<LogCorridaEntity> geraListaLogsCorrida(List<String> listLinhasArquivoLog);

    /**
     * carregaLogsCorridaMemoria
     * Definicao da assinatura do método responsável pela Leitura de arquivo Log e por alocar seu conteúdo em memória
     * @return ValidationResultUtil - Objeto validador que indica sucesso ou falha
     */
    ValidationResultUtil carregaLogsCorridaMemoria();

    /**
     * getListaRepositorio
     * Definicao da assinatura do método responsável pela busca dos objetos da entidade LogCorrida alocados em memória no reposítório
     * @return List<LogCorridaEntity> - Lista de objetos da entidade LogCorrida
     */
    List<LogCorridaEntity> getListaRepositorio();

    /**
     * getPosicaoChegadaPilotos
     * Definicao da assinatura do método responsável por buscar do respositório, a lista tratada por ordem de chegada dos pilotos
     * @return List<LogCorridaEntity> - Lista de objetos da entidade LogCorrida
     */
    List<LogCorridaEntity> getPosicaoChegadaPilotos();

    /**
     * getResultados
     * Definicao da assinatura do método responsável por buscar resultado final que será exibido
     * @return List<LogCorridaViewObject> - Lista de objetos de visualização LogCorrida
     */
    List<LogCorridaViewObject> getResultados();
}
