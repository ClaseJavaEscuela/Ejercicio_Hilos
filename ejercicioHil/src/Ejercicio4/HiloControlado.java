package Ejercicio4;

public class HiloControlado extends Thread {
  private volatile boolean parado = false;
  public void parar() {
    parado = true;
  }
  @Override
  public void run() {
    while (!parado) {
      System.out.println("Hilo controlado");
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

}


class Main{
  public static void main(String[] args) {
    HiloControlado hilo = new HiloControlado();
    hilo.start();
    try{
      Thread.sleep(3000);
    }catch(InterruptedException e){
      e.printStackTrace();
    }
    hilo.parar();
    try{
      hilo.join();
      System.out.println("Hilo termiando");
    }catch(InterruptedException e){
      e.printStackTrace();
    }
  }
}


