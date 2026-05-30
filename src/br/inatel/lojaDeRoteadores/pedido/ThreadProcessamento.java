package br.inatel.lojaDeRoteadores.pedido;

public class ThreadProcessamento extends Thread {

    private Pedido pedido;

    // Construtor para receber o pedido atual
    public ThreadProcessamento(Pedido pedido) {
        this.pedido = pedido;
    }

}