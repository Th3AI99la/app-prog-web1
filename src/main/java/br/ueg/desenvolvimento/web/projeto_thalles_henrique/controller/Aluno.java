package br.ueg.desenvolvimento.web.projeto_thalles_henrique.controller;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Aluno {

    @Id
    private Integer id;

    // Atributos
    private String nome;
    private String email;

    // Construtores
    public Aluno() {

    }

    public Aluno(Integer id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
