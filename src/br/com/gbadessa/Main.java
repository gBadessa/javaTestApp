package br.com.gbadessa;

import br.com.gbadessa.model.business.ILogCorridaBusiness;
import br.com.gbadessa.model.entities.LogCorridaEntity;
import br.com.gbadessa.ui.SplashScreenAppView;
import br.com.gbadessa.util.ArquivoUtil;
import br.com.gbadessa.util.ValidationResultUtil;

import java.io.IOException;
import java.util.List;

/**
 * Main
 * Classe Principal que inicializa a Aplicação
 */
public class Main {

    //region Atributos

    /**
     * AppContainer
     * Responsável por centralizar a criação dos objetos de negócio/modelo da aplicação
     */
    public static AppContainer appContainer;

    //endregion Atributos

    //region Construtores

    public static void main(String[] args) {
        //Cria instância do objeto "container"
        appContainer = new AppContainer();

        // Executa "Splash Screen" do sistema, responsável por alocar os recursos e iniciar a tela de Execução da aplicação
        new SplashScreenAppView();

        // Método de teste executado a partir da classe principal (mantido aqui apenas como "parte" do desenvolvimento)
        // Não será executado pela aplicação e não está em um "padrão" de desenvolvimento
        // Foi utilizado apenas no processo de desenvolvimento
        //executaTesteMain();
    }

    //endregion Construtores

    //region Métodos

    /**
     * executaTesteMain
     * Responsável por executar testes inicias ao longo do desenvolvimento (NÃO É UTILIZADO PELA APLICAÇÃO)
     */
    private static void executaTesteMain(){
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
            List<LogCorridaEntity> logCorridaEntities = appContainer.logCorridaBusiness.geraListaLogsCorrida(ArquivoUtil.getLinhasArquivo(ILogCorridaBusiness.NOME_ARQUIVO_LOG));
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

    //endregion Métodos
}


//region Atributos
//endregion Atributos

//region Métodos
//endregion Métodos