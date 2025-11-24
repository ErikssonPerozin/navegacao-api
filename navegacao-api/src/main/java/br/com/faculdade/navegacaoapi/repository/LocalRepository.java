package br.com.faculdade.navegacaoapi.repository;

import br.com.faculdade.navegacaoapi.model.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LocalRepository extends JpaRepository<Local, Long> {

    List<Local> findByMapaId(Long mapaId);

    List<Local> findByTipoLocalId(Long tipoId);
}
