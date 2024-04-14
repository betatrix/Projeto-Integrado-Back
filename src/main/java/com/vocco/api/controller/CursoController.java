package com.vocco.api.controller;


import com.vocco.api.domain.curso.CursoService;
import com.vocco.api.domain.curso.dto.DadosAtualizacaoCurso;
import com.vocco.api.domain.curso.dto.DadosCadastroCurso;
import com.vocco.api.domain.curso.dto.DadosDetalhamentoCurso;
import com.vocco.api.domain.curso.dto.DadosListagemCurso;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("curso")
public class CursoController {
    @Autowired
    private CursoService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoCurso> cadastrar(@RequestBody @Valid DadosCadastroCurso dados){
        DadosDetalhamentoCurso curso = service.cadastrar(dados);
        return ResponseEntity.ok().body(curso);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoCurso> editar(@RequestBody @Valid DadosAtualizacaoCurso dados){
        DadosDetalhamentoCurso curso = service.editar(dados);
        return ResponseEntity.ok().body(curso);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoCurso> detalhar(@PathVariable Long id){
        return ResponseEntity.ok().body(service.detalhar(id));
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemCurso>> listar(){
        return ResponseEntity.ok().body(service.listar());
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
