package br.com.gbadessa.util;

/**
 * StringResourcesUtil
 * Classe responsável por centralizar as Msgs utilizadas no sistema (resource de strings)
 */
public class StringResourcesUtil {

    //Facilitador para concatenação das Strings
    public static final String concatenaStringsBarra = "%s/%s";
    public static final String concatenaStringsEspaco = "%s %s";
    public static final String concatenaStringsEspacoRet = "%s %s ...";

    //Recurso de Strings
    public static final String appName = "Desafio Java";

    public static final String msgAppIniciando = String.format(concatenaStringsEspacoRet, "Iniciando", appName);
    public static final String msgAppFinalizando = String.format(concatenaStringsEspacoRet, "Finalizando", appName);
    public static final String msgSplashAppRecursosCarregando = "Carregando recursos.... Aguarde.";
    public static final String msgSplashAppRecursosSucesso = "Recursos alocados com sucesso.";
    public static final String pulaLinha = "\n";

    public static final String Sim = "Sim";
    public static final String Nao = "Não";

    public static final String Posiçao = "Posição";
    public static final String CodigoPiloto  = "Codigo Piloto";
    public static final String NomePiloto  = "Nome Piloto";
    public static final String VoltasCompletadas  = "Voltas Completadas";
    public static final String TempoTotalProva = "Tempo Total Prova";
    public static final String MelhorVoltaPiloto = "Melhor Volta Piloto";
    public static final String MelhorVoltaCorrida = "Melhor Volta Corrida";
    public static final String VelocMediaPiloto = "Veloc.MediaPiloto";
    public static final String TempoAposVencedor = "Tempo Após Vencedor";
}
