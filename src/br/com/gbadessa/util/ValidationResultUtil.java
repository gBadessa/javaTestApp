package br.com.gbadessa.util;

public class ValidationResultUtil {

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
}
