package br.com.faculdade.navegacaoapi.controller;

import br.com.faculdade.navegacaoapi.model.Local;
import br.com.faculdade.navegacaoapi.model.Rota; // <-- Import que foi adicionado
import br.com.faculdade.navegacaoapi.service.LocalService;
import br.com.faculdade.navegacaoapi.service.RotaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/local") 
public class LocalController {

    @Autowired
    private LocalService localService;

    @Autowired
    private RotaService rotaService;

    @GetMapping
    public List<Local> listarTodos() {
        return localService.buscarTodos();
    }

    
    @GetMapping("/{id}/rotas_origem")
    public List<Rota> buscarRotasPorOrigem(@PathVariable Long id) {
        return rotaService.buscarPorOrigemId(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Local> buscarPorId(@PathVariable Long id) {
        return localService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Local criar(@RequestBody Local local) {
        return localService.salvar(local);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Local> atualizar(@PathVariable Long id, @RequestBody Local localDetalhes) {
        return localService.buscarPorId(id)
                .map(localExistente -> {
                    // Verifica cada campo: se um novo valor foi enviado, atualiza.
                    if (localDetalhes.getNome() != null) {
                        localExistente.setNome(localDetalhes.getNome());
                    }
                    if (localDetalhes.getNumero() != null) {
                        localExistente.setNumero(localDetalhes.getNumero());
                    }
                    if (localDetalhes.getAndar() != null) {
                        localExistente.setAndar(localDetalhes.getAndar());
                    }
                    if (localDetalhes.getXCoord() != null) {
                        localExistente.setXCoord(localDetalhes.getXCoord());
                    }
                    if (localDetalhes.getYCoord() != null) {
                        localExistente.setYCoord(localDetalhes.getYCoord());
                    }
                    if (localDetalhes.getMapa() != null) {
                        localExistente.setMapa(localDetalhes.getMapa());
                    }
                    if (localDetalhes.getTipoLocal() != null) {
                        localExistente.setTipoLocal(localDetalhes.getTipoLocal());
                    }
                    
                    Local atualizado = localService.salvar(localExistente);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!localService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        localService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}