package br.com.faculdade.navegacaoapi.controller;

import br.com.faculdade.navegacaoapi.model.Rota;
import br.com.faculdade.navegacaoapi.model.Usuario;
import br.com.faculdade.navegacaoapi.model.Local;
import br.com.faculdade.navegacaoapi.service.RotaService;
import br.com.faculdade.navegacaoapi.service.UsuarioService;
import br.com.faculdade.navegacaoapi.service.LocalService;


import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rota")
public class RotaController {

    @Autowired
    private RotaService rotaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LocalService localService;

    
    public static class CriarRotaDTO {
        public Long usuarioId;
        public Long origemId;
        public Long destinoId;
    }

    
    public static class AtualizarRotaDTO {
        public String instrucoes;
        public LocalTime duracaoEstimada;
    }

   
    @PostMapping
    public ResponseEntity<Rota> criar(@RequestBody CriarRotaDTO dto) {
        
        Usuario usuario = usuarioService.buscarPorId(dto.usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
        Local origem = localService.buscarPorId(dto.origemId)
                .orElseThrow(() -> new RuntimeException("Origem não encontrada"));
        Local destino = localService.buscarPorId(dto.destinoId)
                .orElseThrow(() -> new RuntimeException("Destino não encontrado"));

        
        Rota rota = new Rota();
        rota.setUsuario(usuario);
        rota.setOrigem(origem);
        rota.setDestino(destino);

        
        rota.setDataSolicitacao(LocalDate.now()); 
        rota.setDuracaoEstimada(LocalTime.parse("00:00:00")); 
        rota.setInstrucoes("Gerado automaticamente"); 

        
        Rota rotaSalva = rotaService.salvar(rota);
        return ResponseEntity.status(HttpStatus.CREATED).body(rotaSalva); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rota> buscarPorId(@PathVariable Long id) {
        return rotaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody AtualizarRotaDTO dto) {
        Optional<Rota> opt = rotaService.buscarPorId(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Rota r = opt.get();
       
        if (dto.instrucoes != null) {
            r.setInstrucoes(dto.instrucoes);
        }
        if (dto.duracaoEstimada != null) {
            r.setDuracaoEstimada(dto.duracaoEstimada);
        }

        rotaService.salvar(r);
        return ResponseEntity.noContent().build(); 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (rotaService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        rotaService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Rota>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(rotaService.buscarPorUsuarioId(usuarioId));
    }
}