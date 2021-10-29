package br.com.gbadessa.ui;

import br.com.gbadessa.Main;
import br.com.gbadessa.model.business.ILogCorridaBusiness;
import br.com.gbadessa.model.viewobjecties.LogCorridaViewObject;
import br.com.gbadessa.util.StringUtil;

import java.util.List;

/**
 * ExecutaAppViewModel
 * Classe que acessa os objetos de negócio (Business) e retorna status das execuções para a View que é a responsável
 * pela exibição das mensagens para o usuário.
 */
public class ExecutaAppViewModel {

    //region Atributos

    private ILogCorridaBusiness logCorridaBusiness;

    //endregion Atributos

    //region Contrutores

    public ExecutaAppViewModel() {
        iniciaBusinessObjects();
    }

    //endregion Contrutores

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
            iniciaApp();
            buscaResultados();
            finalizaApp();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buscaResultados(){
        List<LogCorridaViewObject> resultadoCorrida = logCorridaBusiness.getResultados();
        resultadoCorrida.forEach(result -> solicitaImpressaoMsg(result.toString()));
    }

    private void iniciaApp(){
        solicitaImpressaoMsg(StringUtil.msgDesafioIniciando);
    }

    private void finalizaApp(){
        solicitaImpressaoMsg(StringUtil.msgDesafioFinalizando);
    }

    private void solicitaImpressaoMsg(String str) {
        if(iExecutaAppViewModelListner != null)
            iExecutaAppViewModelListner.onPrintMsg(str);
    }

    //endregion Metodos

    //region Interfaces

    private IExecutaAppViewModelListner iExecutaAppViewModelListner;

    public interface IExecutaAppViewModelListner {
        void onPrintMsg(String msg);
    }

    //region getters setters

    public IExecutaAppViewModelListner getiExecutaAppViewModelListner() {
        return iExecutaAppViewModelListner;
    }

    public void setiExecutaAppViewModelListner(IExecutaAppViewModelListner iExecutaAppViewModelListner) {
        this.iExecutaAppViewModelListner = iExecutaAppViewModelListner;
    }

    //endregion getters setters

    //endregion Interfaces
}
