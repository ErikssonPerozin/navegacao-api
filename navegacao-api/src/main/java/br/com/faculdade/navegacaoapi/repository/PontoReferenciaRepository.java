package br.com.faculdade.navegacaoapi.repository;

import br.com.faculdade.navegacaoapi.model.PontoReferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PontoReferenciaRepository extends JpaRepository<PontoReferencia, Long> {

    List<PontoReferencia> findByMapaId(Long mapaId);
}