package br.com.gbadessa.ui;

import br.com.gbadessa.Main;
import br.com.gbadessa.model.business.ILogCorridaBusiness;
import br.com.gbadessa.model.viewobjecties.LogCorridaViewObject;
import br.com.gbadessa.util.ConstantesUtil;
import br.com.gbadessa.util.StringMethodsUtil;
import br.com.gbadessa.util.StringResourcesUtil;

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
            buscaResultados();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buscaResultados(){
        List<LogCorridaViewObject> resultadoCorrida = logCorridaBusiness.getResultados();

        imprimeCabecalhoResultado();
        resultadoCorrida.forEach(item -> {
            String resultado =
                    ajustaLinhaImpressao(item.getPosicaoChegada().toString()) +
                    ajustaLinhaImpressao(item.getCodPiloto()) +
                    ajustaLinhaImpressao(item.getNomePiloto()) +
                    ajustaLinhaImpressao(item.getNumVoltasCompletadas().toString()) +
                    ajustaLinhaImpressao(item.getTempoTotalProva().toString()) +
                    ajustaLinhaImpressao(item.getMelhorVoltaPiloto().toString()) +
                    ajustaLinhaImpressao(item.getMelhorVoltaCorrida()) +
                    ajustaLinhaImpressao(item.getVelocidadeMediaPiloto().toString()) +
                    ajustaLinhaImpressao(item.getTempoAposVencedor().toString());
            //
            solicitaImpressaoMsg(resultado);
        });
    }

    private void imprimeCabecalhoResultado(){

        solicitaImpressaoMsg(StringResourcesUtil.msgAppFinalizando);
        //
        String result =
                ajustaLinhaImpressao(StringResourcesUtil.Posiçao) +
                ajustaLinhaImpressao(StringResourcesUtil.CodigoPiloto) +
                ajustaLinhaImpressao(StringResourcesUtil.NomePiloto) +
                ajustaLinhaImpressao(StringResourcesUtil.VoltasCompletadas) +
                ajustaLinhaImpressao(StringResourcesUtil.TempoTotalProva) +
                ajustaLinhaImpressao(StringResourcesUtil.MelhorVoltaPiloto) +
                ajustaLinhaImpressao(StringResourcesUtil.MelhorVoltaCorrida) +
                ajustaLinhaImpressao(StringResourcesUtil.VelocMediaPiloto) +
                ajustaLinhaImpressao(StringResourcesUtil.TempoAposVencedor);
        //
        solicitaImpressaoMsg(result);
    }

    private String ajustaLinhaImpressao(String str){
        Integer length = ConstantesUtil.CABECALHO_COLUNAS_SIZE;
        return StringMethodsUtil.RPad(str, length, ' ');
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
