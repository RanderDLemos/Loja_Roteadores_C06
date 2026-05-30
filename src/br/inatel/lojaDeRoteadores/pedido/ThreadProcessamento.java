package br.inatel.lojaDeRoteadores.pedido;

// TODO: Importar a classe GravadorPedido do pacote utils quando a Pessoa 3 liberar

public class ThreadProcessamento extends Thread {

    private Pedido pedido;

    public ThreadProcessamento(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public void run() {
        System.out.println("\n[THREAD] Iniciando processamento do pedido em segundo plano...");

        try {
            // Simula um tempo de conexao/processamento
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Erro na Thread: " + e.getMessage());
        }

        // TODO: Descomentar a linha abaixo quando a Pessoa 3 criar o gravador
        // GravadorPedido.gravar(this.pedido);

        System.out.println("[THREAD] Processamento finalizado. Arquivo gerado com sucesso!");
    }
}