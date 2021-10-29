package br.com.gbadessa.ui;

import br.com.gbadessa.Main;
import br.com.gbadessa.model.business.ILogCorridaBusiness;
import br.com.gbadessa.util.ConstantesUtil;
import br.com.gbadessa.util.StringUtil;
import br.com.gbadessa.util.ValidationResultUtil;

/**
 * SplashScreenAppViewModel
 * Classe que acessa os objetos de negócio (Business) e retorna status das execuções para a View que é a responsável
 * pela exibição das mensagens para o usuário.
 */
public class SplashScreenAppViewModel {

    //region Atributos

    private ILogCorridaBusiness logCorridaBusiness;

    //endregion Atributos

    //region Contrutores

    public SplashScreenAppViewModel() {
        iniciaBusinessObjects();
    }

    //endregion Construtores

    //region Metodos

    /**
     * iniciaBusinessObjects
     * Método responsável por recuperar objeto Container e extrair do mesmo os objetos de negócios instanciados ao iniciar a aplicação.
     */
    private void iniciaBusinessObjects() {
        logCorridaBusiness = Main.appContainer.getLogCorridaBusiness();
    }

    public void run() {
        try {

            iniciaSplash();
            //
            //Carrega recursos do sistema
            iniciaCarregamentoRecursos();
            ValidationResultUtil result = carregaListaLogs();

            //Exibe msg de falha e finaliza a aplicação no caso de erro
            if(result.isFalha()){
                solicitaImpressaoMsg(result.getResultMensagem());
                return;
            }

            finalizaCarregamentoRecursos();
            finalizaSplash();
            //
            // Executa "Tela de Execução da aplicação" que manipulará os recursos alocados
            new ExecutaAppView();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private ValidationResultUtil carregaListaLogs() {
        return logCorridaBusiness.carregaLogsCorridaMemoria();
    }

    private void iniciaSplash() throws InterruptedException {
        solicitaImpressaoMsg(StringUtil.msgSplashIniciando);
        solicitaImpressaoMsg(StringUtil.msgSplashAppIniciando);
        solicitaAguardar();
    }

    private void iniciaCarregamentoRecursos() {
        solicitaImpressaoMsg(StringUtil.msgSplashAppRecursosCarregando);
    }

    private void finalizaCarregamentoRecursos() {
        solicitaImpressaoMsg(StringUtil.msgSplashAppRecursosSucesso);
    }

    private void finalizaSplash() throws InterruptedException {
        solicitaImpressaoMsg(StringUtil.msgSplashFinalizando);
        solicitaImpressaoMsg(StringUtil.pulaLinha);
        solicitaAguardar();
    }

    private void solicitaImpressaoMsg(String str) {
        if(iSplashScreenAppViewModelListner != null)
            iSplashScreenAppViewModelListner.onPrintMsg(str);
    }

    private void solicitaAguardar() {
        if(iSplashScreenAppViewModelListner != null)
            iSplashScreenAppViewModelListner.onSleep();
    }

    //endregion Metodos

    //region Interfaces

    private ISplashScreenAppViewModelListner iSplashScreenAppViewModelListner;

    public interface ISplashScreenAppViewModelListner {
        void onPrintMsg(String msg);
        void onSleep();
    }

    //region getters setters

    public ISplashScreenAppViewModelListner getiSplashScreenAppViewModelListner() {
        return iSplashScreenAppViewModelListner;
    }

    public void setiSplashScreenAppViewModelListner(ISplashScreenAppViewModelListner iSplashScreenAppViewModelListner) {
        this.iSplashScreenAppViewModelListner = iSplashScreenAppViewModelListner;
    }

    //endregion getters setters

    //endregion Interfaces
}