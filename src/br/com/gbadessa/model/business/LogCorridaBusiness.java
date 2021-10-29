package br.com.gbadessa.model.business;

import br.com.gbadessa.model.entities.LogCorridaEntity;
import br.com.gbadessa.model.repositories.ILogCorridaRepository;
import br.com.gbadessa.model.viewobjecties.LogCorridaViewObject;
import br.com.gbadessa.util.*;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * LogCorridaBusiness
 * Contém a implementação das regras de negócio relacionadas ao Log de Corrida
 * Recebe solicitação do viewModel e quando necessário realiza buscas do repositório para retornar para o viewModel os
 * dados tratados em um formato esperado
 */
public class LogCorridaBusiness implements ILogCorridaBusiness {

    /**
     * ILogCorridaRepository
     * Interface do repositório recebida via construtor no momento de sua criação
     */
    private final ILogCorridaRepository logCorridaRepository;

    /**
     * LogCorridaBusiness
     * Construtor da classe de negócio LogCorrida
     * @param logCorridaRepository - ILogCorridaRepository - Interface do repositório injetada via construtor
     */
    public LogCorridaBusiness(ILogCorridaRepository logCorridaRepository) {
        this.logCorridaRepository = logCorridaRepository;
    }

    @Override
    public List<LogCorridaEntity> geraListaLogsCorrida(List<String> listaLog) {
        //Lista de retorno
        List<LogCorridaEntity> listLogsCorrida = new ArrayList<>();

        //
        if (listaLog == null || listaLog.isEmpty()) {
            //Retonra lista vazia
            return listLogsCorrida;
        }

        //
        //remove primeira linha (cabeçalho) do log
        listaLog.remove(0);

        //Percorre linhas para criação da lista de objetos da entidade(LogCorridaEntity)
        for (String linha : listaLog) {
            // Hora	          Piloto	        N° Volta	Tempo Volta	    Velocidade média da volta
            // 23:49:08.277	  038 – F.MASSA	    1	        1:02.852	    44,275
            //

            /*
                Não estou verificando conteúdo null ou vazio para as "colunas", pois estou assumindo que as linhas do arquivo
                sempre estarão consistentes.
             */
            String[] splitTabulacao = linha.split("[\t]");
            String hora = splitTabulacao[0];
            String piloto = splitTabulacao[1];
            String numVolta = splitTabulacao[2];
            String tempoVolta = splitTabulacao[3];
            String VelocidadeMediaVolta = splitTabulacao[4];
            //
            listLogsCorrida.add(getLogCorrida(hora, piloto, numVolta, tempoVolta, VelocidadeMediaVolta));
        }
        //
        return listLogsCorrida;
    }

    @Override
    public ValidationResultUtil carregaLogsCorridaMemoria(){
        //
        try {
            logCorridaRepository.setLogsCorrida(geraListaLogsCorrida(ArquivoUtil.getLinhasArquivo(NOME_ARQUIVO_LOG)));
            return ValidationResultUtil.setSucesso();
        } catch (IOException e) {
            e.printStackTrace();
            return ValidationResultUtil.setFalha(e.getMessage());
        }
    }

    @Override
    public List<LogCorridaEntity> getListaRepositorio() {
        return logCorridaRepository.getLogsCorrida();
    }

    @Override
    public List<LogCorridaEntity> getPosicaoChegadaPilotos() {
        List<LogCorridaEntity> listaChegada = getResultadoCorrida();
        return listaChegada.stream()
                .filter(ListFilterUtil.distinctByKey(LogCorridaEntity::getPiloto))
                .collect(Collectors.toList());
    }

    @Override
    public List<LogCorridaViewObject> getResultados() {
        List<LogCorridaViewObject> listDetalhesCorrida = new ArrayList<>();

        //Recupera lista GERAL de logs da corrida do repositório
        List<LogCorridaEntity> listLogsCorrida = getListaRepositorio();

        //Recupera melhor volta da corrida
        LocalTime melhorVoltaCorrida = getMelhorVolta(listLogsCorrida);

        //Recupera lista distinta dos pilotos na posição de chegada no repositório
        List<LogCorridaEntity> listPosicaoChegada = getPosicaoChegadaPilotos();

        int i = 1;
        for (LogCorridaEntity it : listPosicaoChegada) {

            //Filtra lista de logs apenas buscando os dados de voltas do piloto
            List<LogCorridaEntity> listVoltasPiloto = getlistaFiltradaPiloto(listLogsCorrida, it.getPiloto());
            //
            Integer posicaoChegada = i;
            String codPiloto = it.getPiloto().split(ConstantesUtil.SEPARADOR_CODIGO_NOME_PILOTO)[0].trim();
            String nomePiloto = it.getPiloto().split(ConstantesUtil.SEPARADOR_CODIGO_NOME_PILOTO)[1].trim();
            Integer numVoltasCompletadas = it.getNumVolta();
            LocalTime tempoTotalProva = getTempoTotalProvaPiloto(listVoltasPiloto);
            LocalTime melhorVoltaPiloto = getMelhorVoltaPiloto(listVoltasPiloto);
            String isMelhorVoltaCorrida = isMelhorVoltaCorrida(melhorVoltaPiloto, melhorVoltaCorrida) ? StringResourcesUtil.Sim : StringResourcesUtil.Nao;
            Double velocidadeMediaPiloto = getVelocidadeMediaPiloto(listVoltasPiloto);
            LocalTime tempoAposVencedor = i == 1 ?
                    getTempoPilotoAtrasVencedor(tempoTotalProva, tempoTotalProva) :
                    getTempoPilotoAtrasVencedor(listDetalhesCorrida.get(0).getTempoTotalProva(), tempoTotalProva);
            //
            LogCorridaViewObject detalhe = new LogCorridaViewObject(posicaoChegada, codPiloto, nomePiloto, numVoltasCompletadas, tempoTotalProva, melhorVoltaPiloto, isMelhorVoltaCorrida, velocidadeMediaPiloto, tempoAposVencedor);
            listDetalhesCorrida.add(detalhe);
            i++;
        }
        //
        return listDetalhesCorrida;
    }

    /**
     * getLogCorrida
     * Método responsável pela crição e retorno da entidade LogCorridaEntity a partir dos parâmetros enviados
     * @param hora -  Parâmetro hora
     * @param piloto - Parâmetro piloto
     * @param numVolta - Parâmetro numVolta
     * @param tempoVolta - Parâmetro tempoVolta
     * @param velocidadeMediaVolta - Parâmetro velocidadeMediaVolta
     * @return LogCorridaEntity - Retorna objeto criado da entidade LogCorrida
     */
    private LogCorridaEntity getLogCorrida(String hora, String piloto, String numVolta, String tempoVolta, String velocidadeMediaVolta) {
        return new LogCorridaEntity(
                TimeUtil.convertLocalTimeString(hora),
                piloto,
                Integer.parseInt(numVolta),
                TimeUtil.convertLocalTimeString(tempoVolta),
                Double.valueOf(velocidadeMediaVolta.replace(",", "."))
        );
    }

    /**
     * getResultadoCorrida
     * Método responsável por retonrar lista ordenada da corrida
     * @return List<LogCorridaEntity> - Retorna lista tratada de objetos da entidade LogCorrida
     */
    private List<LogCorridaEntity> getResultadoCorrida() {
        //Recupera lista de logs do repositório (lista estatica carregada)
        List<LogCorridaEntity> listaOrdenada = getListaRepositorio();

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

    /**
     * getlistaFiltradaPiloto
     * Método responsável por filtrar lista de Log da Corrida extraindo os dados relacionados ao piloto
     * @param listLogsCorrida - List<LogCorridaEntity> - Lista de objetos da entidade LogCorrida para aplicar filtro
     * @param piloto - String - Piloto utilizado no filtro
     * @return List<LogCorridaEntity> - Retorna lista filtrada de objetos da entidade LogCorrida
     */
    private List<LogCorridaEntity> getlistaFiltradaPiloto(List<LogCorridaEntity> listLogsCorrida, String piloto){
        //Filtra lista de logs apenas buscando os dados de voltas do piloto
        return listLogsCorrida.stream().filter(log -> log.getPiloto().equals(piloto)).collect(Collectors.toList());
    }

    /**
     * getTempoTotalProvaPiloto
     * Método responsável por buscar o tempo total de prova do piloto
     * @param listaFiltradaPiloto - List<LogCorridaEntity> - Lista de objetos da entidade LogCorrida relacionados ao piloto
     * @return LocalTime - Retorna objeto LocalTime
     */
    private LocalTime getTempoTotalProvaPiloto(List<LogCorridaEntity> listaFiltradaPiloto) {
        List<LocalTime> listTempoVolta = new ArrayList<>();
        listaFiltradaPiloto.forEach(logCorridaEntity -> listTempoVolta.add(logCorridaEntity.getTempoVolta()));
        return TimeUtil.somaLocalTime(listTempoVolta);
    }

    /**
     * getMelhorVoltaPiloto
     * Método responsável por buscar a melhor volta do piloto
     * @param listaFiltradaPiloto - List<LogCorridaEntity> - Lista de objetos da entidade LogCorrida relacionados ao piloto
     * @return LocalTime - Retorna objeto LocalTime
     */
    private LocalTime getMelhorVoltaPiloto(List<LogCorridaEntity> listaFiltradaPiloto) {
        return getMelhorVolta(listaFiltradaPiloto);
    }

    /**
     * isMelhorVoltaCorrida
     * Método responsável por verificar se melhor volta do piloto é a melhor volta da corrida
     * @param melhorVoltaPiloto - LocalTime - Objetos LocalTime que representa a melhor volta ao piloto
     * @param melhorVoltaCorrida - LocalTime - Objetos LocalTime que representa a melhor volta da corrida
     * @return Boolean - Retorna verdadeiro/falso
     */
    private Boolean isMelhorVoltaCorrida(LocalTime melhorVoltaPiloto, LocalTime melhorVoltaCorrida) {
        return melhorVoltaPiloto == melhorVoltaCorrida;
    }

    /**
     * getMelhorVolta
     * Método responsável por recuperar a partir da lista original de log, a melhor volta da corrida
     * @param list - List<LogCorridaEntity> - Lista de objetos da entidade LogCorrida
     * @return LocalTime - Retorna objeto LocalTime
     */
    private LocalTime getMelhorVolta(List<LogCorridaEntity> list) {
        return list.stream().min(Comparator.comparing(LogCorridaEntity::getTempoVolta)).get().getTempoVolta();
    }

    /**
     * getVelocidadeMediaPiloto
     * Método responsável por calcular a velocidade média do piloto
     * @param listaFiltradaPiloto - List<LogCorridaEntity> - Lista de objetos da entidade LogCorrida relacionados ao piloto
     * @return Double - Retorno velocidade média do piloto na corrida
     */
    private Double getVelocidadeMediaPiloto(List<LogCorridaEntity> listaFiltradaPiloto) {
        double media = listaFiltradaPiloto.stream().mapToDouble(LogCorridaEntity::getVelocidadeMediaVolta).average().orElse(0.0);
        return Double.valueOf(new DecimalFormat(ConstantesUtil.FORMAT_DOUBLE_3_CASAS).format(media).replace(',', '.'));
    }

    /**
     * getTempoPilotoAtrasVencedor
     * Método responsável por calcular a diferença de tempo entre o vencedor e um piloto (x) que pode ser o próprio vencedor
     * @param tempoProvaVencedor - LocalTime - Objeto que representa o Tempo Total de prova do vencedor da corrida
     * @param tempoProvaPiloto - LocalTime - Objeto que representa o Tempo Total de prova de um piloto
     * @return LocalTime - Retorna objeto LocalTime com a diferença do piloto em relação ao vencedor
     */
    private LocalTime getTempoPilotoAtrasVencedor(LocalTime tempoProvaVencedor, LocalTime tempoProvaPiloto) {
        return TimeUtil.calcula_T2_menos_T1(tempoProvaVencedor, tempoProvaPiloto);
    }
}
