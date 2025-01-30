package EjercicioFinal;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class CuentaBancaria {
  private double saldo;
  private int id;
  Lock lock = new ReentrantLock();

  public CuentaBancaria(int id, int saldo) {
    this.saldo = saldo;
    this.id = id;
  }

  public double getSaldo() {
    return saldo;
  }

  public int getId() {
    return id;
  }

  public boolean retirar(double cantidad) {
    lock.lock();
    try {
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
;
  public boolean depositar(double cantidad) {
    lock.lock();
    try {
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

  public void transferencia(double cantidad,CuentaBancaria cuentaDestino) {
    lock.lock();
    try {
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
    CuentaBancaria[] cuentas = {
        new CuentaBancaria(1, 1000),
        new CuentaBancaria(2, 2000),
        new CuentaBancaria(3, 3000),
        new CuentaBancaria(4,4000)
    };
    Random random = new Random();
    int numeroHilos = cuentas.length -2;
    Thread[] hilos = new Thread[numeroHilos];
    for(int i=0;i<numeroHilos;i++){
      hilos[i] = new Thread(()-> {
        for(int j=0;j<3;j++){
          int cuentaOrigen = random.nextInt(cuentas.length);
          int cuentaDestino = random.nextInt(cuentas.length);
          while(cuentaOrigen==cuentaDestino){
            cuentaDestino = random.nextInt(cuentas.length);
          }
          double monto = 50 + random.nextInt(100);
          cuentas[cuentaOrigen].transferencia(monto,cuentas[cuentaDestino]);

          try{
            Thread.sleep(100);
          }catch(InterruptedException e){
            e.printStackTrace();
          }
        };

      });
      hilos[i].start();
    };

    for(Thread hilo:hilos){
      try{
        hilo.join();
      }catch(InterruptedException e){
        e.printStackTrace();
      }
    }
    System.out.println("Saldo de las cuentas: ");
    for(CuentaBancaria cuenta:cuentas){
      System.out.println("Cuenta " + cuenta.getId() + ": " + cuenta.getSaldo());
    }

  }


}
