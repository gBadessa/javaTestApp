package br.com.gbadessa.util;

/**
 * ValidationResultUtil
 * Classe responsável pelo retorno de validações do sistema e retorno de msg de erro ou sucesso (se aplicáveis)
 */
public class ValidationResultUtil {

    //region Atributos

    private enum Result {
        SUCESSO,
        FALHA
    }
    //
    private String resultMensagem;

    public String getResultMensagem() {
        return resultMensagem;
    }

    private Result result;

    //endregion Atributos

    //region Métodos

    public boolean isSucesso(){
        return result == Result.SUCESSO;
    }

    public boolean isFalha(){
        return result == Result.FALHA;
    }

    public static ValidationResultUtil setSucesso(){
        ValidationResultUtil result = new ValidationResultUtil();
        result.result = Result.SUCESSO;
        return result;
    }

    public static ValidationResultUtil setFalha(String msgErro){
        ValidationResultUtil result = new ValidationResultUtil();
        result.resultMensagem = msgErro;
        result.result = Result.FALHA;
        return result;
    }

    //endregion Métodos
}
