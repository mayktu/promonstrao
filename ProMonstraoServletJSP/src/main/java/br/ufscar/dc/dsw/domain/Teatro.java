package br.ufscar.dc.dsw.domain;

public class Teatro {
    private Long id;
    private String email;
    private String nome;
    private String cnpj;
    private String cidade;

    public Teatro(String email, String nome, String cnpj, String cidade) {
        this.email = email;
        this.nome = nome;
        this.cnpj = cnpj;
        this.cidade = cidade;
    }

    public Teatro(Long id, String email, String nome, String cnpj, String cidade) {
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.cnpj = cnpj;
        this.cidade = cidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
