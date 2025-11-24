package br.com.faculdade.navegacaoapi.dto;

import br.com.faculdade.navegacaoapi.model.Usuario;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class UsuarioResponseDTO {

    
    private Long id;
    private String nome;
    private String email;
    private String tipo;

   
    public UsuarioResponseDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.tipo = usuario.getTipo();
    }
}