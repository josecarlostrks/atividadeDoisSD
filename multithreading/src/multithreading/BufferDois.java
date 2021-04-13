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
public class BufferDois implements Buffer{
    
    private Lock lockDeAcesso = new ReentrantLock();
    private Condition podeGravar = lockDeAcesso.newCondition();
    private Condition podeLer = lockDeAcesso.newCondition();
    private int buffer = -1;
    private boolean ocupado = false;

    @Override
    public void set(int valor) {
        lockDeAcesso.lock();
        try{
            while(ocupado){
                System.out.println("Pode tentar gravar.");
                mostrarEstado("Buffer cheio. Produtor aguardando.");
                podeGravar.await();
            }
            buffer = valor;
            ocupado = true;
            mostrarEstado("Produtor grava: " + buffer);
            podeLer.signal();
        }catch(InterruptedException exception){
            exception.printStackTrace();
        }finally{
            lockDeAcesso.unlock();
        }
    }

    @Override
    public int get() {
        int valorLido = 0;
        lockDeAcesso.lock();
        try{
            while(!ocupado){
                System.out.println("Consumidor tenta ler.");
                mostrarEstado("Buffer vazio. Consumidor aguardando.");
                podeLer.await();
            }
            ocupado = false;
            valorLido = buffer;
            mostrarEstado("Consumidor lê: "+valorLido);
            podeGravar.signal();
        }catch(InterruptedException exception){
            exception.printStackTrace();
        }finally{
            lockDeAcesso.unlock();
        }
        
        return valorLido;
    }
    
    public void mostrarEstado(String operacao){
        System.out.printf("%-40s%d\t\t%b\n\n",operacao, buffer, ocupado);
    }
    
}
