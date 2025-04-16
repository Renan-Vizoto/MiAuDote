package com.example.miaudote.model;

public class Ong extends Usuario {
    private String cnpj;
    private String endereco;



    public Ong(String nome, String email, String senha, String cnpj, String endereco) {
        super(nome, email, senha);
        this.cnpj = cnpj;
        this.endereco = endereco;
    }



    // Getters e Setters para cnpj e endereco
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}

