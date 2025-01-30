package Ejercicio5;

public class HiloDetener extends Thread {
  @Override
  public void run(){
    try{
      for(int i=0; i<1000; i++){
        System.out.println("Hilo 1: " + i);
        Thread.sleep(1000);
      }
    }catch (InterruptedException e){
      System.out.println("El hilo se ha detenido");
    }
  }
}

class MainDetenerHilo{
  public static void main(String[] args) throws InterruptedException {
    HiloDetener hilo = new HiloDetener();
    hilo.start();
    try {
      Thread.sleep(10000);
    }catch (InterruptedException e){
      e.printStackTrace();
    }
    hilo.interrupt();
    try{
      hilo.join();
    }catch (InterruptedException e){
      e.printStackTrace();
    }
  }
}