package br.com.gbadessa.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ArquivoUtil
 * Classe responsável por centralizar Métodos relacionados a leitura do arquivo
 */
public class ArquivoUtil {

    //region Atributos

    /**
     * Definição do diretório base do arquivo de LOG
     */
    private static final String DIR_ARQUIVOS = "res/arquivos";

    //endregion Atributos

    //region Métodos

    /**
     * getLinhasArquivo
     * Metodo responsável por ler informações de um arquivo de texto e retonrnas suas linhas
     *
     * @param nomArquivo – Nome do Arquivo a ser lido no diretório de Arquivos do sistema
     * @return List<String> – Lista de String das linhas do arquivo
     * @throws IOException – Retorna IOException no caso de erro
     */
    public static List<String> getLinhasArquivo(String nomArquivo) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(String.format(StringResourcesUtil.concatenaStringsBarra, DIR_ARQUIVOS, nomArquivo)));
        List<String> linhas = br.lines().collect(Collectors.toList());
        br.close();
        //
        return linhas;
    }

    //endregion Métodos
}
