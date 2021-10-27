package br.com.gbadessa.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ArquivoUtil {
    private static final String dirArquivos = "res/arquivos";
    private static final String nomArquivoLog = "log.txt";

    public static List<String> getLinhasArquivoLog() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(String.format(StringUtil.concatenaStringsBarra, dirArquivos, nomArquivoLog)));
        List<String> linhas = br.lines().collect(Collectors.toList());
        br.close();
        //
        return linhas;
    }
}
