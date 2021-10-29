package br.com.gbadessa.ui;

import br.com.gbadessa.util.ConstantesUtil;
import br.com.gbadessa.util.PrintMsgUtil;
import br.com.gbadessa.util.StringUtil;

/**
 * ExecutaAppView
 * Classe que representa a "Tela" de execução do programa, por esse motivo criei dentro do package "ui"
 * Responsável por acessar os "recursos" alocados do sistema e extrair informações dos mesmos
 */
public class ExecutaAppView {

    //region Atributos

    ExecutaAppViewModel viewModel;

    //endregion Atributos

    //region Construtores

    /**
     * ExecutaAppView
     * Construtor é reponsável por chamar método de inicialização dos objetos
     */
    public ExecutaAppView() {

        //Cria Objeto ViewModel e seta Listner para tratamento dos retornos definidos na mesma
        inicializaViewModel();

        //Executa método run da viewModel que inicia as regras de negócio da aplicação
        viewModel.run();
    }

    //endregion Construtores

    //region Métodos

    private void inicializaViewModel(){
        //Instancia viewModel para separar a "interface" da lógica da tela
        viewModel = new ExecutaAppViewModel();

        //Seta interface (listner) para impressao das msgs tratadas no viewModel
        viewModel.setiExecutaAppViewModelListner(new ExecutaAppViewModel.IExecutaAppViewModelListner() {
            @Override
            public void onPrintMsg(String msg) {
                imprimeMsg(msg);
            }
        });
    }

    private static void aguarda1Segundo() throws InterruptedException {
        Thread.sleep(ConstantesUtil.UM_SEGUNDO_EM_MILISEGUNDOS);
    }

    private static void pulaLinha(){
        PrintMsgUtil.imprimeMsg(StringUtil.pulaLinha);
    }

    private static void imprimeMsg(String str){
        PrintMsgUtil.imprimeMsg(str);
    }

    //endregion Métodos
}
