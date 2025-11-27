package br.com.faculdade.navegacaoapi.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal; 

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Entity
@Table(name = "ponto_referencia")
public class PontoReferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 50)
    private String categoria;


    
    @JsonProperty("xCoord")
    @Column(nullable = false, precision = 8, scale = 6)
    private BigDecimal xCoord; 

    @JsonProperty("yCoord")
    @Column(nullable = false, precision = 8, scale = 6)
    private BigDecimal yCoord; 
    

    @ManyToOne
    @JoinColumn(name = "mapa_id", nullable = false)
    private Mapa mapa;
}
