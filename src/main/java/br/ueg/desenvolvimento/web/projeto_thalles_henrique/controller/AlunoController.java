package br.ueg.desenvolvimento.web.projeto_thalles_henrique.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AlunoController { // Controller para gerenciar alunos
    private final List<Map<String, String>> alunos = new ArrayList<>();

    // Bloco de inicialização para adicionar alunos de exemplo
    public AlunoController() {
        alunos.add(Map.of("nome", "João", "email", "joao@localhost"));
        alunos.add(Map.of("nome", "Maria", "email", "maria@localhost"));
        alunos.add(Map.of("nome", "José", "email", "jose@localhost"));
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/alunos")
    public String getHome(Model model) {
        model.addAttribute("alunos", alunos);
        return "alunos";
    }

    @GetMapping("/alunos/cadastrar")
    public String getForm() {
        return "aluno-create";
    }

    @PostMapping("/alunos/cadastrar")
    public String postForm(
            @RequestParam String nome,
            @RequestParam String email) {

        System.out.println("Nome: " + nome);
        System.out.println("Email: " + email);

        // Adiciona um novo aluno na lista
        alunos.add(Map.of("nome", nome, "email", email));

        return "redirect:/alunos";
    }

    
}
