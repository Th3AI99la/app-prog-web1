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

    @Autowired
    DisciplinaRepository disciplinaRepository;

    @GetMapping("/aluno/update/{id}")
    public String updateAluno(@PathVariable Integer id, Model model) {
        Aluno aluno = alunoRepository.findById(id).orElse(null);
        List<Disciplina> todasDisciplinas = disciplinaRepository.findAll();
        model.addAttribute("aluno"

                , aluno);
        model.addAttribute("todasDisciplinas"

                , todasDisciplinas);

        return "aluno-update";
    }

    @PostMapping("/alunos/update")
    public String postUpdate(
            @RequestParam int id,
            @RequestParam String nome,
            @RequestParam String email,
            @RequestParam List<Integer> disciplinas) {
        // Buscar aluno pelo ID
        Aluno aluno = alunoRepository.findById(id).get();
        aluno.setNome(nome);
        aluno.setEmail(email);

        // Buscar disciplinas selecionadas
        List<Disciplina> disciplinasSelecionadas = new ArrayList<>();
        for (Integer idDisciplina : disciplinas) {
            Disciplina disciplina = disciplinaRepository.findById(idDisciplina).get();
            disciplinasSelecionadas.add(disciplina);
        }

        aluno.setDisciplinas(disciplinasSelecionadas);

        // Salvar atualizações
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
