package br.inatel.lojaDeRoteadores.main;

import br.inatel.lojaDeRoteadores.exception.EntradaInvalidaException;
import br.inatel.lojaDeRoteadores.exception.EstoqueEsgotadoException;
import br.inatel.lojaDeRoteadores.exception.RoteadorInvalidoException;
import br.inatel.lojaDeRoteadores.pedido.GravarPedido;
import br.inatel.lojaDeRoteadores.pedido.Pedido;
import br.inatel.lojaDeRoteadores.roteadores.Roteador;
import br.inatel.lojaDeRoteadores.roteadores.RoteadorEmpresarial;
import br.inatel.lojaDeRoteadores.roteadores.RoteadorEspecial;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void limparTela() {
        // Esse código ANSI move o cursor para o topo (H) e limpa a tela (2J)
        System.out.print("\u001b[H\u001b[2J");
        System.out.flush();
    }

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        // 1. Montando o catálogo polimórfico (Fora do loop para não recriar toda hora)
        Roteador[] catalogo = new Roteador[6];

        catalogo[0] = new RoteadorEmpresarial("Catalyst 8300", 15000.00f, 500, "Cisco");
        catalogo[1] = new RoteadorEmpresarial("ISR 1101", 8500.00f, 200, "Cisco");
        catalogo[2] = new RoteadorEmpresarial("Catalyst 8500", 25000.00f, 1000, "Cisco");

        catalogo[3] = new RoteadorEspecial("IR1101", 12000.00f, "Industrial", "5G/LTE");
        catalogo[4] = new RoteadorEspecial("ASR 1001-X", 35000.00f, "Borda", "Fibra/SFP+");
        catalogo[5] = new RoteadorEspecial("Meraki MX67", 6500.00f, "SD-WAN", "Cloud/Firewall");

        boolean sistemaRodando = true;

        // Loop principal do sistema (Permite fazer novos pedidos ou sair)
        while (sistemaRodando) {
            limparTela();
            System.out.println("Bem-vindo ao sistema de pedidos de Roteadores!");
            System.out.print("Digite o nome do Cliente: ");
            String nomeCliente = entrada.nextLine();

            // Validação de CNPJ
            String cnpjCliente = "";
            boolean cnpjValido = false;
            while (!cnpjValido) {
                System.out.print("Digite o CNPJ do Cliente (14 números): ");
                cnpjCliente = entrada.nextLine();

                // Remove pontos, traços e barras, deixando só os números
                String apenasNumeros = cnpjCliente.replaceAll("\\D", "");

                if (apenasNumeros.length() == 14) {
                    cnpjValido = true;
                    cnpjCliente = apenasNumeros;
                } else {
                    System.out.println("CNPJ inválido! O CNPJ deve conter exatamente 14 dígitos.");
                }
            }

            Pedido pedidoAtual = new Pedido(nomeCliente, cnpjCliente);
            System.out.println("Pedido iniciado com sucesso. Preparando catálogo...\n");

            boolean comprando = true;

            // Loop de compras do cliente atual
            while (comprando) {
                System.out.println("-- Roteadores disponíveis --");
                for (int i = 0; i < catalogo.length; i++) {
                    System.out.print((i + 1) + ". ");
                    catalogo[i].mostraInfo();
                }
                System.out.println("0. Finalizar Pedido.");
                System.out.println("____________________");
                System.out.print("Digite o código do roteador desejado: ");

                try {
                    int codigo = entrada.nextInt();

                    if (codigo == 0) {
                        comprando = false; // encerra o loop de compras

                    } else if (codigo < 1 || codigo > 6) {
                        throw new RoteadorInvalidoException("Código inválido! Digite entre 1 e 6.\n");

                    } else {
                        // Conecta o roteador (certifique-se de que o método conectar() existe na classe Roteador)
                        // catalogo[codigo - 1].conectar();

                        System.out.print("\nDeseja conectar este roteador agora? (S/N): ");
                        String opcaoConexao = entrada.next();

                        if (opcaoConexao.equalsIgnoreCase("S")) {
                            catalogo[codigo - 1].conectar();
                        }

                        pedidoAtual.adicionarRoteador(catalogo[codigo - 1]);
                        System.out.println("\n-> Roteador adicionado ao pedido com sucesso!\n");

                        pedidoAtual.adicionarRoteador(catalogo[codigo - 1]);
                        System.out.println("\n-> Roteador adicionado ao pedido com sucesso!\n");
                    }

                } catch (InputMismatchException e) {
                    entrada.nextLine(); // limpa o buffer
                    System.out.println("\n-> Entrada inválida! Digite apenas números inteiros.\n");

                } catch (RoteadorInvalidoException e) {
                    System.out.println("\n-> " + e.getMessage());

                } catch (EstoqueEsgotadoException e) {
                    System.out.println("\n-> " + e.getMessage());
                }
            }

            // Finalização — após sair do loop de compras
            limparTela();
            System.out.println("\n=== RESUMO DO PEDIDO ===");
            pedidoAtual.processarPedido();
            float totalCompra = pedidoAtual.calcularTotalCompra();
            System.out.printf("Total da compra: R$ %.2f%n", totalCompra);
            System.out.println("========================");

            // Grava o arquivo TXT
            GravarPedido gravador = new GravarPedido();
            gravador.gravar(nomeCliente, cnpjCliente, totalCompra);

            // Pergunta o que o usuário quer fazer a seguir
            System.out.println("\nO que deseja fazer agora?");
            System.out.println("1. Novo Pedido");
            System.out.println("2. Encerrar Sistema");
            System.out.print("Opção: ");

            int opcao = entrada.nextInt();
            entrada.nextLine(); // Limpa o buffer após o nextInt()

            if (opcao == 2) {
                sistemaRodando = false;
                limparTela();
                System.out.println("Muito obrigado pela preferencia!");
                System.out.println("Seu pedido já vai começar a ser preparado...");
            }
        }

        entrada.close();
    }
}