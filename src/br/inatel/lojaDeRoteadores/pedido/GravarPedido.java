// GravarPedido.java
package br.inatel.lojaDeRoteadores.pedido;

import java.io.FileWriter;
import java.io.IOException;

public class GravarPedido {
    public void gravar(String nomeCliente, String cnpj, float total) {
        // try-with-resources — fecha o arquivo automaticamente ao terminar
        try (FileWriter fw = new FileWriter("pedidos.txt", true)) {
            fw.write("======= PEDIDO =======\n");
            fw.write("Cliente: " + nomeCliente + "\n");
            fw.write("CNPJ: " + cnpj + "\n");
            fw.write("Total: R$ " + String.format("%.2f", total) + "\n");
            fw.write("======================\n\n");
            System.out.println("Pedido gravado com sucesso em pedidos.txt!");
        } catch (IOException e) {
            System.out.println("Erro ao gravar pedido: " + e.getMessage());
        }
    }
}