package br.com.gbadessa.model.repositories;

import br.com.gbadessa.model.entities.LogCorridaEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * LogCorridaRepository
 * Representa o Repositório de Log Corrida
 * A idéia aqui foi que a classe de negócio da aplicação (LogCorridaBusiness) trate o arquivo log e passe para o repositório uma lista de Entidades
 * Na implementação do Reposítório, ao receber os dados tratados, poderia gravar os mesmos em uma tabela do sistema, enviar os dados via API, gravar em arquivo json, xml, etc.
 * A intenção aqui foi apenas "alocar" as informações em memória e sempre que a classe de negócio necessitar, realiza a solicitação ao repositório
 * sem ter o conhecimento de como o repositório fará para retornar a solicitação (acesso a bd, arquivos, api ou simpleste a lista em memória no caso do nosso app)
 *
 */
public class LogCorridaRepository implements ILogCorridaRepository{

    /**
     * listLogsCorrida
     * Lista estática que armazena em memória os logs da corrida
     */
    private static List<LogCorridaEntity> listLogsCorrida;
    //
    @Override
    public boolean setLogsCorrida(List<LogCorridaEntity> listEntity) {
        try {
            listLogsCorrida = listEntity;
            return true;
        } catch (Exception ex) {
            listLogsCorrida = new ArrayList<>();
            return false;
        }
    }

    @Override
    public List<LogCorridaEntity> getLogsCorrida() {
        /*
        Retorna lista alocada em  memória pois não foi objetivo nesse momento implementar a busca dessa informação de outra maneira.
        Contudo, no contexto da aplicação, se necessário buscar a informação de algum outro lugar, pode-se facilmente implementar
        no repositório a requisção a um banco de dados, arquivo de texto, chamada de uma API, ect, demonstrando a flexibilidade da estrutura
        em possível alterações do projeto
         */
        return listLogsCorrida;
    }
}
