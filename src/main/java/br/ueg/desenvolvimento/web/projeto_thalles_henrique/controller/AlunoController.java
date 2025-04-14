package br.ueg.desenvolvimento.web.projeto_thalles_henrique.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AlunoController { // Controller para gerenciar alunos
    static List alunos = new ArrayList<>();

    @Autowired
    private AlunoRepository alunoRepository;

    static {
        alunos.add(Map.of("nome", "João", "email", "joao@localhost"));
        alunos.add(Map.of("nome", "Maria", "email", "maria@localhost"));
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/alunos")
    public String getHome(Model model) {
        //model.addAttribute("alunos", alunos);
        model.addAttribute("alunos", alunoRepository.findAll());

        return "alunos";
    }

    @GetMapping("/alunos/cadastrar")
    public String getForm() {
        return "aluno-create";
    }

    @PostMapping("/alunos/cadastrar")
    public String postForm(@RequestParam String nome, @RequestParam String email) {
        System.out.println("Nome: " + nome);
        System.out.println("Email: " + email);

        // Adiciona um novo aluno na lista
        alunos.add(Map.of("nome", nome, "email", email));

        return "redirect:/alunos";
    }

    @GetMapping("/alunos/create")
    public String getCreate() {
        return "aluno-create";
    }

    @PostMapping("/alunos/create")
    public String postCreate(@RequestParam String nome, @RequestParam String email) {
        alunos.add(Map.of("nome", nome, "email", email));
        return "redirect:/alunos";
    }

    @GetMapping("/alunos/editar/{id}")
    public String getUpdate(@PathVariable int id, Model model) {
        //model.addAttribute("aluno", alunos.get(id));
        model.addAttribute("aluno", alunoRepository.findById(id).get());
        model.addAttribute("id", id);
        return "aluno-update.html";
    }

    @PostMapping("/alunos/editar")
    public String postUpdate(@RequestParam int id, @RequestParam String nome, @RequestParam String email) {
        alunos.set(id, Map.of("nome", nome, "email", email));
        return "redirect:/alunos";
    }

    @GetMapping("/alunos/delete/{id}")
    public String getDelete(@PathVariable int id) {
    // alunos.remove(id);
    alunoRepository.deleteById(id);
    return "redirect:/alunos";
    }

    @PostMapping("/alunos/delete")
    public String postDelete(@RequestParam int id) {
        alunos.remove(id);
        return "redirect:/alunos";
    }

 
@GetMapping("/alunos/busca")
public String getBusca(@RequestParam("nome") String nome, Model model) {
    // Busca alunos pelo nome (ignorando maiúsculas/minúsculas) e adiciona ao modelo
    model.addAttribute("alunos", alunoRepository.findByNomeContainingIgnoreCase(nome));

    // Retorna o nome da view (sem extensão .html se estiver usando Thymeleaf)
    return "alunos"; // Thymeleaf resolve como alunos.html automaticamente
}


}
