package br.inatel.lojaDeRoteadores.roteadores;

public class RoteadorEspecial extends Roteador {

    //Encapsulamento
    private String tipo;
    private String conectividade;

    // Construtor
    public RoteadorEspecial(String modelo, float preco, String tipo, String conectividade) {
        super(modelo, preco);
        this.tipo = tipo;
        this.conectividade = conectividade;
    }

    // Polimorfismo
    @Override
    public void mostraInfo() {
        System.out.println("=== Roteador Especial ===");
        System.out.println("Modelo: " + getModelo());
        System.out.println("Preço: R$ " + getPreco());
        System.out.println("Tipo: " + tipo);
        System.out.println("Conectividade: " + conectividade);
        System.out.println("Conectado: " + isConexao());
    }

    @Override
    public float calcularTotal() {
        return getPreco() * 1.15f;
    }

    @Override
    public boolean permitirConexao() {

        return this.conectividade != null && !this.conectividade.isEmpty();
    }

    // Getters (Encapsulamento)
    public String getTipo() { return tipo; }
    public String getConectividade() { return conectividade; }
}