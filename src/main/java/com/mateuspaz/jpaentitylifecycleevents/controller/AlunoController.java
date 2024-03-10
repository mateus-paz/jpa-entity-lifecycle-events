package com.mateuspaz.jpaentitylifecycleevents.controller;

import com.mateuspaz.jpaentitylifecycleevents.entity.Aluno;
import com.mateuspaz.jpaentitylifecycleevents.entity.Curso;
import com.mateuspaz.jpaentitylifecycleevents.repository.AlunoRepository;
import com.mateuspaz.jpaentitylifecycleevents.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/alunos")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoRepository alunoRepository;
    private final CursoRepository cursoRepository;

    @PostMapping("/popular")
    public ResponseEntity<String> popularDados() {
        List<String> titulos = List.of("certificacao java basico", "java primeiros passos", "java pacotes e java lang",
                "certificacao java tipos de dados", "certificacao java operadores decisao",
                "certificacao java usando arrays", "certificacao java loops",
                "certificacao java metodos encapsulamento", "certificacao java heranca", "java excecoes",
                "certificacao java excecoes", "java introducao orientacao objetos", "solid orientacao objetos java",
                "java heranca interfaces polimorfismo", "introducao design patterns java",
                "avancando design patterns java");

        List<Curso> cursos = titulos.stream().map((titulo) -> cursoRepository.save(new Curso(titulo))).toList();

        List<String> nomes = List.of("Miguel", "Sophia", "Davi", "Alice", "Arthur", "Julia", "Pedro", "Isabella",
                "Gabriel", "Manuela", "Bernardo", "Laura", "Lucas", "Luiza", "Mateus", "Valentina", "Rafael",
                "Giovanna", "Heitor", "Maria Eduarda");

        List<Aluno> alunos = nomes.stream().map((nome) -> alunoRepository.save(new Aluno(nome))).toList();

        for(Aluno aluno : alunos) {
            int quantidadeCursos = (int) (Math.random() * cursos.size()) + 1;
            Set<Curso> c = new HashSet<>();
            while (quantidadeCursos > c.size()) c.add(cursos.get((int) (Math.random() * cursos.size()) + 0));
            aluno.associarCursos(c);
            alunoRepository.save(aluno);
        }

        return new ResponseEntity<>("Registros populados com sucesso!", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> buscarTodos() {
        return ResponseEntity.ok(alunoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscarPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(alunoRepository.findById(id).orElse(null));
    }

    @PostMapping
    public ResponseEntity<Aluno> salvar(@RequestBody Aluno aluno) {
        return new ResponseEntity(alunoRepository.save(aluno), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable("id") Long id) {
        alunoRepository.deleteById(id);
        return ResponseEntity.ok("Usuário deletado com sucesso!");
    }

    @PutMapping("/associar-curso/{id}")
    public ResponseEntity<String> associarCurso(@PathVariable("id") Long id, @RequestBody Curso curso) {
        Aluno aluno = alunoRepository.findById(id)
                .orElse(alunoRepository.save(new Aluno(LocalDateTime.now().toString())));

        curso = cursoRepository.findByTitulo(curso.getTitulo())
                .orElse(cursoRepository.save(new Curso(curso.getTitulo())));

        aluno.associarCurso(curso);
        alunoRepository.save(aluno);

        return ResponseEntity.ok("Curso associada com sucesso!");
    }

}
