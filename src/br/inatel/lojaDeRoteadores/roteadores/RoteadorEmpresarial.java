package br.inatel.lojaDeRoteadores.roteadores;

public class RoteadorEmpresarial extends Roteador {

    // Encapsulamento
    private int capacidade;
    private String fabricante;

    // Construtor
    public RoteadorEmpresarial(String modelo, float preco, int capacidade, String fabricante) {
        super(modelo, preco);
        this.capacidade = capacidade;
        this.fabricante = fabricante;
    }

    // Polimorfismo
    @Override
    public void mostraInfo() {
        System.out.println("=== Roteador Empresarial ===");
        System.out.println("Modelo: " + getModelo());
        System.out.println("Preço: R$ " + getPreco());
        System.out.println("Fabricante: " + fabricante);
        System.out.println("Capacidade: " + capacidade + " dispositivos");
        System.out.println("Conectado: " + isConexao());
    }

    @Override
    public float calcularTotal() {
        return getPreco() * 1.10f;
    }

    @Override
    public boolean permitirConexao() {
        return this.capacidade > 0;
    }

    // Getter
    public int getCapacidade() { return capacidade; }
    public String getFabricante() { return fabricante; }
}