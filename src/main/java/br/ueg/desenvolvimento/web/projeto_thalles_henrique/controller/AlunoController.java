package br.ueg.desenvolvimento.web.projeto_thalles_henrique.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private TelefoneAlunoRepository telefoneAlunoRepository;

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/alunos")
    public String getHome(Model model) {
        List<Aluno> alunosBd = alunoRepository.findAll();
        model.addAttribute("alunos", alunosBd);
        model.addAttribute("mensagem", "Todos os alunos cadastrados");
        return "alunos.html";
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
        Optional<Aluno> optionalAluno = alunoRepository.findById(id);
        if (optionalAluno.isPresent()) {
            Aluno aluno = optionalAluno.get();
            model.addAttribute("aluno", aluno);
            model.addAttribute("id", id);
            model.addAttribute("todasDisciplinas", disciplinaRepository.findAll());
            return "aluno-update.html";
        } else {
            return "redirect:/alunos";
        }
    }

    @PostMapping("/alunos/update")
    public String postUpdate(
            @RequestParam int id,
            @RequestParam String nome,
            @RequestParam String email,
            @RequestParam List<Integer> disciplinas) {
        Optional<Aluno> optionalAluno = alunoRepository.findById(id);
        if (optionalAluno.isPresent()) {
            Aluno aluno = optionalAluno.get();
            aluno.setNome(nome);
            aluno.setEmail(email);

            List<Disciplina> disciplinasBd = new ArrayList<>();
            for (Integer discId : disciplinas) {
                disciplinaRepository.findById(discId).ifPresent(disciplinasBd::add);
            }

            aluno.setDisciplinas(disciplinasBd);
            alunoRepository.save(aluno);
        }
        return "redirect:/alunos";
    }

    @GetMapping("/alunos/update/telefone/{id}")
    public String updateTelefone(@PathVariable Integer id, Model model) {
        Optional<Aluno> optionalAluno = alunoRepository.findById(id);
        if (optionalAluno.isPresent()) {
            model.addAttribute("aluno", optionalAluno.get());
            return "aluno-telefone";
        } else {
            return "redirect:/alunos";
        }
    }

    @PostMapping("/alunos/update/telefone")
    public String postUpdateTelefone(@RequestParam int id, @RequestParam String novoTelefone) {
        Optional<Aluno> optionalAluno = alunoRepository.findById(id);
        if (optionalAluno.isPresent()) {
            Aluno aluno = optionalAluno.get();
            TelefoneAluno telefone = new TelefoneAluno();
            telefone.setNumero(novoTelefone);
            telefone.setAluno(aluno);
            telefoneAlunoRepository.save(telefone);
        }
        return "redirect:/alunos/update/telefone/" + id;
    }

    @GetMapping("/alunos/delete/telefone/{id}/{idTelefone}")
    public String deleteTelefone(@PathVariable int id, @PathVariable int idTelefone) {
        telefoneAlunoRepository.deleteById(idTelefone);
        return "redirect:/alunos/update/telefone/" + id;
    }

    @GetMapping("/alunos/delete/{id}")
    public String getDelete(@PathVariable int id, Model model) {
        Optional<Aluno> optionalAluno = alunoRepository.findById(id);
        if (optionalAluno.isPresent()) {
            model.addAttribute("aluno", optionalAluno.get());
            model.addAttribute("id", id);
            return "aluno-delete.html";
        } else {
            return "redirect:/alunos";
        }
    }

    @PostMapping("/alunos/delete")
    public String postDelete(@RequestParam int id) {
        alunoRepository.deleteById(id);
        return "redirect:/alunos";
    }

    @GetMapping("/alunos/busca")
    public String getBusca(@RequestParam String nome, Model model) {
        model.addAttribute("alunos", alunoRepository.findByNomeContainingIgnoreCase(nome));
        return "alunos.html";
    }
}
