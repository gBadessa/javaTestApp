package br.com.gbadessa;

import br.com.gbadessa.model.entities.LogCorridaEntity;
import br.com.gbadessa.util.ArquivoUtil;
import br.com.gbadessa.util.StringUtil;
import br.com.gbadessa.util.TimeUtil;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static AppContainer appContainer;

    public static void main(String[] args) {
        System.out.println("Iniciando" + StringUtil.appName);
        //
        try {

            appContainer = new AppContainer();
            List<LogCorridaEntity> logCorridaEntities = appContainer.logCorridaBusiness.geraListaLogsCorrida(ArquivoUtil.getLinhasArquivoLog());
            logCorridaEntities.forEach(result -> System.out.println((result.toString())));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}