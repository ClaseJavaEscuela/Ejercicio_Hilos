package Ejercicio2;
/*
* LUIS ANGEL DIAZ DIAZ
* CAMPUES: SAN JACINTO
* */
class HiloRunnable implements Runnable {// clase que implementa Runnable
  private int limite; // limite de iteraciones que debe de realizar

  public HiloRunnable(int limite){
    this.limite = limite;
  } // constructor de la clase

  @Override
  public void run(){
    // iteracion del 0 al limite y muestra un mensaje en cad iteracion
    for(int i=0; i<limite; i++){
      System.out.println("numero: "+(i+1));
      try {
        //le indica al hilo que se duerma por 1 segundo
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}

class EjemploHiloRunnable {
  public static void main(String[] args) {
    // crea un objeto de la clase HiloRunnable y lo asigna a la variable hilo
    HiloRunnable hilo = new HiloRunnable(10);
    // crea un objeto de la clase Thread y le asigna el hilo creado y depsues lo inicia
    Thread hilo2 = new Thread(hilo);
    hilo2.start();
    try {
      //espera a que el hilo termine de ejecutarse
      hilo2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
