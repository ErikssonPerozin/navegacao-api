package br.com.faculdade.navegacaoapi.controller;

import br.com.faculdade.navegacaoapi.dto.LoginRequestDTO;
import br.com.faculdade.navegacaoapi.dto.LoginResponseDTO;
import br.com.faculdade.navegacaoapi.model.Usuario;
import br.com.faculdade.navegacaoapi.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login") 
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager; 

    @Autowired
    private TokenService tokenService; 

    @PostMapping
    public ResponseEntity<?> efetuarLogin(@RequestBody LoginRequestDTO dados) {

        
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.getEmail(), dados.getSenha());

        
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        
        var usuario = (Usuario) authentication.getPrincipal();

        
        String tokenJWT = tokenService.gerarToken(usuario);

        
        return ResponseEntity.ok(new LoginResponseDTO(tokenJWT));
    }
}