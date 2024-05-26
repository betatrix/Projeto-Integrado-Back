package com.vocco.api.domain.estudante_teste;

import com.vocco.api.domain.estudante.Estudante;
import com.vocco.api.domain.estudante.EstudanteRepository;
import com.vocco.api.domain.estudante_teste.dto.DadosCadastroEstudanteTeste;
import com.vocco.api.domain.estudante_teste.dto.DadosDetalhamentoEstudanteTeste;
import com.vocco.api.domain.estudante_teste.dto.DadosListagemEstudanteTeste;
import com.vocco.api.domain.teste.Teste;
import com.vocco.api.domain.teste.TesteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudanteTesteService {
    @Autowired
    private EstudanteTesteRepository repository;
    @Autowired
    private EstudanteRepository estudanteRepository;
    @Autowired
    private TesteRepository testeRepository;

    public EstudanteTeste cadastrar(DadosCadastroEstudanteTeste dados){
        Teste teste = testeRepository.getReferenceById(dados.testeId());
        Estudante estudante = estudanteRepository.getReferenceByUsuarioId(dados.usuarioId());
        EstudanteTeste estudanteTeste = new EstudanteTeste(teste, estudante);
        repository.save(estudanteTeste);
        return estudanteTeste;
    }

    public List<DadosListagemEstudanteTeste> listarTestesDeEstudante(Long usuarioId){
        Estudante estudante = estudanteRepository.getReferenceByUsuarioId(usuarioId);
        return repository.findByEstudanteId(estudante.getId()).stream().map(DadosListagemEstudanteTeste::new).toList();
    }

    public Integer contar(){
        return repository.findAll().size();
    }

    public DadosDetalhamentoEstudanteTeste detalhar(Long id){
        return new DadosDetalhamentoEstudanteTeste(repository.getReferenceById(id));
    }


}
