package com.vocco.api.controller;

import com.vocco.api.domain.usuario.Usuario;
import com.vocco.api.domain.usuario.UsuarioRepository;
import com.vocco.api.domain.usuario.UsuarioService;
import com.vocco.api.domain.usuario.dto.AuthenticationDTO;
import com.vocco.api.domain.usuario.dto.LoginResponseDTO;
import com.vocco.api.domain.usuario.dto.RegistroUsuarioDTO;
import com.vocco.api.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioService service;
    private final AuthenticationManager authenticationManager;

    public AuthenticationController(@Lazy AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

//    @PostMapping("/login")
//    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO dados){
//        try {
//            var usernamePassword = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
//            var auth = this.authenticationManager.authenticate(usernamePassword);
//            var token = tokenService.generateToken((Usuario) auth.getPrincipal());
//
//            return ResponseEntity.ok(new LoginResponseDTO(token));
//
//        } catch (Exception e) {
//            return ResponseEntity.status(401).body("Credenciais inválidas");
//        }
//    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO dados){
       return  ResponseEntity.ok().body(service.login(dados));
    }

    @PostMapping("/registrar")
    public ResponseEntity registrar(@RequestBody @Valid RegistroUsuarioDTO dados){
        if(this.repository.findByLogin(dados.login()) != null) return ResponseEntity.badRequest().body("Usuário já existente"); //verifica se ja não existe

        var encryptedPassword = new BCryptPasswordEncoder().encode(dados.senha());
        Usuario novoUsuario = new Usuario(dados.login(), encryptedPassword, dados.role());

        this.repository.save(novoUsuario);
        return ResponseEntity.ok().build();
    }
}
