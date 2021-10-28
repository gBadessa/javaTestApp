package br.com.gbadessa.model.business;

import br.com.gbadessa.model.entities.LogCorridaEntity;
import br.com.gbadessa.model.repositories.ILogCorridaRepository;
import br.com.gbadessa.util.ArquivoUtil;
import br.com.gbadessa.util.TimeUtil;
import br.com.gbadessa.util.ValidationResultUtil;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class LogCorridaBusiness implements ILogCorridaBusiness {

    private ILogCorridaRepository logCorridaRepository;

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

    private static LogCorridaEntity getLogCorrida(String hora, String piloto, String numVolta, String tempoVolta, String velocidadeMediaVolta) {
        LocalTime _hora = TimeUtil.convertLocalTimeString(hora);
        String _piloto = piloto;
        int _numVolta = Integer.parseInt(numVolta);
        LocalTime _tempoVolta = TimeUtil.convertLocalTimeString(tempoVolta);
        Double _velocidadeMediaVolta = Double.valueOf(velocidadeMediaVolta.replace(",", "."));
        return new LogCorridaEntity(_hora, _piloto, _numVolta, _tempoVolta, _velocidadeMediaVolta);
    }


    @Override
    public ValidationResultUtil carregaLogsCorridaMemoria(){
        //
        try {
            logCorridaRepository.setLogsCorrida(geraListaLogsCorrida(ArquivoUtil.getLinhasArquivoLog()));
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
        return logCorridaRepository.getPosicaoChegadaPilotos();
    }
}
