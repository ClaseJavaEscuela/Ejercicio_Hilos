package Ejercicio6;

class Tarea extends Thread {
  private int tiempo;
  @Override
  public void run() {
    System.out.println("Tarea: "+getName()+" nivel de prioridada: "+getPriority());
    if (getPriority() == 1){
      this.tiempo = 100;
    }
    if (getPriority() == 5){
      this.tiempo = 200;
    }
    if(getPriority() == 10){
      this.tiempo = 300;
    }
    try{
      Thread.sleep(1000);
      System.out.println("Tarea: "+getName()+" terminada");
    }catch (InterruptedException e){
      System.out.println("Error: "+e);
    }
  }
}

public class SistemaTareas {
  public static void main(String[] args) {
    Tarea procesarDatos = new Tarea();
    procesarDatos.setPriority(Thread.MAX_PRIORITY);
    procesarDatos.setName("Procesar Datos");

    Tarea RegistrosEnDisco = new Tarea();
    RegistrosEnDisco.setPriority(Thread.MIN_PRIORITY);
    RegistrosEnDisco.setName("Guardar Registros en Disco");

    procesarDatos.start();
    RegistrosEnDisco.start();

    try{
      procesarDatos.join();
      RegistrosEnDisco.join();
    }catch(InterruptedException e){
      System.out.println("Error: "+e);
    }

  }
}
