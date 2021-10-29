package br.com.gbadessa;

import br.com.gbadessa.model.business.ILogCorridaBusiness;
import br.com.gbadessa.model.business.LogCorridaBusiness;
import br.com.gbadessa.model.repositories.ILogCorridaRepository;
import br.com.gbadessa.model.repositories.LogCorridaRepository;

/**
 * AppContainer
 * Classe responsável por centralizar a criação dos objetos de negócio/modelo da aplicação
 */
public class AppContainer {

    //region Atributos

    /**
     * Declaração da Interface dos Objetos de negócio (Business)
     * Caso existam novos Objetos de negócio, devem ser inseridas aqui
     */
    ILogCorridaBusiness logCorridaBusiness;

    //region Getter's

    public ILogCorridaBusiness getLogCorridaBusiness() {
        return logCorridaBusiness;
    }

    //endregion Getter's

    //endregion Atributos

    //region Construtores

    /**
     * AppContainer
     * Construtor é reponsável por chamar método de inicializaçõd dos objetos
     */
    public AppContainer() {
        inicializaObjetos();
    }

    //endregion Construtores

    //region Métodos

    /**
     * Inicializa objetos de negócio (imutáveis) apenas ao iniciar a aplicação,
     * "injetando" as dependências necessárias via construtor, afim de garantir maior manutenibilidade e organização do código.
     */
    public void inicializaObjetos()
    {
        ILogCorridaRepository logCorridaRepository = new LogCorridaRepository();
        logCorridaBusiness = new LogCorridaBusiness(logCorridaRepository);
    }

    //endregion Métodos
}
