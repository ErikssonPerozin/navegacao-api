package br.com.faculdade.navegacaoapi.repository;

import br.com.faculdade.navegacaoapi.model.TipoLocal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoLocalRepository extends JpaRepository<TipoLocal, Long> {}