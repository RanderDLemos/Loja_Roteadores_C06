package br.inatel.lojaDeRoteadores.roteadores;

public abstract class Roteador implements Conectavel {

    private float preco;
    private String modelo;
    private String frequencia;
    private boolean conexao;

    public Roteador(String modelo, float preco) {
        this.modelo = modelo;
        this.preco = preco;
    }

    public float getPreco() {
        return preco;
    }

    public String getModelo() {
        return modelo;
    }

    public String getFrequencia() {
        return frequencia;
    }

    public boolean isConexao() {
        return conexao;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }

    public void setConexao(boolean conexao) {
        this.conexao = conexao;
    }

    public abstract void mostraInfo();

    public abstract float calcularTotal();

    @Override
    public abstract boolean permitirConexao();
}