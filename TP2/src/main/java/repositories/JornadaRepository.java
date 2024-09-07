package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.Jornada;

public interface JornadaRepository extends JpaRepository<Jornada, Integer> {
	boolean existsBynroDocumento(int nroDocumento);
}