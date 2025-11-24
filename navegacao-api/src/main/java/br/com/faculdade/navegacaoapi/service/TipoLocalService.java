package br.com.faculdade.navegacaoapi.service;

import br.com.faculdade.navegacaoapi.model.TipoLocal;
import br.com.faculdade.navegacaoapi.repository.TipoLocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TipoLocalService {

    @Autowired
    private TipoLocalRepository tipoLocalRepository;

    public List<TipoLocal> buscarTodos() {
        return tipoLocalRepository.findAll();
    }

    public Optional<TipoLocal> buscarPorId(Long id) {
        return tipoLocalRepository.findById(id);
    }

    public TipoLocal salvar(TipoLocal tipoLocal) {
        return tipoLocalRepository.save(tipoLocal);
    }

    public void deletarPorId(Long id) {
        tipoLocalRepository.deleteById(id);
    }
}