package Ejercicio2;
/*
* LUIS ANGEL DIAZ DIAZ
* CAMPUS:SAN JACINTO
* */
class HiloRunnableComparacion implements Runnable {
  private int limite;

  public HiloRunnableComparacion(int limite) {
    this.limite = limite;
  }

  @Override
  public void run() {
    for (int i = 0; i < this.limite; i++) {
      System.out.println("hilo Runnable: " + i);
    }
  }

}

class HiloThreadComparacion extends Thread {
  private int limite;

  public HiloThreadComparacion(int limite) {
    this.limite = limite;
  }

  @Override
  public void run() {
    for (int i = 0; i < this.limite; i++) {
      System.out.println("Hilo Thread: " + i);
    }
  }

}

public class ComparacionesHilos {

  public static void main(String[] args) {
    int limite = 10;

    HiloThreadComparacion hilo1 = new HiloThreadComparacion(limite);
    hilo1.start();

    Thread hilo2 = new Thread(new HiloRunnableComparacion(limite));
    hilo2.start();

    try{
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
