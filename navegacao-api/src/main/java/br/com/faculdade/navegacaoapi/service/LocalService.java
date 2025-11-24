package br.com.faculdade.navegacaoapi.service;

import br.com.faculdade.navegacaoapi.model.Local;
import br.com.faculdade.navegacaoapi.model.Mapa;
import br.com.faculdade.navegacaoapi.model.TipoLocal;
import br.com.faculdade.navegacaoapi.repository.LocalRepository;
import br.com.faculdade.navegacaoapi.repository.MapaRepository;
import br.com.faculdade.navegacaoapi.repository.TipoLocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocalService {

    @Autowired
    private LocalRepository localRepository;

    
    @Autowired
    private MapaRepository mapaRepository;

    @Autowired
    private TipoLocalRepository tipoLocalRepository;

    public List<Local> buscarTodos() {
        return localRepository.findAll();
    }

    public Optional<Local> buscarPorId(Long id) {
        return localRepository.findById(id);
    }
    
    
    public Local salvar(Local local) {
        
        Long mapaId = local.getMapa().getId();
        Long tipoLocalId = local.getTipoLocal().getId();

        
        Mapa mapa = mapaRepository.findById(mapaId)
                .orElseThrow(() -> new RuntimeException("Mapa não encontrado com o id: " + mapaId));
        
        TipoLocal tipoLocal = tipoLocalRepository.findById(tipoLocalId)
                .orElseThrow(() -> new RuntimeException("TipoLocal não encontrado com o id: " + tipoLocalId));

        
        local.setMapa(mapa);
        local.setTipoLocal(tipoLocal);

        
        return localRepository.save(local);
    }

    public void deletarPorId(Long id) {
        localRepository.deleteById(id);
    }

   
    public List<Local> buscarPorMapaId(Long mapaId) {
        return localRepository.findByMapaId(mapaId);
    }

    public List<Local> buscarPorTipoLocalId(Long tipoId) {
        return localRepository.findByTipoLocalId(tipoId);
    }
}