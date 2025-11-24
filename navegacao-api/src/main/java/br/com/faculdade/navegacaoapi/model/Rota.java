package br.com.faculdade.navegacaoapi.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Rota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "origem_id", nullable = false)
    private Local origem;

    @ManyToOne
    @JoinColumn(name = "destino_id", nullable = false)
    private Local destino;

    @Column(nullable = false)
    private LocalDate dataSolicitacao;

    private LocalTime duracaoEstimada;

    private String instrucoes;

    // CAMPO QUE ESTAVA FALTANDO, AGORA CORRIGIDO
    @ManyToMany
    @JoinTable(
      name = "rota_ponto_referencia",
      joinColumns = @JoinColumn(name = "rota_id"),
      inverseJoinColumns = @JoinColumn(name = "ponto_referencia_id")
    )
    private List<PontoReferencia> pontosReferencia;
    

    // Construtores, Getters e Setters
    public Rota() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Local getOrigem() {
        return origem;
    }

    public void setOrigem(Local origem) {
        this.origem = origem;
    }

    public Local getDestino() {
        return destino;
    }

    public void setDestino(Local destino) {
        this.destino = destino;
    }

    public LocalDate getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(LocalDate dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }



    public String getInstrucoes() {
        return instrucoes;
    }

    public void setInstrucoes(String instrucoes) {
        this.instrucoes = instrucoes;
    }

    public List<PontoReferencia> getPontosReferencia() {
        return pontosReferencia;
    }

    public void setPontosReferencia(List<PontoReferencia> pontosReferencia) {
        this.pontosReferencia = pontosReferencia;
    }

    public LocalTime getDuracaoEstimada() {
        return duracaoEstimada;
    }

    public void setDuracaoEstimada(LocalTime duracaoEstimada) {
        this.duracaoEstimada = duracaoEstimada;
    }
}