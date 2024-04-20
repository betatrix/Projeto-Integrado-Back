package com.vocco.api.domain.teste;

import com.vocco.api.domain.teste.dto.DadosAtualizacaoTeste;
import com.vocco.api.domain.teste.dto.DadosCadastroTeste;
import com.vocco.api.domain.teste.dto.DadosDetalhamentoTeste;
import com.vocco.api.domain.teste.dto.DadosListagemTeste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TesteService {

    @Autowired TesteRepository repository;

    public DadosDetalhamentoTeste cadastrar(DadosCadastroTeste dados){
        Teste teste = new Teste(dados);
        repository.save(teste);
        return new DadosDetalhamentoTeste(teste);
    }
    public DadosDetalhamentoTeste editar(DadosAtualizacaoTeste dados){
        Teste teste = repository.getReferenceById(dados.id());
        teste.editarInformacoes(dados);
        repository.save(teste);
        return new DadosDetalhamentoTeste(teste);
    }
    public DadosDetalhamentoTeste detalhar(Long id){
        return new DadosDetalhamentoTeste(repository.getReferenceById(id));
    }

    public List<DadosListagemTeste> listar(){
        return repository.findAllByAtivoTrue().stream().map(DadosListagemTeste::new).toList();
    }
    public void excluir(Long id){
        Teste teste = repository.getReferenceById(id);
        teste.excluir();
        repository.save(teste);
    }
}
