package br.inatel.lojaDeRoteadores.main;

import br.inatel.lojaDeRoteadores.pedido.Pedido;
import br.inatel.lojaDeRoteadores.roteadores.Roteador;
import br.inatel.lojaDeRoteadores.roteadores.RoteadorEmpresarial;
import br.inatel.lojaDeRoteadores.roteadores.RoteadorEspecial;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        // 1. Montando o catálogo polimórfico
        Roteador[] catalogo = new Roteador[6];

        // Instâncias que a Pessoa 2 vai criar (Pode dar erro de compilação até ela subir a parte dela, ignore!)
        catalogo[0] = new RoteadorEmpresarial("Catalyst 8300", 15000.00f, 500, "Cisco");
        catalogo[1] = new RoteadorEmpresarial("ISR 1101", 8500.00f, 200, "Cisco");
        catalogo[2] = new RoteadorEmpresarial("Catalyst 8500", 25000.00f, 1000, "Cisco");

        catalogo[3] = new RoteadorEspecial("IR1101", 12000.00f, "Industrial", "5G/LTE");
        catalogo[4] = new RoteadorEspecial("ASR 1001-X", 35000.00f, "Borda", "Fibra/SFP+");
        catalogo[5] = new RoteadorEspecial("Meraki MX67", 6500.00f, "SD-WAN", "Cloud/Firewall");

        // 2. Coletando dados para a Composição do Pedido
        System.out.println("Bem-vindo ao sistema de pedidos de Roteadores!");
        System.out.print("Digite o nome do Cliente: ");
        String nomeCliente = entrada.nextLine();

        System.out.print("Digite o CNPJ do Cliente: ");
        String cnpjCliente = entrada.nextLine();

        // A Composição que tirou ponto no seu semestre passado acontece aqui dentro!
        Pedido pedidoAtual = new Pedido(nomeCliente, cnpjCliente);

        System.out.println("Pedido iniciado com sucesso. Preparando catálogo...");

        entrada.close();
    }
}