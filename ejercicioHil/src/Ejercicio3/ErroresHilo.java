package Ejercicio3;
/*
* LUIS ANGEL DIAZ DIAZ
* CAMPUS: SAN JACINTO
* */
class HiloError extends Thread { // clase que hereda de Thread

  public void run() {
    // Le indicamos al hilo que al inicar debe de ejecutar un bucle y mostrar un mensaje
    for(int i=0; i<2; i++){
      System.out.println("Hola desde el hilo: "+getName());
      try {
        //le indica al hilo que se duerma por 1s
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
    HiloError hilo1 = new HiloError(); // crea un objeto de la clase HiloError
    hilo1.setName("Hilo 1"); // le asigna un nombre al hilo
    try {
      //le inciamos que inice el hilo dos veces
      hilo1.start();
      hilo1.start();
      //espera a que el hilo termine
      hilo1.join();
    }catch (IllegalThreadStateException e){
      //si el hilo no puede iniciarse se muestra el mensaje
      hilo1.interrupt();
      System.out.println("Error: "+e.getMessage());
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
