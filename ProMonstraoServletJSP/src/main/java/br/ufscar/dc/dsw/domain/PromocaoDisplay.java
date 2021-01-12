package br.ufscar.dc.dsw.domain;

public class PromocaoDisplay {
    private String nomeSite;
    private String nomeTeatro;
    private String nome;
    private float preco;
    private String data;

    public PromocaoDisplay(String nomeSite, String nomeTeatro, String nome, float preco, String data) {
        this.nomeSite = nomeSite;
        this.nomeTeatro = nomeTeatro;
        this.nome = nome;
        this.preco = preco;
        this.data = data;
    }

    public String getNomeSite() {
        return nomeSite;
    }

    public String getNomeTeatro() {
        return nomeTeatro;
    }

    public String getNome() {
        return nome;
    }

    public float getPreco() {
        return preco;
    }

    public String getData() {
        return data;
    }
}
