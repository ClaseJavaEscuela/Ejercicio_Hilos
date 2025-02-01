package Ejercicio7;
/*
* LUIS ANGEL DIAZ DIAZ
* CAMPUS: SAN JACINTO
* */
class Data{
  private int[] data;


  //contructor que inicializa el array
  public Data(int maximaData){
    this.data = new int[maximaData];
  }

  //Metodo sincronizado para agregar datos al array
  public synchronized void ingresarData(int dato, int indice) throws InterruptedException {
    if(indice < data.length ){
      System.out.println("El dato ingresado es: " + dato);
      this.data[indice] = dato;
      notifyAll();
    }
  }

  // Metodo sincronizado para sacar datos del array
  public synchronized void sacarDatos(int indice){
    while(data.length == 0){
      try{
        System.out.println("Esperando datos");
        wait();
      }catch(InterruptedException e){
        e.printStackTrace();
      }
    }
    System.out.println("El dato sacado es: " + data[indice]);

  }

}

public class ProductoConsumidor {
  private static int limiteData = 10;

  public static void main(String[] args) {
    Data data = new Data(limiteData);
    // Hilo productor que ingresa datos cada segundo
    Thread productor = new Thread(() -> {
      for (int i = 0; i < limiteData; i++) {
        try {
          data.ingresarData(i+1, i);
          Thread.sleep(900);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    // Hilo consumidor que saca datos despues de 1 segundo
    Thread consumidor = new Thread(() -> {
      for (int i = 0; i < limiteData; i++) {
        try {
          data.sacarDatos(i);
          Thread.sleep(950);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    // inicamos los hilos
    productor.start();
    consumidor.start();

    try {
      // esperamos a que los hilos terminen
      productor.join();
      consumidor.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
