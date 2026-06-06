package br.inatel.lojaDeRoteadores.pedido;

import br.inatel.lojaDeRoteadores.cliente.Cliente;
import br.inatel.lojaDeRoteadores.exception.EstoqueEsgotadoException;
import br.inatel.lojaDeRoteadores.roteadores.Roteador;

public class Pedido {

    private Cliente cliente;
    private Roteador[] roteadores;
    private int totalItens;

    public Pedido(String nomeCliente, String cnpjCliente) {
        this.cliente = new Cliente(nomeCliente, cnpjCliente);
        this.roteadores = new Roteador[10];
        this.totalItens = 0;
    }

    public void adicionarRoteador(Roteador roteador) throws EstoqueEsgotadoException {
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
            System.out.println("Erro: O limite do carrinho está cheio.");
        }
    }

    public float calcularTotalCompra() {
        float total = 0;

        for (int i = 0; i < roteadores.length; i++) {
            if (roteadores[i] != null) {
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