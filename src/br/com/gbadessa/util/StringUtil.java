package br.com.gbadessa.util;

/**
 * StringUtil
 * Classe responsável por centralizar as Msgs utilizadas no sistema (resource de strings)
 */
public class StringUtil {

    //Facilitador para concatenação das Strings
    public static final String concatenaStringsBarra = "%s/%s";
    public static final String concatenaStringsEspaco = "%s %s";
    public static final String concatenaStringsEspacoRet = "%s %s ...";

    //Recurso de Strings
    public static final String appName = "Desafio Java";

    public static final String msgSplashAppIniciando = String.format(concatenaStringsEspacoRet, "Iniciando", appName);
    public static final String msgSplashAppRecursosCarregando = "Carregando recursos.... Aguarde.";
    public static final String msgSplashAppRecursosSucesso = "Recursos alocados com sucesso.";
    public static final String pulaLinha = "\n";
    public static final String msgSplashIniciando    = "******************** INICIANDO SPLASH SCREEN.. ********************";
    public static final String msgSplashFinalizando  = "******************** FINALIZANDO SPLASH SCREEN ********************";
    public static final String msgDesafioIniciando   = "******************** INICIANDO DESAFIO........ ********************";
    public static final String msgDesafioFinalizando = "******************** FINALIZANDO DESAFIO...... ********************";
}
