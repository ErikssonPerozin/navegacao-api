package br.com.faculdade.navegacaoapi.repository;

import br.com.faculdade.navegacaoapi.model.Mapa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MapaRepository extends JpaRepository<Mapa, Long> {

    /**
     * @param id 
     * @return
     */
    @Query("SELECT m FROM Mapa m LEFT JOIN FETCH m.pontosDeReferencia WHERE m.id = :id")
    Optional<Mapa> findByIdWithPontoReferencia(@Param("id") Long id);
}