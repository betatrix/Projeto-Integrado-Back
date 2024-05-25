package com.vocco.api.domain.usuario;

import com.vocco.api.domain.estudante.Estudante;
import com.vocco.api.domain.estudante.EstudanteRepository;
import com.vocco.api.domain.usuario.dto.AuthenticationDTO;
import com.vocco.api.domain.usuario.dto.LoginResponseDTO;
import com.vocco.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
    UsuarioRepository repository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private EstudanteRepository estudanteRepository;

    private final AuthenticationManager authenticationManager;

    public UsuarioService(@Lazy AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);
    }

    public LoginResponseDTO login(AuthenticationDTO dados){
        Usuario usuario = repository.findByLoginUsuario(dados.login());

        if(usuario == null){
            throw new RuntimeException("Credenciais Inválidas!");
        }

        var usernamePassword = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        Estudante estudante = estudanteRepository.findAllByUsuarioId(usuario.getId());
        return new LoginResponseDTO(token, usuario, estudante);
    }

}
