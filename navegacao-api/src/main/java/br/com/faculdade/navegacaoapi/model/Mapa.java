package br.com.faculdade.navegacaoapi.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List; 

@Data
@Entity
public class Mapa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(length = 100)
    private String descricao;

    @Column(nullable = false, length = 20)
    private String bloco;

    @Column(columnDefinition = "TEXT")
    private String urlImagem;

  
     
    @OneToMany(mappedBy = "mapa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PontoReferencia> pontosDeReferencia;
    
}