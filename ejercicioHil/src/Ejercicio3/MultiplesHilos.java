package Ejercicio3;
/*
*   LUIS ANGEL DIAZ DIAZ
* CAMPUS: SAN JACINTO
* */
class Hilo extends Thread {// clase Hilo hereda de la clase Thread

    public void run() {
      // codigo del hilo
      // al inicar el hilo debe de ejecutarse dos veces el ciclo for y mostrar el nombre del hilo cada segundo
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
* El orden de ejecucion de los hilos no tiene algun orden
* en algunas ocasiones se ejecutara primero el hilo 1
* en otras ocasiones se ejecutara primero el hilo 2
* en otras ocasiones se ejecutara primero el hilo 3
* */
public class MultiplesHilos {
    public static void main(String[] args) {
      // creamos los hilos y les asignamos un nombre
        Hilo hilo1 = new Hilo();
        hilo1.setName("Hilo 1");
        Hilo hilo2 = new Hilo();
        hilo2.setName("Hilo 2");
        Hilo hilo3 = new Hilo();
        hilo3.setName("Hilo 3");
        // iniciamos los hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();

        try{
          // esperamos a que los hilos terminen de ejecutarse
            hilo1.join();
            hilo2.join();
            hilo3.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }

    }
}
