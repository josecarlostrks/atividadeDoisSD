/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreading;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author jcarlos
 */
public class BufferTres implements Buffer{
    private Lock acessLock = new ReentrantLock();
    private Condition podeGravar = acessLock.newCondition();
    private Condition podeLer = acessLock.newCondition();
    private int[] buffer = {-1,-1,-1};
    private int buffersOcupados = 0;
    private int gravarIndice = 0;
    private int lerIndice = 0;

    @Override
    public void set(int valor){
        acessLock.lock();
        try{
            while(buffersOcupados == buffer.length){
                System.out.printf("Todos os buffers cheios. Produtor aguarda.\n ");
                podeGravar.await();
            }
            buffer[gravarIndice] = valor;
            gravarIndice = (gravarIndice + 1)%buffer.length;
            buffersOcupados++;
            mostrarEstado("Produtor grava "+valor);
            podeLer.signal();
        }catch(InterruptedException exception){
            exception.printStackTrace();
        }finally{
            acessLock.unlock();
        }
        
    }

    @Override
    public int get() {
        int valorLido = 0;
        acessLock.lock();
        
        try{
            while(buffersOcupados==0){
                System.out.printf("Todos os buffers estão vazios. Consumidor aguarda\n");
                podeLer.await();
            }
            valorLido = buffer[lerIndice];
            lerIndice = (lerIndice+1)%buffer.length;
            buffersOcupados--;
            mostrarEstado("Consumidor lê: "+valorLido);
            podeGravar.signal();
        }catch(InterruptedException exception){
            exception.printStackTrace();
        }finally{
            acessLock.unlock();
        }
        return valorLido;
    }
    
    public void mostrarEstado(String operacao){
        System.out.printf("%s%s%d\n",operacao,"Buffers ocupados: ",buffersOcupados);
    }
    
}
