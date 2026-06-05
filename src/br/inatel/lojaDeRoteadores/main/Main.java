package br.inatel.lojaDeRoteadores.main;

import br.inatel.lojaDeRoteadores.exception.EntradaInvalidaException;
import br.inatel.lojaDeRoteadores.exception.EstoqueEsgotadoException;
import br.inatel.lojaDeRoteadores.exception.RoteadorInvalidoException;
import br.inatel.lojaDeRoteadores.pedido.GravarPedido; // <-- Não esqueça de importar!
import br.inatel.lojaDeRoteadores.pedido.Pedido;
import br.inatel.lojaDeRoteadores.roteadores.Roteador;
import br.inatel.lojaDeRoteadores.roteadores.RoteadorEmpresarial;
import br.inatel.lojaDeRoteadores.roteadores.RoteadorEspecial;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        // 1. Montando o catálogo polimórfico
        Roteador[] catalogo = new Roteador[6];

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

        Pedido pedidoAtual = new Pedido(nomeCliente, cnpjCliente);

        System.out.println("Pedido iniciado com sucesso. Preparando catálogo...");

        System.out.println("-- Roteadores disponiveis --");
        for(int i = 0; i < catalogo.length; i++){
            System.out.print((i + 1) + ". ");
            catalogo[i].mostraInfo();
        }
        System.out.println("0. Finalizar Pedido.");
        System.out.println("____________________");

        boolean comprando = true;
        while (comprando) {
            System.out.print("Digite o código do roteador desejado: ");
            try {
                int codigo = entrada.nextInt();

                if (codigo == 0) {
                    comprando = false;

                } else if (codigo < 1 || codigo > 6) {
                    throw new RoteadorInvalidoException("Código inválido! Digite entre 1 e 6.");

                } else {
                    pedidoAtual.adicionarRoteador(catalogo[codigo - 1]);
                    System.out.println("Roteador adicionado ao pedido!");
                }

            } catch (InputMismatchException e) {
                entrada.nextLine(); // limpa o buffer — obrigatório!
                // Aqui você pode imprimir a mensagem ou jogar a sua EntradaInvalidaException se preferir
                System.out.println("Entrada inválida! Digite apenas números inteiros.");

            } catch (RoteadorInvalidoException e) {
                System.out.println(e.getMessage());

            } catch (EstoqueEsgotadoException e) {
                System.out.println(e.getMessage());
            }
        }

        // Finalização — após sair do loop
        System.out.println("\n=== RESUMO DO PEDIDO ===");
        pedidoAtual.processarPedido();

        // Guardando o total para poder enviar para o TXT
        float totalCompra = pedidoAtual.calcularTotalCompra();
        System.out.printf("Total da compra: R$ %.2f%n", totalCompra);
        System.out.println("========================");

        // 3. Chamando a classe que gera o arquivo TXT
        GravarPedido gravador = new GravarPedido();
        gravador.gravar(nomeCliente, cnpjCliente, totalCompra);

        entrada.close();
    }
}