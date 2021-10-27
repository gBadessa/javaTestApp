package br.com.gbadessa.model.business;

import br.com.gbadessa.model.entities.LogCorridaEntity;
import br.com.gbadessa.util.ArquivoUtil;

import java.util.List;

public interface ILogCorridaBusiness {
    List<LogCorridaEntity> geraListaLogsCorrida(List<String> listLinhasArquivoLog);
}
