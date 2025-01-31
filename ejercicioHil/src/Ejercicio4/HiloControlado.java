package Ejercicio4;
/*
* LUIS ANGEL DIAZ DIAZ
* CAMPUS: SAN JACINTO
* */
public class HiloControlado extends Thread { //HiloControlado hereda de Thread
  private volatile boolean parado = false;//Variable volatile para simular que podemos controlar el hilo
  // funcion para detener el hilo
  public void parar() {
    //cambiamos el valor de la variable parado
    // de esta forma simulamos controlar el hilo
    parado = true;
  }
  @Override
  public void run() {
    //ciclo infinito el cual se detiene cuando se llama a la funcion parar
    while (!parado) {
      // mientras no se detenga el chiclo se muestra un mensaje
      System.out.println("Hilo ejecutandose");
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
    HiloControlado hilo = new HiloControlado();//creamos un hilo
    hilo.start();// iniciamos el hilo
    try{
      //dormimos el programa principal 3 segundos mientras que el hilo
      // se ejecuta
      Thread.sleep(3000);
    }catch(InterruptedException e){
      e.printStackTrace();
    }
    //despues de los 3 segundo paramos el hilo
    hilo.parar();
    try{
      // esperamos a que el hilo termine
      hilo.join();
      System.out.println("Hilo termiando");
    }catch(InterruptedException e){
      e.printStackTrace();
    }
  }
}


