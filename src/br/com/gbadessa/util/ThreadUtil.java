package br.com.gbadessa.util;

import java.time.LocalTime;
import java.util.List;

/**
 * ThreadUtil
 * Classe responsável por centralizar Métodos relacionados a manipulação de Threads
 */
public class ThreadUtil {

    //region Métodos

    public static void aguarda1Segundo() throws InterruptedException {
        Thread.sleep(ConstantesUtil.UM_SEGUNDO_EM_MILISEGUNDOS);
    }

    //endregion Métodos
}
