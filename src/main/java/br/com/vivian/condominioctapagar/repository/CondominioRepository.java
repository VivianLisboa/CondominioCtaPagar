package br.com.vivian.condominioctapagar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.vivian.condominioctapagar.domain.Condominio;

@Repository
public interface CondominioRepository extends JpaRepository<Condominio, Integer>{
	
	@Query("SELECT COUNT(1) AS existe FROM Condominio c WHERE c.cnpj = :cnpj")
	public Long validateExistClientByCnpj(@Param("cnpj")String cnpj);
	
	@Query("SELECT c FROM Condominio c WHERE c.cnpj = :cnpj")
	public Optional<Condominio>findByCnpj(@Param("cnpj")String cnpj);

}
