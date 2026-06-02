package br.inatel.lojaDeRoteadores.pedido;

import br.inatel.lojaDeRoteadores.cliente.Cliente;
import br.inatel.lojaDeRoteadores.roteadores.Roteador;
// TODO: Importar a EstoqueEsgotadoException do pacote de excecoes quando a Pessoa 3 criar

public class Pedido {

    private Cliente cliente;
    private Roteador[] roteadores;
    private int totalItens;

    public Pedido(String nomeCliente, String cnpjCliente) {
        this.cliente = new Cliente(nomeCliente, cnpjCliente);
        this.roteadores = new Roteador[10];
        this.totalItens = 0;
    }

    // TODO: Adicionar "throws EstoqueEsgotadoException" na assinatura do metodo depois
    public void adicionarRoteador(Roteador roteador) {
        boolean adicionou = false;

        for (int i = 0; i < roteadores.length; i++) {
            if (roteadores[i] == null) {
                roteadores[i] = roteador;
                this.totalItens++;
                adicionou = true;
                System.out.println("Roteador adicionado ao carrinho com sucesso!");
                break;
            }
        }

        if (!adicionou) {
            // TODO: Substituir o print por: throw new EstoqueEsgotadoException("Carrinho cheio!");
            System.out.println("Erro: O limite do carrinho está cheio.");
        }
    }

    public float calcularTotalCompra() {
        float total = 0;

        for (int i = 0; i < roteadores.length; i++) {
            if (roteadores[i] != null) {
                // Polimorfismo sendo executado aqui:
                total += roteadores[i].calcularTotal();
            }
        }
        return total;
    }

    public void processarPedido() {
        System.out.println("\n===== RESUMO DO PEDIDO =====");

        for (int i = 0; i < roteadores.length; i++) {
            if (roteadores[i] != null) {
                roteadores[i].mostraInfo();
                System.out.println("----------------------------");
            }
        }
    }
}