package com.example.miaudote.model;

public class Adotante extends Usuario {
    private String cpf;
//    private String dataDeNascimento;


    public Adotante(String nome, String email, String senha, String cpf) {
        super(nome, email, senha); // este super precisa bater com os campos do construtor da classe Usuario
        this.cpf = cpf;
//        this.dataDeNascimento = dataDeNascimento;
    }


    // Getters e Setters para cpf e dataDeNascimento
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}


//    public String getDataDeNascimento() {
//        return dataDeNascimento;
//    }
//
//    public void setDataDeNascimento(String dataDeNascimento) {
//        this.dataDeNascimento = dataDeNascimento;
//    }
//}

