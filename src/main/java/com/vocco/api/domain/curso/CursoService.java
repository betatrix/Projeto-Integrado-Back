package com.vocco.api.domain.curso;

import com.vocco.api.domain.area.Area;
import com.vocco.api.domain.area.AreaRepository;
import com.vocco.api.domain.curso.dto.DadosAtualizacaoCurso;
import com.vocco.api.domain.curso.dto.DadosCadastroCurso;
import com.vocco.api.domain.curso.dto.DadosDetalhamentoCurso;
import com.vocco.api.domain.curso.dto.DadosListagemCurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {
    @Autowired
    private CursoRepository repository;
    @Autowired
    private AreaRepository areaRepository;

    public DadosDetalhamentoCurso cadastrar(DadosCadastroCurso dados){
        Area area = areaRepository.getReferenceById(dados.areaId());
        Curso curso = new Curso(dados, area);
        repository.save(curso);
        return new DadosDetalhamentoCurso(curso);
    }

    public DadosDetalhamentoCurso editar(DadosAtualizacaoCurso dados){
        Curso curso = repository.getReferenceById(dados.id());
        if(dados.areaId() != null){
            Area area = areaRepository.getReferenceById(dados.areaId());
            curso.setArea(area);
        }
        curso.editarInformacoes(dados);
        repository.save(curso);
        return new DadosDetalhamentoCurso(curso);
    }

    public DadosDetalhamentoCurso detalhar(Long id){
        return new DadosDetalhamentoCurso(repository.getReferenceById(id));
    }

    public List<DadosListagemCurso> listar(){
        return repository.findAllByAtivoTrue().stream().map(DadosListagemCurso::new).toList();
    }

    public void excluir(Long id){
        Curso curso = repository.getReferenceById(id);
        curso.excluir();
        repository.save(curso);
    }
}
