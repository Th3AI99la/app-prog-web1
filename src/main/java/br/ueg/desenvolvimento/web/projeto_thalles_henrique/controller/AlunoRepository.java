package br.ueg.desenvolvimento.web.projeto_thalles_henrique.controller;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
}