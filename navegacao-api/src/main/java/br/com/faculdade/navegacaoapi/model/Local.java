package br.com.faculdade.navegacaoapi.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal; 

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Entity
public class Local {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(length = 10)
    private String numero;

    @Column(nullable = false, length = 50)
    private String andar;

   @JsonProperty("xCoord")
@Column(nullable = false, precision = 8, scale = 6)
private BigDecimal xCoord;

@JsonProperty("yCoord")
@Column(nullable = false, precision = 8, scale = 6)
private BigDecimal yCoord;


    @ManyToOne
    @JoinColumn(name = "mapa_id", nullable = false)
    private Mapa mapa;

    @ManyToOne
    @JoinColumn(name = "tipo_local_id", nullable = false)
    private TipoLocal tipoLocal;
}