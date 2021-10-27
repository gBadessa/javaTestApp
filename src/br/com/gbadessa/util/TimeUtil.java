package br.com.gbadessa.util;

import java.time.LocalTime;
import java.util.List;

public class TimeUtil {
    public static final String FORMAT_HORA_MINUTO_SEGUNDO_MILISSEGUNDOS = "HH:mm:ss.SSS";
    public static final String FORMAT_M_SEGUNDO_MILISSEGUNDOS = "m:ss.SSS";
    public static final String FORMAT_MM_SEGUNDO_MILISSEGUNDOS = "mm:ss.SSS";

    public static LocalTime convertLocalTimeString(String str) {

        try {
            int patternLength = FORMAT_HORA_MINUTO_SEGUNDO_MILISSEGUNDOS.length();
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
                    if(strLength ==  FORMAT_M_SEGUNDO_MILISSEGUNDOS.length()){
                        return LocalTime.parse("00:0" + str);
                    }
                    else if(strLength ==  FORMAT_MM_SEGUNDO_MILISSEGUNDOS.length()){
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
}
