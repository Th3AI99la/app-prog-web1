package br.ueg.desenvolvimento.web.projeto_thalles_henrique.controller;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String nome;
    private String email;

    @OneToMany(mappedBy = "aluno")
    private List<TelefoneAluno> telefones;

    @ManyToMany
    private List<Disciplina> disciplinas;

    public Aluno() {

    }

    public Aluno(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public List<TelefoneAluno> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<TelefoneAluno> telefones) {
        this.telefones = telefones;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

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
