package Ejercicio1;
/*
*

Sección 1: Atributos de los Hilos
Comprendiendo los atributos básicos de un hilo:
    Crea un hilo que imprima:
    Su nombre.
    Su prioridad.
    Su estado (usando el método getState()).
    Cambia su nombre y prioridad antes de iniciarlo y observa los cambios.

Ejemplo: Muestra cómo configurar
    Thread.currentThread().setName("HiloPrincipal") y
    setPriority(Thread.MAX_PRIORITY).
    Diferenciando el hilo principal de los secundarios:
    Muestra los atributos del hilo principal y, luego, crea e inicia dos hilos
    secundarios que impriman mensajes con sus respectivos atributos.

*
* LUIS ANGEL DIAZ DIAZ
* 2022-01-29
* Ejercicio 1
* */

class MiHilo extends Thread {
    public MiHilo(String nombre) {
        super(nombre);
    }
    @Override
    public void run() {
        System.out.println("Nombre: " + getName());
        System.out.println("Prioridad: " + getPriority());
        System.out.println("Estado: " + getState());
    }
}
public class Ejercicio1 {
    public static void main(String[] args) {
        MiHilo hilo = new MiHilo("Hilo Principal");
        hilo.start();
        hilo.setName("Hilo Principal");
        hilo.setPriority(Thread.MAX_PRIORITY);
        System.out.println("Nombre: " + hilo.getName());
    }

}
