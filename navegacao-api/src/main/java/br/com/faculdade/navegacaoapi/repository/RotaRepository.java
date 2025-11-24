package br.com.faculdade.navegacaoapi.repository;

import br.com.faculdade.navegacaoapi.model.Rota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RotaRepository extends JpaRepository<Rota, Long> {
    List<Rota> findByUsuarioId(Long usuarioId);      
    List<Rota> findByOrigemId(Long origemId);        
    List<Rota> findByDestinoId(Long destinoId);      
    List<Rota> findByPontosReferenciaId(Long pontoReferenciaId);
}
