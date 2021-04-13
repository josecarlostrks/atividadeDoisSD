/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreading;

import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 * @author jcarlos
 */
public class BufferBlocking implements Buffer{
    
    private ArrayBlockingQueue<Integer> buffer;

    public BufferBlocking() {
        this.buffer = new ArrayBlockingQueue<Integer>(3);
    }
    

    @Override
    public void set(int valor) {
        try{
            buffer.put(valor);
            System.out.printf("%s%2d\t%s%d\n", "Produtor grava: ", valor,"Buffers ocupados: ", buffer.size() );
        }catch(Exception exception){
            exception.printStackTrace();
        }
    }

    @Override
    public int get() {
        int readValue=0;
        try{
            readValue=buffer.take();
            System.out.printf("%s%2d\t%s%d\n","Consumidor lê: ",readValue,"Buffers ocupados: ",buffer.size());
        }catch(Exception exception){
            exception.printStackTrace();
        }
        return readValue;
    }
    
}
