package br.com.gbadessa;

import br.com.gbadessa.model.business.ILogCorridaBusiness;
import br.com.gbadessa.model.business.LogCorridaBusiness;
import br.com.gbadessa.model.repositories.ILogCorridaRepository;
import br.com.gbadessa.model.repositories.LogCorridaRepository;

public class AppContainer {

    ILogCorridaBusiness logCorridaBusiness;

    public AppContainer() {
        ILogCorridaRepository logCorridaRepository = new LogCorridaRepository();
        logCorridaBusiness = new LogCorridaBusiness(logCorridaRepository);
    }
}
