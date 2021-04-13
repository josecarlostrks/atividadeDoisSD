/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreading;

import java.util.Random;

/**
 *
 * @author jcarlos
 */
public class Consumidor implements Runnable{
    
    private static Random gerador = new Random();
    private Buffer localizacaoCompartilhada;

    public Consumidor(Buffer localizacaoCompartilhada) {
        this.localizacaoCompartilhada = localizacaoCompartilhada;
    }
    
    

    @Override
    public void run() {
        int soma = 0;
        for(int contador=1;contador<=10;contador++){
            try{
                Thread.sleep(gerador.nextInt(3000));
                soma += localizacaoCompartilhada.get();
            }catch(InterruptedException exception){
                exception.printStackTrace();
            }
        }
        System.out.printf("\n%s%d\n ", "Fim do Consumidor. Valor da soma: ", soma );
    }
    
}
