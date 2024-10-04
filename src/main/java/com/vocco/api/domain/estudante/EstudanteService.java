package com.vocco.api.domain.estudante;

import com.vocco.api.domain.estudante.dto.DadosAtualizacaoEstudante;
import com.vocco.api.domain.estudante.dto.DadosCadastroEstudante;
import com.vocco.api.domain.estudante.dto.DadosDetalhamentoEstudante;
import com.vocco.api.domain.estudante.dto.DadosListagemEstudante;
import com.vocco.api.domain.usuario.Usuario;
import com.vocco.api.domain.usuario.UsuarioRepository;
import com.vocco.api.infra.aws.FilesService;
import com.vocco.api.infra.exception.excecoesPersonalizadas.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class EstudanteService {
    @Autowired
    private EstudanteRepository repository;
    @Autowired
    private FilesService filesService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public DadosDetalhamentoEstudante cadastrar(DadosCadastroEstudante dados){
        if(repository.existsByEmail(dados.email())){
            throw new ValidacaoException("Já existe uma conta cadastrada com esse email!");
        }
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
        return repository.findAll().stream().map(DadosListagemEstudante::new).toList();
    }

    public List<DadosListagemEstudante> listarAtivos(){
        return repository.findAllByAtivoTrue().stream().map(DadosListagemEstudante::new).toList();
    }

    public Integer contarAtivos(){
        return repository.findAllByAtivoTrue().size();
    }

    public void excluir(Long id){
        Estudante estudante = repository.getReferenceById(id);
        estudante.excluir();
        repository.save(estudante);
    }

    public DadosDetalhamentoEstudante adicionarFoto(Long id, MultipartFile arquivo){
        Estudante estudante = repository.getReferenceByUsuarioId(id);
        Usuario usuario = estudante.getUsuario();
        if(arquivo != null){
            usuario.setFotoDePerfil(filesService.salvarFotoDePerfil(arquivo, id));
        } else{
            throw new ValidacaoException("Insira uma imagem válida.");
        }
        usuarioRepository.save(usuario);
        return new DadosDetalhamentoEstudante(estudante);
    }
}
