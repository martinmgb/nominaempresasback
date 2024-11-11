package cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.model.entity.Trabajador;

/**
 * Interface Repository para tratamiento de datos de la entidad Trabajador
 * Entity: Trabajador
 */
@Repository
public interface TrabajadorRepository extends JpaRepository<Trabajador, Long> {

	/**
	 * Implementación de @Query para buscar los trabajadores de una empresa
	 * @param idEmpresa
	 * @return List<Trabajador> trabajadores de la empresa
	 */
	@Query("SELECT t FROM Trabajador t WHERE t.empresa.id = :idEmpresa")
	List<Trabajador> findByEmpresaId(@Param("idEmpresa") String idEmpresa);
	
	Optional<Trabajador> findByNumeroRut(Long numeroRut);
}
