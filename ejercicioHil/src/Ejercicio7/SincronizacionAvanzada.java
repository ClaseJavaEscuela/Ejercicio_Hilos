package Ejercicio7;
/*
* LUIS ANGEL DIAZ DIAZ
* */
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
// Clase que gestiona datos con control de concurrencia mediante Lock
class DataAvanzada{
  private int[] data;
  Lock lock = new ReentrantLock();

  public DataAvanzada(int maximaData){
    this.data = new int[maximaData];
  }

  // Metodo para ingresar datos de manera segura con bloqueo explícito
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

  // Metodo para extraer datos con sincronización
  public void sacarDatos(int indice){
    lock.lock();
    try{
      while(data.length == 0){
        System.out.println("Esperando datos");
      }

      if(indice < data.length){
        System.out.println("Sacando el dato: " + data[indice]);
      }
    }finally {
      lock.unlock();
    }

  }

}

// Clase principal que maneja el flujo del programa
public class SincronizacionAvanzada {
  private static int limiteData = 10;

  // Hilo que simula un productor de datos
  public static void main(String[] args) {
    DataAvanzada data = new DataAvanzada(limiteData);

    Thread productor = new Thread(() -> {
      for (int i = 0; i < limiteData; i++) {
        try {
          data.ingresarData(i+1, i);
          Thread.sleep(900);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    // Hilo que simula un consumidor de datos
    Thread consumidor = new Thread(() -> {
      for (int i = 0; i < limiteData; i++) {
        try {
          data.sacarDatos(i);
          Thread.sleep(950);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    // Iniciar ambos hilos
    productor.start();
    consumidor.start();

    try {
      //esperar a que ambos hilos terminen
      productor.join();
      consumidor.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}