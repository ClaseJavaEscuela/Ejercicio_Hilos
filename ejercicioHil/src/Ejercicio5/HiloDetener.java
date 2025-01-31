package Ejercicio5;
/*
* LUIS ANGEL DIAZ DIAZ
* CAMPUS: SAN JACINTO
* */
public class HiloDetener extends Thread {// Hilo hereda de Thread
  @Override
  public void run(){
    // le indicamos al hilo que al inicar ejecute un ciclo for el cual
    // mostrara un mensaje cada segundo hasta que el hilo se detenga
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
    HiloDetener hilo = new HiloDetener();// creamos un objeto de tipo HiloDetener
    hilo.start();// iniciamos el hilo
    try {
      // esperamos 10 segundos para que el hilo se detenga
      Thread.sleep(10000);
    }catch (InterruptedException e){
      e.printStackTrace();
    }
    // despues de 10 segundos llamamos al metodo interrupt() para detener el hilo
    hilo.interrupt();
    try{
      // esperamos a que el hilo se detenga
      hilo.join();
    }catch (InterruptedException e){
      e.printStackTrace();
    }
  }
}