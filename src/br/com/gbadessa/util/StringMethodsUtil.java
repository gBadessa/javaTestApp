package br.com.gbadessa.util;

/**
 * StringMethodsUtil
 * Classe responsável por centralizar Métodos relacionados a manipulação de Strings
 */
public class StringMethodsUtil {


    public static String RPad(String str, Integer length, char car) {
        return String.format("%-" + length + "s", str).replace(' ', car);
    }
}
