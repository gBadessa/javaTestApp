package br.com.gbadessa.ui;

import br.com.gbadessa.util.ConstantesUtil;
import br.com.gbadessa.util.PrintMsgUtil;
import br.com.gbadessa.util.ThreadUtil;

/**
 * SplashScreenAppView
 * Classe que representa a "Tela de Splash" screen do programa, por esse motivo criei dentro do package "ui"
 * Responsável por alocar os "recursos" do sistema e em seguida iniciar a execução do mesmo
 */
public class SplashScreenAppView {

    //region Atributos

    SplashScreenAppViewModel viewModel;

    //endregion Atributos

    //region Construtores

    /**
     * SplashScreenAppView
     * Construtor é reponsável por chamar método de inicialização dos objetos
     */
    public SplashScreenAppView() {

        //Cria Objeto ViewModel e seta Listner para tratamento dos retornos definidos na mesma
        inicializaViewModel();

        //Executa método run da viewModel que inicia as regras de negócio da aplicação
        viewModel.run();
    }

    //endregion Construtores

    //region Métodos

    private void inicializaViewModel(){
        //Instancia viewModel para separar a "interface" da lógica da tela
        viewModel = new SplashScreenAppViewModel();

        //Seta interface (listner) para impressao das msgs tratadas no viewModel
        viewModel.setiSplashScreenAppViewModelListner(new SplashScreenAppViewModel.ISplashScreenAppViewModelListner() {
            @Override
            public void onPrintMsg(String msg) {
                imprimeMsg(msg);
            }

            @Override
            public void onSleep() {
                try {
                    aguarda1Segundo();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static void imprimeMsg(String str){
        PrintMsgUtil.imprimeMsg(str);
    }

    private void aguarda1Segundo() throws InterruptedException {
        ThreadUtil.aguarda1Segundo();
    }

    //endregion Métodos
}
