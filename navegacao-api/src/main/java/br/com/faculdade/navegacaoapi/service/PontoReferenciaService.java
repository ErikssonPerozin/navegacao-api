package br.com.faculdade.navegacaoapi.service;

import br.com.faculdade.navegacaoapi.model.PontoReferencia;
import br.com.faculdade.navegacaoapi.repository.PontoReferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Importe

import java.util.List;
import java.util.Optional;

@Service
public class PontoReferenciaService {

    @Autowired
    private PontoReferenciaRepository repository;

    
    @Transactional(readOnly = true) 
    public List<PontoReferencia> buscarTodos() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public List<PontoReferencia> buscarPorMapaId(Long mapaId) {
        return repository.findByMapaId(mapaId);
    }

    
    @Transactional(readOnly = true)
    public Optional<PontoReferencia> buscarPorId(Long id) {
        return repository.findById(id);
    }

    
    @Transactional 
    public PontoReferencia salvar(PontoReferencia pontoReferencia) {
        return repository.save(pontoReferencia);
    }

    
    @Transactional
    public Optional<PontoReferencia> atualizar(Long id, PontoReferencia detalhes) {
        return repository.findById(id).map(existente -> {
            
            if (detalhes.getNome() != null && !detalhes.getNome().isBlank()) {
                existente.setNome(detalhes.getNome());
            }
            
            if (detalhes.getCategoria() != null && !detalhes.getCategoria().isBlank()) {
                existente.setCategoria(detalhes.getCategoria());
            }
            
            if (detalhes.getXCoord() != null) {
                existente.setXCoord(detalhes.getXCoord());
            }
            if (detalhes.getYCoord() != null) {
                existente.setYCoord(detalhes.getYCoord());
            }
            
            
            if (detalhes.getMapa() != null) {
                existente.setMapa(detalhes.getMapa());
            }

            return repository.save(existente);
        });
    }

   
    @Transactional
    public boolean deletar(Long id) {
        return repository.findById(id).map(existente -> {
            repository.delete(existente);
            return true;
        }).orElse(false);
    }
}