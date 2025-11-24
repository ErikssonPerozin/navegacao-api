package br.com.faculdade.navegacaoapi.service;

import br.com.faculdade.navegacaoapi.model.Mapa;
import br.com.faculdade.navegacaoapi.repository.MapaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 

import java.util.List;
import java.util.Optional;

@Service
public class MapaService {

    @Autowired
    private MapaRepository mapaRepository;

    public List<Mapa> buscarTodos() {
        return mapaRepository.findAll();
    }

    
    @Transactional(readOnly = true) 
    public Optional<Mapa> buscarPorId(Long id) {
        
        return mapaRepository.findByIdWithPontoReferencia(id);
    }
    

    public Mapa salvar(Mapa mapa) {
        return mapaRepository.save(mapa);
    }

    public void deletarPorId(Long id) {
        mapaRepository.deleteById(id);
    }
}