package Ejercicio2;
/*
* LUIS ANGEL DIAZ DIAZ
* CAMPUS: SAN JACINTO
* */
class HiloExtendido extends  Thread {
  @Override
  public void run() {
    System.out.println("Mensaje desde el hilo: "+getName());
    for(int i=0;i<5;i++){
      System.out.println("mensaje numero: "+(i+1));
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

  }

}
class EjemploHiloExtendido {
  public static void main(String[] args) {
    HiloExtendido hilo1 = new HiloExtendido();
    hilo1.setName("Hilo 1");
    hilo1.start();
    try {
      hilo1.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
