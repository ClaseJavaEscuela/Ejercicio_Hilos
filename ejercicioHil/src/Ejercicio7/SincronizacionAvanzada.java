package Ejercicio7;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
class DataAvanzada{
  private int[] data;
  Lock lock = new ReentrantLock();

  public DataAvanzada(int maximaData){
    this.data = new int[maximaData];
  }

  public void ingresarData(int dato, int indice){
    lock.lock();
    try{
      if(indice < data.length ){
        System.out.println("El dato ingresado es: " + dato);
        this.data[indice] = dato;
      }
    }finally {
      lock.unlock();
    }
  }

  public void sacarDatos(int indice){
    lock.lock();
    try{
      while(data.length == 0){
        try{
          System.out.println("Esperando datos");
          wait();
        }catch(InterruptedException e){
          e.printStackTrace();
        }
      }

      if(indice < data.length){
        System.out.println("Sacando el dato: " + data[indice]);
      }
    }finally {
      lock.unlock();
    }

  }

}

public class SincronizacionAvanzada {
  private static int limiteData = 10;

  public static void main(String[] args) {
    DataAvanzada data = new DataAvanzada(limiteData);

    Thread productor = new Thread(() -> {
      for (int i = 0; i < limiteData; i++) {
        try {
          data.ingresarData(i+1, i);
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    Thread consumidor = new Thread(() -> {
      for (int i = 0; i < limiteData; i++) {
        try {
          data.sacarDatos(i);
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    productor.start();
    consumidor.start();

    try {
      productor.join();
      consumidor.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}