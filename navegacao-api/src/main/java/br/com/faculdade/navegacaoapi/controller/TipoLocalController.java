package br.com.faculdade.navegacaoapi.controller;

import br.com.faculdade.navegacaoapi.model.Local; 
import br.com.faculdade.navegacaoapi.model.TipoLocal;
import br.com.faculdade.navegacaoapi.service.LocalService; 
import br.com.faculdade.navegacaoapi.service.TipoLocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipolocal")
public class TipoLocalController {

    @Autowired
    private TipoLocalService tipoLocalService;

    
    @Autowired
    private LocalService localService;

    
    @GetMapping
    public List<TipoLocal> listarTodos() {
        return tipoLocalService.buscarTodos();
    }
    
    
    @GetMapping("/{id}/locais")
    public List<Local> buscarLocaisPorTipo(@PathVariable Long id) {
        
        return localService.buscarPorTipoLocalId(id);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<TipoLocal> buscarPorId(@PathVariable Long id) {
        return tipoLocalService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    
    @PostMapping
    public TipoLocal criar(@RequestBody TipoLocal tipoLocal) {
        return tipoLocalService.salvar(tipoLocal);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<TipoLocal> atualizar(@PathVariable Long id, @RequestBody TipoLocal tipoLocalDetalhes) {
        return tipoLocalService.buscarPorId(id)
                .map(tipoExistente -> {
                    if (tipoLocalDetalhes.getNome() != null && !tipoLocalDetalhes.getNome().isBlank()) {
                        tipoExistente.setNome(tipoLocalDetalhes.getNome());
                    }
                    if (tipoLocalDetalhes.getDescricao() != null) {
                        tipoExistente.setDescricao(tipoLocalDetalhes.getDescricao());
                    }
                    if (tipoLocalDetalhes.getIcone() != null) {
                        tipoExistente.setIcone(tipoLocalDetalhes.getIcone());
                    }
                    TipoLocal atualizado = tipoLocalService.salvar(tipoExistente);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!tipoLocalService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        tipoLocalService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}