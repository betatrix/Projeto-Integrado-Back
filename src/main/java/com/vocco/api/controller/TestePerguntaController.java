package com.vocco.api.controller;

import com.vocco.api.domain.pergunta.dto.DadosDetalhamentoPergunta;
import com.vocco.api.domain.teste.dto.DadosCadastroTeste;
import com.vocco.api.domain.teste.dto.DadosDetalhamentoTeste;
import com.vocco.api.domain.teste.dto.DadosListagemTeste;
import com.vocco.api.domain.teste_pergunta.TestePerguntaService;
import com.vocco.api.domain.teste_pergunta.dto.DadosCadastroTestePergunta;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("testePergunta")
public class TestePerguntaController {
    @Autowired
    private TestePerguntaService service;
    @PostMapping
    @Transactional
    public ResponseEntity<List<DadosDetalhamentoPergunta>> cadastrar(@RequestBody @Valid DadosCadastroTestePergunta dados){
        List<DadosDetalhamentoPergunta> perguntasTeste = service.cadastrar(dados);
        return ResponseEntity.ok().body(perguntasTeste);
    }

    @GetMapping("perguntas/{testeId}")
    public ResponseEntity<List<DadosDetalhamentoPergunta>> listarPerguntasDoTeste(@PathVariable Long testeId){
        return ResponseEntity.ok().body(service.listarPerguntasDoTeste(testeId));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<List<DadosDetalhamentoPergunta>> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }



}
