package br.com.faculdade.navegacaoapi.controller;

import br.com.faculdade.navegacaoapi.model.PontoReferencia;
import br.com.faculdade.navegacaoapi.model.Rota;
import br.com.faculdade.navegacaoapi.service.PontoReferenciaService;
import br.com.faculdade.navegacaoapi.service.RotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pontoreferencia")
public class PontoReferenciaController {

    @Autowired
    private PontoReferenciaService pontoReferenciaService;

    @Autowired
    private RotaService rotaService;

    

    
    @GetMapping
    public List<PontoReferencia> listarTodos() {
        return pontoReferenciaService.buscarTodos();
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<PontoReferencia> buscarPorId(@PathVariable Long id) {
        return pontoReferenciaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PontoReferencia criar(@RequestBody PontoReferencia pontoReferencia) {
        return pontoReferenciaService.salvar(pontoReferencia);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<PontoReferencia> atualizar(
            @PathVariable Long id,
            @RequestBody PontoReferencia pontoReferencia) {

        return pontoReferenciaService.atualizar(id, pontoReferencia)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean deletado = pontoReferenciaService.deletar(id);
        return deletado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    
    @GetMapping("/{id}/rotas")
    public List<Rota> buscarRotasPorPontoReferencia(@PathVariable("id") Long pontoReferenciaId) {
        return rotaService.buscarPorPontoReferenciaId(pontoReferenciaId);
    }

    
}
