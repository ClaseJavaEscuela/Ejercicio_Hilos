package Ejercicio5;

import java.util.Scanner;
/*
* LUIS ANGEL DIAZ DIAZ
* CAMPUS: SAN JACINTO
* */
public class HiloSuspender extends Thread {
  // declaramos dos variables para poder simular el control del hilo
  // una variable para simular una suspension del hilo
  // otra para indicar que el hilo debe terminar
  private volatile boolean suspendido = true;
  private volatile boolean terminar = false;
  @Override
  public void run(){
    // este hilo ejecuta un bucle infinito
    while(!terminar){
      // el hilo se ejecuta mientras no este suspendido
      // y tambien mientras no se haya indicado que el hilo debe terminar
      if(!suspendido) {
        // se imprime un mensaje indicando que el hilo esta ejecutando
        System.out.println("\nHilo " + this.getName() + " ejecutando");
        System.out.print("\nIngrese su opcion: ");
        try {
          // se espera 1 segundo
          sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  // metodo para suspender el hilo
  public void suspender(){
    suspendido = true;
  }

  // metodo para continuar el hilo
  public void continuar(){
    suspendido = false;
  }

  // metodo para terminar el hilo
  public void terminar(){
    terminar = true;
  }

}
class MainSuspenderHilos{
  static Scanner input = new Scanner(System.in);//Scanner para leer la entrada del usuario
  private static int opcion; // variable para almacenar la opcion del usuario
  public static void main(String[] args) {
    HiloSuspender hilo1 = new HiloSuspender(); // creamos un hilo
    hilo1.setName("mi Hilito");// le damos un nombre al hilo
    hilo1.start();// iniciamos el hilo
    while(true){
      System.out.println("------------------------ MENU DE OPCIONES ---------------");
      System.out.println("Ingrese el numero de la opcion que desea realizar");
      System.out.println("1. Suspende el hilo");
      System.out.println("2. Continua el hilo");
      System.out.println("3. Termina el hilo");
      System.out.print("Opcion: ");
      /*
      * Dependiendo de la opcion ingresa por el usuario el hilo se comportada de una forma u otroa
      * puede suspender el hilo
      * puede hacer que el hilo continua
      * puede terminar el hilo
      * */
      try{
        opcion = input.nextInt();
      }catch(Exception e){
        // si se ingresa un valor diferente a un numero entero
        System.out.println("Opcion invalida");
      }
      if(opcion == 1){
        hilo1.suspender();
        System.out.println("Hilo suspendido");
      }
      if(opcion == 2){
        hilo1.continuar();
      }
      if(opcion == 3){
        hilo1.terminar();
        System.out.println("hilo terminado");
        break;
      }
      if(opcion< 1 || opcion > 3){
        // controlamos el ingreso de opciones no validas
        System.out.println("Opcion invalida");
      }
    }
    try{
      //esperamos a que el hilo termine
      hilo1.join();
    }catch(InterruptedException e){
      e.printStackTrace();
    }
  }
}

