package com.vocco.api.domain.instituicao;

import com.vocco.api.domain.instituicao.dto.DadosAtualizacaoInstituicao;
import com.vocco.api.domain.instituicao.dto.DadosCadastroInstituicao;
import com.vocco.api.domain.instituicao.dto.DadosDetalhamentoInstituicao;
import com.vocco.api.domain.instituicao.dto.DadosListagemInstituicao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstituicaoService {
    @Autowired
    private InstituicaoRepository repository;

    public DadosDetalhamentoInstituicao cadastrar(DadosCadastroInstituicao dados){
        Instituicao instituicao = new Instituicao(dados);
        repository.save(instituicao);
        return new DadosDetalhamentoInstituicao(instituicao);
    }

    public DadosDetalhamentoInstituicao editar(DadosAtualizacaoInstituicao dados){
        Instituicao instituicao = repository.getReferenceById(dados.id());
        instituicao.editarInformacoes(dados);
        repository.save(instituicao);
        return new DadosDetalhamentoInstituicao(instituicao);
    }

    public DadosDetalhamentoInstituicao detalhar(Long id){
        return new DadosDetalhamentoInstituicao(repository.getReferenceById(id));
    }

    public List<DadosListagemInstituicao> listar(){
        return repository.findAllByAtivoTrue().stream().map(DadosListagemInstituicao::new).toList();
    }

    public void excluir(Long id){
        Instituicao instituicao = repository.getReferenceById(id);
        instituicao.excluir();
        repository.save(instituicao);
    }
}
