package Ejercicio6;
/*
* LUIS ANGEL DIAZ DIAZ
* CAMPUS:SAN JACINTO
* */
class Tarea extends Thread {// la clase tarea hereda de la clase thread
  private int tiempo;
  @Override
  public void run() {
    //le decimos como debe de comportarse
    System.out.println("Tarea: "+getName()+" nivel de prioridada: "+getPriority());
    //dependiendo del nivel de tarea se ejecuta un tiempo distinto
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
    //declaramos las tareass le asignamos un nombre y un nivel de prioridad
    Tarea procesarDatos = new Tarea();
    procesarDatos.setPriority(Thread.MAX_PRIORITY);
    procesarDatos.setName("Procesar Datos");

    Tarea RegistrosEnDisco = new Tarea();
    RegistrosEnDisco.setPriority(Thread.MIN_PRIORITY);
    RegistrosEnDisco.setName("Guardar Registros en Disco");

    //iniciamos las tareas
    procesarDatos.start();
    RegistrosEnDisco.start();

    try{
      //esperamos a que terminen las tareas
      procesarDatos.join();
      RegistrosEnDisco.join();
    }catch(InterruptedException e){
      System.out.println("Error: "+e);
    }

  }
}
