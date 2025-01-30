package Ejercicio3;

class HiloError extends Thread {

  public void run() {
    for(int i=0; i<2; i++){
      System.out.println("Hola desde el hilo: "+getName());
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
/*
- Intentar iniciar un mismo hilo dos veces genera `IllegalThreadStateException`.
- Una vez que un hilo ha sido iniciado y finalizado, no puede reiniciarse.
* */
public class ErroresHilo {

  public static void main(String[] args) {
    HiloError h1 = new HiloError();
    h1.setName("Hilo 1");
    try {
      h1.start();
      h1.start();
      h1.join();
    }catch (IllegalThreadStateException e){
      System.out.println("Error: "+e.getMessage());
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
