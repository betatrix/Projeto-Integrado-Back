package com.vocco.api.domain.estudante;

import com.vocco.api.domain.estudante.dto.DadosAtualizacaoEstudante;
import com.vocco.api.domain.estudante.dto.DadosCadastroEstudante;
import com.vocco.api.domain.estudante.dto.DadosDetalhamentoEstudante;
import com.vocco.api.domain.estudante.dto.DadosListagemEstudante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EstudanteService {
    @Autowired
    private EstudanteRepository repository;

    public DadosDetalhamentoEstudante cadastrar(DadosCadastroEstudante dados){
        Estudante estudante = new Estudante(dados);
        repository.save(estudante);
        return new DadosDetalhamentoEstudante(estudante);
    }

    public DadosDetalhamentoEstudante editar(DadosAtualizacaoEstudante dados){
        Estudante estudante = repository.getReferenceById(dados.id());
        estudante.editarInformacoes(dados);
        repository.save(estudante);
        return new DadosDetalhamentoEstudante(estudante);
    }

    public DadosDetalhamentoEstudante detalhar(Long id){
        return new DadosDetalhamentoEstudante((repository.getReferenceById(id)));
    }

    public List<DadosListagemEstudante> listar(){
        return repository.findAllByAtivoTrue().stream().map(DadosListagemEstudante::new).toList();
    }
}
