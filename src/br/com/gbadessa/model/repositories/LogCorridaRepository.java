package br.com.gbadessa.model.repositories;

import br.com.gbadessa.model.entities.LogCorridaEntity;
import br.com.gbadessa.util.ListFilterUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogCorridaRepository implements ILogCorridaRepository{

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
        return listLogsCorrida;
    }

    @Override
    public List<LogCorridaEntity> getPosicaoChegadaPilotos() {
        List<LogCorridaEntity> listaChegada = getResultadoCorrida();
        return listaChegada.stream()
                .filter(ListFilterUtil.distinctByKey(LogCorridaEntity::getPiloto))
                .collect(Collectors.toList());
    }

    private List<LogCorridaEntity> getResultadoCorrida() {
        //Recupera lista de logs do repositório (lista estatica carregada)
        List<LogCorridaEntity> listaOrdenada = getLogsCorrida();

        //Oderna lista para identificar a ordem final de chegada dos pilotos
        listaOrdenada.sort((o1, o2) -> {
            int comparacaoResult = 0;

            //Compara e ordena pelo n° Voltas (Descendente)
            comparacaoResult = o2.getNumVolta().compareTo(o1.getNumVolta());

            //Se n° Volta são iguais, desempata pela Hora
            if (comparacaoResult == 0) {
                //
                //Compara pela hora (Ascendente)
                comparacaoResult = o1.getHora().compareTo(o2.getHora());
            }
            //
            return comparacaoResult;
        });
        //
        return listaOrdenada;
    }
}
