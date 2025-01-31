package Ejercicio4;
/*
* LUIS ANGEL DIAZ DIAZ
* CAMPUS: SAN JACINTO
* */
public class HiloPausado extends Thread { // Hilo pausado hereda de Thread
  @Override
  public void run() {
    // Hilo que se ejecuta y ejecuta un ciclo for el cual mostrara un mensaje con el valor de la
    // variable i
    for (int i = 1; i <= 10; i++) {
      System.out.println("[ "+i+" ]");
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

}
class MainHiloPausado {
  public static void main(String[] args) {
    // Creacion de un hilo pausado
    HiloPausado hilo = new HiloPausado();
    // Iniciamos del hilo
    hilo.start();
    try{
      // Esperamos a que el hilo termine
      hilo.join();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
