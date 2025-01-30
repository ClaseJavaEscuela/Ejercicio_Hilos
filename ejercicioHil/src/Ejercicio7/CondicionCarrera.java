package Ejercicio7;
class ContadorSinSyncronized{
    private int contador = 0;
    public void incrementar(){
        contador++;
    }
    public int getContador(){
        return contador;
    }
}
class ContadorConSyncronized{
  private int contador = 0;
  public synchronized void incrementar(){
    contador++;
  }
  public int getContador(){
    return contador;
  }
}
public class CondicionCarrera {
  public static void main(String[] args) {
    ContadorSinSyncronized contador = new ContadorSinSyncronized();
    ContadorConSyncronized contadorConSyncronized = new ContadorConSyncronized();

    Runnable tarea = () -> {
      for (int i = 0; i < 1000; i++) {
        contador.incrementar();
      }
    };
    Runnable tareaConSyncronized = () -> {
      for (int i = 0; i < 1000; i++) {
        contadorConSyncronized.incrementar();
      }
    };

    Thread hilo1 = new Thread(tarea);
    Thread hilo2 = new Thread(tarea);

    Thread hilo1Syncronized = new Thread(tareaConSyncronized);
    Thread hilo2Syncronized = new Thread(tareaConSyncronized);

    hilo1.start();
    hilo2.start();

    hilo1Syncronized.start();
    hilo2Syncronized.start();

    try{
      hilo1.join();
      hilo2.join();
      hilo1Syncronized.join();
      hilo2Syncronized.join();
    }catch(InterruptedException e){
      e.printStackTrace();
    }

    System.out.println("Contador: "+contador.getContador());
    System.out.println("Contador con Syncronized: "+contadorConSyncronized.getContador());

  }

}
