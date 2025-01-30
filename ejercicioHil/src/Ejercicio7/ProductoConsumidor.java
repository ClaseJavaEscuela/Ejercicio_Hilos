package Ejercicio7;

class Data{
  private int[] data;

  public Data(int maximaData){
    this.data = new int[maximaData];
  }

  public synchronized void ingresarData(int dato, int indice){
    if(indice < data.length ){
      System.out.println("El dato ingresado es: " + dato);
      this.data[indice] = dato;
      notifyAll();
    }
  }

  public synchronized void sacarDatos(int indice){
    while(data.length == 0){
      try{
        System.out.println("Esperando datos");
        wait();
      }catch(InterruptedException e){
        e.printStackTrace();
      }
    }

    if(indice < data.length){
      System.out.println("Sacando el dato: " + data[indice]);
    }

  }

}

public class ProductoConsumidor {
  private static int limiteData = 10;

  public static void main(String[] args) {
    Data data = new Data(limiteData);

    Thread productor = new Thread(() -> {
      for (int i = 0; i < limiteData; i++) {
        try {
          data.ingresarData(i+1, i);
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    Thread consumidor = new Thread(() -> {
      for (int i = 0; i < limiteData; i++) {
        try {
          data.sacarDatos(i);
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    productor.start();
    consumidor.start();

    try {
      productor.join();
      consumidor.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
