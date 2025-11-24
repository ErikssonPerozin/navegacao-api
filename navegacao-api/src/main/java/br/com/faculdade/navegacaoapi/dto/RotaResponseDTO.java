package br.com.faculdade.navegacaoapi.dto;

import br.com.faculdade.navegacaoapi.model.Rota;

public class RotaResponseDTO {
    
    private Long id;
    

    public RotaResponseDTO(Rota rota) {
        this.id = rota.getId();
    }

    public Long getId() { return id; }
}
