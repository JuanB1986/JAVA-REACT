package repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.ConceptoLaboral;




public interface ConceptoLaboralRepository extends JpaRepository<ConceptoLaboral, Integer> {

    List<ConceptoLaboral> findByIdAndNombre(Integer id, String nombre);

    @Override
	Optional<ConceptoLaboral> findById(Integer id);

    List<ConceptoLaboral> findByNombreContaining(String nombre);

    @Override
	List<ConceptoLaboral> findAll();

}
