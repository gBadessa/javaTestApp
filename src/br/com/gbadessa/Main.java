package br.com.gbadessa;

import br.com.gbadessa.model.entities.LogCorridaEntity;
import br.com.gbadessa.util.ArquivoUtil;
import br.com.gbadessa.util.StringUtil;
import br.com.gbadessa.util.TimeUtil;
import br.com.gbadessa.util.ValidationResultUtil;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static AppContainer appContainer;

    public static void main(String[] args) {
        System.out.println("Iniciando" + StringUtil.appName);
        //
        appContainer = new AppContainer();

        //Teste seta valor repositorio
        ValidationResultUtil validationResult = appContainer.logCorridaBusiness.carregaLogsCorridaMemoria();
        if(appContainer.logCorridaBusiness.carregaLogsCorridaMemoria().isFalha()){
            System.out.println(validationResult.getResultMensagem());
            return;
        }
        else
            System.out.println("sucesso teste");

        try {
            //teste imprime log
            System.out.println("...");
            System.out.println("Teste imprime log");
            System.out.println("...");
            List<LogCorridaEntity> logCorridaEntities = appContainer.logCorridaBusiness.geraListaLogsCorrida(ArquivoUtil.getLinhasArquivoLog());
            logCorridaEntities.forEach(result -> System.out.println((result.toString())));

        } catch (IOException e) {
            e.printStackTrace();
        }

        //teste imprime log do repositorio
        System.out.println("...");
        System.out.println("Teste imprime log do repositorio");
        System.out.println("...");
        List<LogCorridaEntity> logCorridaEntities = appContainer.logCorridaBusiness.getListaRepositorio();
        logCorridaEntities.forEach(result -> System.out.println((result.toString())));

        //Teste busca posicao final dos pilotos na corrida
        System.out.println("...");
        System.out.println("Teste busca posicao final dos pilotos na corrida");
        System.out.println("...");
        List<LogCorridaEntity> listPosicaoChegadaPilotos = appContainer.logCorridaBusiness.getPosicaoChegadaPilotos();
        listPosicaoChegadaPilotos.forEach(result -> System.out.println((result.toString())));

    }
}