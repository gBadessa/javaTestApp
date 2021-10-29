package br.com.gbadessa.model.repositories;

import br.com.gbadessa.model.entities.LogCorridaEntity;
import br.com.gbadessa.util.ListFilterUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
