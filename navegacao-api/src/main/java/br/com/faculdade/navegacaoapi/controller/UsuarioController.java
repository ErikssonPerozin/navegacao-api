package br.com.faculdade.navegacaoapi.controller;

import br.com.faculdade.navegacaoapi.dto.RotaResponseDTO;
import br.com.faculdade.navegacaoapi.dto.UsuarioResponseDTO; 
import br.com.faculdade.navegacaoapi.dto.UsuarioInputDTO;
import jakarta.validation.Valid;
import br.com.faculdade.navegacaoapi.model.Rota;
import br.com.faculdade.navegacaoapi.model.Usuario;
import br.com.faculdade.navegacaoapi.service.RotaService;
import br.com.faculdade.navegacaoapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors; 

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RotaService rotaService;

    
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criarUsuario(@RequestBody @Valid UsuarioInputDTO usuarioInput) {
        Usuario usuarioParaSalvar = new Usuario();
        usuarioParaSalvar.setNome(usuarioInput.getNome());
        usuarioParaSalvar.setEmail(usuarioInput.getEmail());
        usuarioParaSalvar.setSenha(usuarioInput.getSenha()); // Será criptografado depois 

        if (usuarioInput.getTipo() == null) {
            usuarioParaSalvar.setTipo("USER");
        } else {
            usuarioParaSalvar.setTipo(usuarioInput.getTipo());
        }

        Usuario salvo = usuarioService.salvar(usuarioParaSalvar);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(salvo.getId())
                .toUri();
        
        UsuarioResponseDTO dto = new UsuarioResponseDTO(salvo);
        return ResponseEntity.created(location).body(dto);
    }

    
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodosUsuarios() {
        List<Usuario> usuarios = usuarioService.buscarTodos();
        
        
        List<UsuarioResponseDTO> dtos = usuarios.stream()
                .map(UsuarioResponseDTO::new) 
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(dtos);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id)
                
                .map(usuario -> ResponseEntity.ok(new UsuarioResponseDTO(usuario)))
                .orElse(ResponseEntity.notFound().build());
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioDetalhes) {
        return usuarioService.buscarPorId(id)
                .map(usuarioExistente -> {
                    
                    if (usuarioDetalhes.getNome() != null) {
                        usuarioExistente.setNome(usuarioDetalhes.getNome());
                    }
                    if (usuarioDetalhes.getEmail() != null) {
                        usuarioExistente.setEmail(usuarioDetalhes.getEmail());
                    }
                    if (usuarioDetalhes.getTipo() != null) {
                        usuarioExistente.setTipo(usuarioDetalhes.getTipo());
                    }
                    if (usuarioDetalhes.getSenha() != null) {
                        
                        usuarioExistente.setSenha(usuarioDetalhes.getSenha());
                    }
                    
                    Usuario atualizado = usuarioService.salvar(usuarioExistente);

                    
                    return ResponseEntity.ok(new UsuarioResponseDTO(atualizado));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        if (!usuarioService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        usuarioService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    
    @GetMapping("/{usuarioId}/rotas")
    public ResponseEntity<List<RotaResponseDTO>> listarRotasPorUsuario(@PathVariable Long usuarioId) {
        List<Rota> rotas = rotaService.buscarPorUsuarioId(usuarioId);

        List<RotaResponseDTO> dtos = rotas.stream()
                .map(RotaResponseDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }
}