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
public class AlunoController {

    static List alunos = new ArrayList<>();

    @Autowired
    private AlunoRepository alunoRepository;

    static {
        alunos.add(Map.of("nome", "João", "email", "joao@localhost"));
        alunos.add(Map.of("nome", "Maria", "email", "maria@localhost"));
    }

    @GetMapping("/alunos")
    public String getHome(Model model) {
        List alunosBd = alunoRepository.findAll();
        model.addAttribute("alunos", alunosBd);
        // model.addAttribute("alunos", alunos);
        model.addAttribute("mensagem", "Todos os alunos cadastrados");
        return "alunos.html";
    }

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/alunos/create")
    public String getCreate() {
        return "aluno-create.html";
    }

    @PostMapping("/alunos/create")
    public String postCreate(@RequestParam String nome, @RequestParam String email) {
        Aluno aluno = new Aluno(nome, email);
        alunoRepository.save(aluno);
        return "redirect:/alunos";
    }

    @GetMapping("/alunos/update/{id}")
    public String getUpdate(@PathVariable int id, Model model) {
        // model.addAttribute("aluno", alunos.get(id));
        model.addAttribute("aluno", alunoRepository.findById(id).get());
        model.addAttribute("id", id);
        return "aluno-update.html";
    }

    @PostMapping("/alunos/update")
    public String postUpdate(@RequestParam int id, @RequestParam String nome, @RequestParam String email) {
        // alunos.set(id, Map.of("nome", nome, "email", email));
        Aluno aluno = alunoRepository.findById(id).get();
        aluno.setNome(nome);
        aluno.setEmail(email);
        alunoRepository.save(aluno);
        return "redirect:/alunos";
    }

    @GetMapping("/alunos/delete/{id}")
    public String getDelete(@PathVariable int id, Model model) {
        // model.addAttribute("aluno", alunos.get(id));
        Aluno alunodb = alunoRepository.findById(id).get();
        model.addAttribute("aluno", alunodb);
        model.addAttribute("id", id);
        return "aluno-delete.html";
    }

    @PostMapping("/alunos/delete")
    public String postDelete(@RequestParam int id) {
        // alunos.remove(id);
        alunoRepository.deleteById(id);
        return "redirect:/alunos";
    }

    @GetMapping("/alunos/busca")
    public String getBusca(@RequestParam String nome, Model model) {
        // model.addAttribute("alunos", alunos);
        model.addAttribute("alunos", alunoRepository.findByNomeContainingIgnoreCase(nome));
        return "alunos.html";
    }
}
