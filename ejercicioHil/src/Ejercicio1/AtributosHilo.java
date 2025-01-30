package Ejercicio1;
/*
*

Ejercicio 1.1: Atributos de un Hilo (10 puntos)
Crea una clase llamada AtributosHilo.
Implementa un hilo que imprima:
  Su nombre.
  su prioridad.
  Su estado (usando getState()).
Cambia el nombre y la prioridad del hilo antes de iniciarlo y muestra los
cambios.
Entregable:
  Archivo: AtributosHilo.java

*
* LUIS ANGEL DIAZ DIAZ
* Ejercicio 1
* */

class AtributosHilo extends Thread {
    public AtributosHilo(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println("Nombre: " + getName());
        System.out.println("Prioridad: " + getPriority());
        System.out.println("Estado: " + getState());
    }
}

class EjemploAtributosHilo {
    public static void main(String[] args) {
      AtributosHilo hilo = new AtributosHilo("Hilo 1");
      hilo.setName("Hilo 2");
      hilo.setPriority(Thread.MAX_PRIORITY);
      hilo.start();

      try{
        hilo.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
}
