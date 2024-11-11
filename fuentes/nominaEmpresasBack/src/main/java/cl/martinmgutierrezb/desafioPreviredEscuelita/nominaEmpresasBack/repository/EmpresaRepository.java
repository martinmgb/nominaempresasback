package cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.model.entity.Empresa;

/**
 * Interface Repository para tratamiento de datos de la entidad Empresa
 * Entity: Empresa
 */
@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, String> {

	Optional<Empresa> findByNumeroRut(Long numeroRut);
}
