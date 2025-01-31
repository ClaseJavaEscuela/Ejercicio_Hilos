package Ejercicio2;
/*
* LUIS ANGEL DIAZ DIAZ
* CAMPUS:SAN JACINTO
* */
class HiloRunnableComparacion implements Runnable {//implementamos una interfaz Runnable en la clase HiloRunnableComparacion
  private int limite; //declaramos un atributo de tipo int llamado limite el cual indica cuantos mensjes por pantalla debe de imprimir

  // declaramos un constructor que recibe un parametro de tipo int llamado limite el cual indica cuantos mensjes por pantalla debe de imprimir
  public HiloRunnableComparacion(int limite) {
    this.limite = limite;
  }

  @Override
  public void run() {
    //declaramos un bucle for que recorre desde 0 hasta el limite y imprime el mensaje "hilo Runnable: " seguido del valor de i
    for (int i = 0; i < this.limite; i++) {
      System.out.println("hilo Runnable: " + i);
    }
  }

}

class HiloThreadComparacion extends Thread {//declaramos una clase llamada HiloThreadComparacion que extiende la clase Thread
  private int limite;//declaramos un atributo de tipo int llamado limite el cual indica cuantos mensjes por pantalla debe de imprimir

  //declaramos un constructor que recibe un parametro de tipo int llamado limite el cual indica cuantos mensjes por pantalla debe de imprimir
  public HiloThreadComparacion(int limite) {
    this.limite = limite;
  }

  @Override
  public void run() {
    //declaramos un bucle for que recorre desde 0 hasta el limite y imprime el mensaje "Hilo Thread: " seguido del valor de i
    for (int i = 0; i < this.limite; i++) {
      System.out.println("Hilo Thread: " + i);
    }
  }

}

public class ComparacionesHilos {
//declaramos el metodo main que es el punto de entrada de la aplicacion
  public static void main(String[] args) {
    //declaramos un entero llamado limite el cual indica cuantos mensjes por pantalla debe de imprimir
    int limite = 10;

    HiloThreadComparacion hilo1 = new HiloThreadComparacion(limite);//declaramos un objeto de tipo HiloThreadComparacion y le pasamos el valor de limite
    hilo1.start();//iniciamos el hilo

    Thread hilo2 = new Thread(new HiloRunnableComparacion(limite));//declaramos un objeto de tipo Thread y le pasamos un objeto de tipo HiloRunnableComparacion y le pasamos el valor de limite
    hilo2.start();//iniciamos el hilo

    try{
      //esperamos a que los hilos terminen
      hilo1.join();
      hilo2.join();
    }catch(Exception e){
      e.printStackTrace();
    }

  }
}
/*
Diferencias:
1._ HiloThreadComparacion extiende la clase `Thread`, lo que significa que no puede extender otra clase.
2._ HiloRunnableComparacion implementa `Runnable`, lo que permite a la clase seguir heredando de otra clase si es necesario.
3._ En ThreadExample, el método run() se sobrescribe directamente en la clase Thread.
4._ En RunnableExample, se debe pasar una instancia de la clase a un objeto `Thread` para ejecutarlo.*/
