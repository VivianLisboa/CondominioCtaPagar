package br.com.vivian.condominioctapagar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.vivian.condominioctapagar.domain.CtaPagar;

@Repository
public interface CtaPagarRepository extends JpaRepository<CtaPagar, Integer> {

	@Query("SELECT c FROM CtaPagar c WHERE c.condominio.id_condominio = :id_condominio")
	public Optional<CtaPagar> findById(@Param("id_condominio") Integer id);
}
