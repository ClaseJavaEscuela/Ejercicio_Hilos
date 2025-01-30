package Ejercicio6;

import static java.lang.Thread.*;

public class HilosPrioridades extends Thread {
  @Override
  public void run(){
    System.out.println("Hilo con prioridad: " + this.getPriority());
  }
}
class MainPrioridades {
  public static void main(String[] args) {
    HilosPrioridades h1 = new HilosPrioridades();
    HilosPrioridades h2 = new HilosPrioridades();
    HilosPrioridades h3 = new HilosPrioridades();
    h1.setPriority(MIN_PRIORITY);
    h2.setPriority(NORM_PRIORITY);
    h3.setPriority(MAX_PRIORITY);
    h1.start();
    h2.start();
    h3.start();
    try{
      h1.join();
      h2.join();
      h3.join();
    }catch(InterruptedException e){
      e.printStackTrace();
    }
  }
}
