package Ejercicio1;
/*
*
* LUIS ANGEL DIAZ DIAZ
* Ejercicio 1
* */

class AtributosHilo extends Thread {
    public AtributosHilo(String name) {
        super(name);
    } // creamos el constructor para asignarle un nombre al hilo

    @Override
    public void run() {
        //sobrescrimos el metodo run y dentro de este le indicamos que imprima los atributos del hilo
        System.out.println("Nombre: " + getName());
        System.out.println("Prioridad: " + getPriority());
        System.out.println("Estado: " + getState());
    }
}

class EjemploAtributosHilo {
    public static void main(String[] args) {
      AtributosHilo hilo = new AtributosHilo("Hilo 1");//creamos un hilo y le asignamos un nombre
      hilo.setName("Hilo 2");//le asignamos un nombre
      hilo.setPriority(Thread.MAX_PRIORITY);//le asignamos un nivel de prioridad
      hilo.start();// iniciamos el hilo

      try{
        hilo.join();// esperamos a que el hilo termine
      } catch (InterruptedException e) {
        e.printStackTrace();// si se produce una excepcion se imprime el error
      }
    }
}
