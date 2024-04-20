package com.vocco.api.domain.pergunta;

import com.vocco.api.domain.pergunta.dto.DadosAtualizacaoPergunta;
import com.vocco.api.domain.pergunta.dto.DadosCadastroPergunta;
import com.vocco.api.domain.pergunta.dto.DadosDetalhamentoPergunta;
import com.vocco.api.domain.pergunta.dto.DadosListagemPergunta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PerguntaService {

    @Autowired
    private PerguntaRepository repository;

    public DadosDetalhamentoPergunta cadastrar(DadosCadastroPergunta dados){
        Pergunta pergunta = new Pergunta(dados);
        repository.save(pergunta);
        return new DadosDetalhamentoPergunta(pergunta);
    }

    public DadosDetalhamentoPergunta editar(DadosAtualizacaoPergunta dados){
        Pergunta pergunta = repository.getReferenceById(dados.id());
        pergunta.editarInformacoes(dados);
        repository.save(pergunta);
        return new DadosDetalhamentoPergunta(pergunta);
    }
    public DadosDetalhamentoPergunta detalhar(Long id){
        return new DadosDetalhamentoPergunta(repository.getReferenceById(id));
    }

    public List<DadosListagemPergunta> listar(){
        return repository.findAllByAtivoTrue().stream().map(DadosListagemPergunta::new).toList();
    }
    public void excluir(Long id){
        Pergunta pergunta = repository.getReferenceById(id);
        pergunta.excluir();
        repository.save(pergunta);
    }
}
