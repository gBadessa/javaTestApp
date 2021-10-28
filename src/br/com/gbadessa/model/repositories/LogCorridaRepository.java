package br.com.gbadessa.model.repositories;

import br.com.gbadessa.model.entities.LogCorridaEntity;

import java.util.ArrayList;
import java.util.List;

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
}
