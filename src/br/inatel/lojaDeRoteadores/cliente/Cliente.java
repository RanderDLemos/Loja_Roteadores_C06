package br.inatel.lojaDeRoteadores.cliente;

public class Cliente {

    private String nome;
    private String CNPJ;


    public Cliente(String nome, String CNPJ){
        this.nome = nome;
        this.CNPJ = CNPJ;
    }

    public String getNome() {
        return nome;
    }

    public String getCNPJ() {
        return CNPJ;
    }
}
