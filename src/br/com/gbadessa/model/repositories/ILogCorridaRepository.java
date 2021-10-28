package br.com.gbadessa.model.repositories;

import br.com.gbadessa.model.entities.LogCorridaEntity;

import java.util.List;

public interface ILogCorridaRepository {
    boolean setLogsCorrida(List<LogCorridaEntity> listEntity);
    List<LogCorridaEntity> getLogsCorrida();
}
