package Ejercicio4;

public class HiloPausado extends Thread {
  @Override
  public void run() {
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
    HiloPausado hilo = new HiloPausado();
    hilo.start();
    try{
      hilo.join();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
