package br.ufscar.dc.dsw.domain;


public class Promocao {
    private Long id;
    private Long idSite;
    private Long idTeatro;
    private String nome;
    private float preco;
    private String data;

    public Promocao(Long id, Long idSite, Long idTeatro, String nome, float preco, String data) {
        this.id = id;
        this.idSite = idSite;
        this.idTeatro = idTeatro;
        this.nome = nome;
        this.preco = preco;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdSite() {
        return idSite;
    }

    public void setIdSite(Long idSite) {
        this.idSite = idSite;
    }

    public Long getIdTeatro() {
        return idTeatro;
    }

    public void setIdTeatro(Long idTeatro) {
        this.idTeatro = idTeatro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
