package br.com.faculdade.navegacaoapi.controller;

import br.com.faculdade.navegacaoapi.model.Local;
import br.com.faculdade.navegacaoapi.model.Mapa;
import br.com.faculdade.navegacaoapi.model.PontoReferencia;
import br.com.faculdade.navegacaoapi.service.LocalService;
import br.com.faculdade.navegacaoapi.service.MapaService;
import br.com.faculdade.navegacaoapi.service.PontoReferenciaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mapa")
public class MapaController {

    @Autowired
    private MapaService mapaService;

    @Autowired
    private PontoReferenciaService pontoReferenciaService;

    @Autowired
    private LocalService localService;

    
    @GetMapping
    public List<Mapa> listarTodos() {
        return mapaService.buscarTodos();
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<Mapa> buscarPorId(@PathVariable Long id) {
        return mapaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    
    @PostMapping
    public Mapa criar(@RequestBody Mapa mapa) {
        return mapaService.salvar(mapa);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Mapa> atualizar(@PathVariable Long id, @RequestBody Mapa mapaDetalhes) {
        return mapaService.buscarPorId(id)
                .map(mapaExistente -> {

                    
                    if (mapaDetalhes.getNome() != null && !mapaDetalhes.getNome().isBlank()) {
                        mapaExistente.setNome(mapaDetalhes.getNome());
                    }

                    if (mapaDetalhes.getDescricao() != null) {
                        mapaExistente.setDescricao(mapaDetalhes.getDescricao());
                    }

                    
                    if (mapaDetalhes.getBloco() != null && !mapaDetalhes.getBloco().isBlank()) {
                        mapaExistente.setBloco(mapaDetalhes.getBloco());
                    }

                    if (mapaDetalhes.getUrlImagem() != null) {
                        mapaExistente.setUrlImagem(mapaDetalhes.getUrlImagem());
                    }

                    Mapa atualizado = mapaService.salvar(mapaExistente);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!mapaService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        mapaService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    
    @GetMapping("/{mapaId}/locais")
    public List<Local> listarLocaisPorMapa(@PathVariable Long mapaId) {
        return localService.buscarPorMapaId(mapaId);
    }

     @GetMapping("/{id}/pontos")
    public List<PontoReferencia> buscarPontosPorMapa(@PathVariable("id") Long mapaId) {
        return pontoReferenciaService.buscarPorMapaId(mapaId);
    }
}
