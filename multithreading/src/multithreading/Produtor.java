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
public class Produtor implements Runnable{
    
    private static Random gerador = new Random();
    private Buffer localizacaoCompartilhada;

    public Produtor(Buffer localicacaoCompartilhada) {
        this.localizacaoCompartilhada = localicacaoCompartilhada;
    }
    
    

    @Override
    public void run() {
        for(int contador=1; contador<=10; contador++){
            try{
                Thread.sleep(gerador.nextInt(3000));
                localizacaoCompartilhada.set(contador);
            }catch(InterruptedException exception){
                exception.printStackTrace();
            }
        }
        System.out.printf("\n%s\n%s\n", "Produtor produz!!!",
        "Fim do Produtor.");
    }
    
}
