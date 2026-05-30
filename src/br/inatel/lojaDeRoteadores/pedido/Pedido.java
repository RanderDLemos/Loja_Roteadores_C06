package br.inatel.lojaDeRoteadores.pedido;

import br.inatel.lojaDeRoteadores.cliente.Cliente;
import br.inatel.lojaDeRoteadores.roteadores.Roteador;

public class Pedido {

    private Cliente cliente;
    private Roteador[] roteadores;
    private int totalItens;

    // CONSTRUTOR: Aplicando a Composição forte exigida
    public Pedido(String nomeCliente, String cnpjCliente) {
        // O Cliente é instanciado DENTRO do construtor (Composição)
        this.cliente = new Cliente(nomeCliente, cnpjCliente);

        // Agregação: Preparando o array para receber os roteadores do catálogo externo
        this.roteadores = new Roteador[10];
        this.totalItens = 0;
    }

}