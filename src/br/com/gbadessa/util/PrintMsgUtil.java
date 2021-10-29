package br.com.gbadessa.util;

/**
 * PrintMsgUtil
 * Classe responsável por controlar a impressão das Msgs do sistema
 */
public class PrintMsgUtil {

    //region Métodos

    public static void pulaLinha() {
        imprimeMsg(StringUtil.pulaLinha);
    }

    public static void imprimeMsg(String str) {
        System.out.println(str);
    }

    //endregion Métodos
}
