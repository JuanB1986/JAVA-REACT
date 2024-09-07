package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entities.Empleado;


@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer>{
	boolean existsBynroDocumento(int nroDocumento);
	boolean existsByemail(String email);
}
