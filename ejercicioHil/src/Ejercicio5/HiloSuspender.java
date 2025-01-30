package Ejercicio5;

import java.util.Scanner;

public class HiloSuspender extends Thread {
  private volatile boolean suspendido = true;
  private volatile boolean terminar = false;
  @Override
  public void run(){
    while(!terminar){
      if(!suspendido) {
        System.out.println("Hilo " + this.getName() + " ejecutando");
        System.out.print("Ingrese su opcion: ");
        try {
          sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }else{
        System.out.println("El hilo esta suspendido");
      }
    }
  }

  public void suspender(){
    suspendido = true;
  }

  public void continuar(){
    suspendido = false;
  }

  public void terminar(){
    terminar = true;
  }

}
class MainSuspenderHilos{
  static Scanner input = new Scanner(System.in);
  private static int opcion;
  public static void main(String[] args) {
    HiloSuspender hilo1 = new HiloSuspender();
    hilo1.setName("mi Hilito");
    hilo1.start();
    while(true){
      System.out.println("------------------------ MENU DE OPCIONES ---------------");
      System.out.println("Ingrese el numero de la opcion que desea realizar");
      System.out.println("1. Suspende el hilo");
      System.out.println("2. Continua el hilo");
      System.out.println("3. Termina el hilo");
      System.out.print("Opcion: ");
      try{
        opcion = input.nextInt();
      }catch(Exception e){
        System.out.println("Opcion invalida");
      }
      if(opcion == 1){
        hilo1.suspender();
      }
      if(opcion == 2){
        hilo1.continuar();
      }
      if(opcion == 3){
        hilo1.terminar();
        System.out.println("hilo terminado");
        break;
      }
    }
    try{
      hilo1.join();
    }catch(InterruptedException e){
      e.printStackTrace();
    }
  }
}

