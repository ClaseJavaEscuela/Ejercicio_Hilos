package Ejercicio2;
/*
* LUIS ANGEL DIAZ DIAZ
* CAMPUS: SAN JACINTO
* */
class HiloExtendido extends  Thread {//Hilo que hereda de la clase Thread
  @Override
  public void run() {
    //Se ejecuta el hilo
    System.out.println("Mensaje desde el hilo: "+getName());
    //Declaramos un for para que se ejecute 5 veces y mostramos un mensaje por cada iteracion
    for(int i=0;i<5;i++){
      System.out.println("mensaje numero: "+(i+1));
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

  }

}
class EjemploHiloExtendido {//Hilo principal
  public static void main(String[] args) {
    //Creamos un objeto de la clase HiloExtendido
    HiloExtendido hilo1 = new HiloExtendido();
    //Asignamos un nombre al hilo y le indicamos que inicie
    hilo1.setName("Hilo 1");
    hilo1.start();
    try {
      //Esperamos a que el hilo termine
      hilo1.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
