package br.com.gbadessa.util;

import java.time.LocalTime;
import java.util.List;

/**
 * TimeUtil
 * Classe responsável por centralizar Métodos relacionados a manipulação de Objeto 'LocalTime'
 */
public class TimeUtil {

    //region Métodos

    /**
     * convertLocalTimeString
     * Métdo responsável pela conversão de String para Objeto LocalTime
     * @param str - String a ser convertida
     * @return Objeto LocalTime ou null (caso excessão)
     */
    public static LocalTime convertLocalTimeString(String str) {
        try {
            int patternLength = ConstantesUtil.FORMAT_HORA_MINUTO_SEGUNDO_MILISSEGUNDOS.length();
            int strLength = str.length();
            if (strLength == patternLength)
                return LocalTime.parse(str);
            else {
                if (strLength > patternLength)
                    return null;
                else /*if (strLength < patternLength)*/{
                    /*
                        Também para esse caso, estou assumindo que os valores de Data/hora estão nos formatos abaixo
                            #:##.###
                           ##:##.### (Embora n exista essa situação no log, é "plausível" que por algum motivo a volta tenha levado mais de 10 minutos ou exista no arquivo "01:00.000" ao invés de "1:00.000")
                        ##:##:##.###

                        Acredito que através de algum facilitador (classes Formatter), é possível realizar o controle de forma automatizada,
                        mas nesse exemplo preferi tratar concatenando os valores para ficar o mais simples possivel
                    */
                    if(strLength ==  ConstantesUtil.FORMAT_M_SEGUNDO_MILISSEGUNDOS.length()){
                        return LocalTime.parse("00:0" + str);
                    }
                    else if(strLength ==  ConstantesUtil.FORMAT_MM_SEGUNDO_MILISSEGUNDOS.length()){
                        return LocalTime.parse("00:" + str);
                    }
                    else
                        return null;
                }
            }
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * somaLocalTime
     * Método responsável por somar objetos LocalTime
     * @param lstLocalTime - List<LocalTime> - Lista de objetos para a soma
     * @return LocalTime - Retorna objeto que representa o resultado da soma
     */
    public static LocalTime somaLocalTime(List<LocalTime> lstLocalTime){
        LocalTime result = LocalTime.MIN;
        for (LocalTime t : lstLocalTime) {
            result = result.plusHours(t.getHour()).plusMinutes(t.getMinute()).plusSeconds(t.getSecond()).plusNanos(t.getNano());
        }
        //
        return result;
    }

    /**
     * calcula_T2_menos_T1
     * Método responsável por calcular a diferença de tempo entre 2 Objetos LocalTime
     * @param t1 - LocalTime - Objeto t1
     * @param t2 - LocalTime - Objeto t2
     * @return LocalTime - Retorna objeto LocalTime com a diferença (t2 - t1)
     */
    public static LocalTime calcula_T2_menos_T1(LocalTime t1, LocalTime t2){
        if(t1 != null && t2 != null)
            return t2.minusHours(t1.getHour()).minusMinutes(t1.getMinute()).minusSeconds(t1.getSecond()).minusNanos(t1.getNano());
        else
            return null;
    }

    //endregion Métodos
}
