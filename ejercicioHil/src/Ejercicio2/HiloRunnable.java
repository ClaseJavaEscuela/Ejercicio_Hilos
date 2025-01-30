package Ejercicio2;
/*
* LUIS ANGEL DIAZ DIAZ
* CAMPUES: SAN JACINTO
* */
class HiloRunnable implements Runnable {
  private int limite;

  public HiloRunnable(int limite){
    this.limite = limite;
  }

  @Override
  public void run(){
    for(int i=0; i<limite; i++){
      System.out.println("numero: "+(i+1));
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

  }

}

class EjemploHiloRunnable {
  public static void main(String[] args) {
    HiloRunnable hilo = new HiloRunnable(10);
    Thread hilo2 = new Thread(hilo);
    hilo2.start();
    try {
      hilo2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
