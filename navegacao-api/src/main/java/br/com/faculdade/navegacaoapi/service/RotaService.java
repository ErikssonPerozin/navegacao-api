package br.com.faculdade.navegacaoapi.service;

import br.com.faculdade.navegacaoapi.model.Rota;
import br.com.faculdade.navegacaoapi.repository.RotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RotaService {

    @Autowired
    private RotaRepository rotaRepository;

    public List<Rota> buscarTodos() {
        return rotaRepository.findAll();
    }

    public Optional<Rota> buscarPorId(Long id) {
        return rotaRepository.findById(id);
    }

    public Rota salvar(Rota rota) {
        return rotaRepository.save(rota);
    }

    public void deletarPorId(Long id) {
        rotaRepository.deleteById(id);
    }

    public List<Rota> buscarPorUsuarioId(Long usuarioId) {
        return rotaRepository.findByUsuarioId(usuarioId);
    }

    public List<Rota> buscarPorOrigemId(Long origemId) {
        return rotaRepository.findByOrigemId(origemId);
    }

    public List<Rota> buscarPorPontoReferenciaId(Long pontoReferenciaId) {
        return rotaRepository.findByPontosReferenciaId(pontoReferenciaId);
    }
}
