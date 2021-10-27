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

    public static void main(String[] args) {
        System.out.println("Iniciando" + StringUtil.appName);
        //
        try {
            List<LogCorridaEntity> logCorridaEntities = geraListaLogsCorrida(ArquivoUtil.getLinhasArquivoLog());
            logCorridaEntities.forEach(result -> System.out.println((result.toString())));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<LogCorridaEntity> geraListaLogsCorrida(List<String> listaLog) {
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

}