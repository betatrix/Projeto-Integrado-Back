package com.vocco.api.domain.administrador;

import com.vocco.api.domain.administrador.dto.DadosAtualizacaoAdministrador;
import com.vocco.api.domain.administrador.dto.DadosCadastroAdministrador;
import com.vocco.api.domain.administrador.dto.DadosDetalhamentoAdministrador;
import com.vocco.api.domain.administrador.dto.DadosListagemAdministrador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdministradorService {
    @Autowired
    private AdministradorRepository repository;

    public DadosDetalhamentoAdministrador cadastrar(DadosCadastroAdministrador dados){
        Administrador administrador = new Administrador(dados);
        repository.save(administrador);
        return new DadosDetalhamentoAdministrador(administrador);
    }

    public DadosDetalhamentoAdministrador editar(DadosAtualizacaoAdministrador dados){
        Administrador administrador = repository.getReferenceById(dados.id());
        administrador.editarInformacoes(dados);
        repository.save(administrador);
        return new DadosDetalhamentoAdministrador(administrador);
    }

    public DadosDetalhamentoAdministrador detalhar(Long id){
        return new DadosDetalhamentoAdministrador(repository.getReferenceById(id));
    }

    public List<DadosListagemAdministrador> listar(){
        return repository.findAllByAtivoTrue().stream().map(DadosListagemAdministrador::new).toList();
    }
}
