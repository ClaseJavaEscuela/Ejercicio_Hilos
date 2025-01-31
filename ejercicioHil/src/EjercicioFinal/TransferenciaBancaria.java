package EjercicioFinal;
/*
* LUIS ANGEL DIAZ DIAZ
* */
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Clase que representa una cuenta bancaria con saldo y un identificador
class CuentaBancaria {
  private double saldo;
  private int id;
  Lock lock = new ReentrantLock();// Lock para evitar problemas de concurrencia

  public CuentaBancaria(int id, int saldo) {
    this.saldo = saldo;
    this.id = id;
  }

  // Métodos para obtener saldo e ID de la cuenta
  public double getSaldo() {
    return saldo;
  }

  public int getId() {
    return id;
  }

  // Metodo para retirar dinero de la cuenta
  public boolean retirar(double cantidad) {
    lock.lock();
    try {
      // validamos que la cantidad no sea mayor al saldo
      if (cantidad > saldo) {
        System.out.println("No hay suficiente saldo en la cuenta " + id);
        return false;
      }
      saldo -= cantidad;
      System.out.println("Se ha retirado " + cantidad + " de la cuenta " + id);
      return  true;
    } finally {
      lock.unlock();
    }
  }

  // Metodo para depositar dinero en la cuenta
  public boolean depositar(double cantidad) {
    lock.lock();
    try {
      //Validamos que la cantidad no sea menor o igual a cero
      if(cantidad<=0){
        System.out.println("Cantidad no valida");
        return false;
      }
      saldo += cantidad;
      System.out.println("Se ha depositado " + cantidad + " en la cuenta " + id);
      return true;
    } finally {
      lock.unlock();
    }
  };

  // Método para hacer una transferencia entre cuentas
  public void transferencia(double cantidad,CuentaBancaria cuentaDestino) {
    lock.lock();
    try {
      // validamos las operaciones necesarias para la tranferencia
      if (!this.retirar(cantidad)) {
        System.out.println("Error en el deposito");
      }
      if (!cuentaDestino.depositar(cantidad)) {
        System.out.println("Error en el depositar en la cuenta detino");
      }
      System.out.println("Se ha transferido " + cantidad + " de la cuenta " + id + " a la cuenta " + cuentaDestino.id);

    } finally {
      lock.unlock();
    }

  }
}

public class TransferenciaBancaria {
  public static void main(String[] args) {
    // Creamos un array de cuentas bancarias
    CuentaBancaria[] cuentas = {
        new CuentaBancaria(1, 1000),
        new CuentaBancaria(2, 2000),
        new CuentaBancaria(3, 3000),
        new CuentaBancaria(4,4000)
    };
    Random random = new Random();
    int numeroHilos = cuentas.length -2;// Número de hilos que harán transferencias

    Thread[] hilos = new Thread[numeroHilos];

    for(int i=0;i<numeroHilos;i++){
      hilos[i] = new Thread(()-> {
        // Cada hilo hace 3 transferencias
        for(int j=0;j<3;j++){
          int cuentaOrigen = random.nextInt(cuentas.length);
          int cuentaDestino = random.nextInt(cuentas.length);
          // Aseguramos que la cuenta origen y destino sean diferentes
          while(cuentaOrigen==cuentaDestino){
            cuentaDestino = random.nextInt(cuentas.length);
          }
          // Monto aleatorio para la transferencia
          double monto = 50 + random.nextInt(100);
          cuentas[cuentaOrigen].transferencia(monto,cuentas[cuentaDestino]);

          try{
            //le damos un retraso para la transferencia
            Thread.sleep(100);
          }catch(InterruptedException e){
            e.printStackTrace();
          }
        };

      });
      hilos[i].start();
    };

    // Esperamos a que todos los hilos terminen
    for(Thread hilo:hilos){
      try{
        hilo.join();
      }catch(InterruptedException e){
        e.printStackTrace();
      }
    }

    // Mostramos el saldo de todas las cuentas
    System.out.println("Saldo de las cuentas: ");
    for(CuentaBancaria cuenta:cuentas){
      System.out.println("Cuenta " + cuenta.getId() + ": " + cuenta.getSaldo());
    }

  }


}
