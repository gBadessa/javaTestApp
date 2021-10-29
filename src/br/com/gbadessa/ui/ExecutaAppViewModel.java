package br.com.gbadessa.ui;

import br.com.gbadessa.Main;
import br.com.gbadessa.model.business.ILogCorridaBusiness;
import br.com.gbadessa.model.viewobjecties.LogCorridaViewObject;
import br.com.gbadessa.util.ConstantesUtil;
import br.com.gbadessa.util.StringUtil;

import java.util.List;

public class ExecutaAppViewModel {

    //private LogCorridaBusiness logCorridaBusiness;
    private ILogCorridaBusiness logCorridaBusiness;

    public ExecutaAppViewModel() {
        iniciaBusinessObjects();
    }

    private void iniciaBusinessObjects() {
        logCorridaBusiness = Main.appContainer.getLogCorridaBusiness();
    }

    //region Metodos
    public void run() {
        try {

            iniciaApp();
            //List<LogCorridaEntity> logCorridaEntities = buscaListaLogs();

            //buscaPosicaoChegada();
            buscaResultados();

            //buscaVencedor();
            finalizaApp();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    public List<LogCorridaEntity> buscaListaLogs() {
//        return logCorridaBusiness.getLogsCorrida();
//    }

//    public void buscaVencedor() {
//
//        List<LogCorridaEntity> resultadoCorrida = logCorridaBusiness.getResultadoCorrida();
//        for (LogCorridaEntity log: resultadoCorrida) {
//            imprimeMsg(log.toString());
//        }
//
//        LogCorridaEntity vencedor = logCorridaBusiness.getVencedor();
//        imprimeMsg(vencedor.getPiloto());
//    }
//
//    public void buscaPosicaoChegada(){
//        List<LogCorridaEntity> resultadoCorrida = logCorridaBusiness.getPosicaoChegada();
//        for (LogCorridaEntity log: resultadoCorrida) {
//            imprimeMsg(log.toString());
//        }
//    }

    public void buscaResultados(){
        List<LogCorridaViewObject> resultadoCorrida = logCorridaBusiness.getResultados();
//        for (DetalheCorridaEntity detalhe: resultadoCorrida) {
//            imprimeMsg(detalhe.toString());
//        }

        resultadoCorrida.forEach(result -> imprimeMsg(result.toString()));
    }

    private void iniciaApp() throws InterruptedException {
        imprimeMsg(StringUtil.msgSplashAppIniciando);
        pulaLinha();
        //
        aguarda1Segundo();
    }

    private void finalizaApp() throws InterruptedException {
        aguarda1Segundo();
        //
        pulaLinha();
        imprimeMsg(StringUtil.msgSplashAppFinalizando);
    }

    private void aguarda1Segundo() throws InterruptedException {
        Thread.sleep(ConstantesUtil.UM_SEGUNDO_EM_MILISEGUNDOS);
    }

    private void pulaLinha() {
        imprimeMsg(StringUtil.pulaLinha);
    }

    private void imprimeMsg(String str) {
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


    //endregion

    //endregion
}
