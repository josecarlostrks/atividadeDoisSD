/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author jcarlos
 */
public class Multithreading {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ExecutorService teste = Executors.newFixedThreadPool(2);
        //Buffer localizacaoCompartilhada = new BufferUm();
        //Buffer localizacaoCompartilhada = new BufferDois();
        //Buffer localizacaoCompartilhada = new BufferTres();
        Buffer localizacaoCompartilhada = new BufferBlocking();
        
        try{
            teste.execute(new Produtor(localizacaoCompartilhada));
            teste.execute(new Consumidor(localizacaoCompartilhada));
            
        }catch(Exception exception){
            exception.printStackTrace();
        }
        teste.shutdown();
    }
    
}
