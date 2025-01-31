package Ejercicio6;
/*
* LUIS ANGEL DIAZ DIAZ
* CAMPUES: SAN JACINTO
* */
import static java.lang.Thread.*;

public class HilosPrioridades extends Thread {
  @Override
  public void run(){
    //mostramos la prioridad del hilo
    System.out.println("Hilo con prioridad: " + this.getPriority());
  }
}
class MainPrioridades {
  public static void main(String[] args) {
    // creamos los hilos
    HilosPrioridades h1 = new HilosPrioridades();
    HilosPrioridades h2 = new HilosPrioridades();
    HilosPrioridades h3 = new HilosPrioridades();
    //cambiamos los niveles de prioridad en los hilos
    h1.setPriority(MIN_PRIORITY);
    h2.setPriority(NORM_PRIORITY);
    h3.setPriority(MAX_PRIORITY);
    // iniciamos los hilos
    h1.start();
    h2.start();
    h3.start();
    try{
      //esperamos a que terminen los hilos
      h1.join();
      h2.join();
      h3.join();
    }catch(InterruptedException e){
      e.printStackTrace();
    }
  }
}
