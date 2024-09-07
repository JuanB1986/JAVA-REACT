package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entities.ConceptoLaboral;
import entitiesDTO.ConceptoLaboralDTO;
import repositories.ConceptoLaboralRepository;

@Service
public class ConceptoLaboralService {

    @Autowired
    private ConceptoLaboralRepository conceptoLaboralRepository;

    //GET
    public List<ConceptoLaboralDTO> getConceptos(Integer id, String nombre){

		List<ConceptoLaboral> listaConceptos=new ArrayList<>();

		if (id != null && nombre != null) {
		    listaConceptos = conceptoLaboralRepository.findByIdAndNombre(id, nombre);
		} else if (id != null) {
	        Optional<ConceptoLaboral> optionalConcepto = conceptoLaboralRepository.findById(id);
	        listaConceptos = optionalConcepto.map(List::of).orElseGet(ArrayList::new);
		} else if (nombre != null) {
		    listaConceptos = conceptoLaboralRepository.findByNombreContaining(nombre);
		} else {
		    listaConceptos = conceptoLaboralRepository.findAll();
		}

		List<ConceptoLaboralDTO> listaConceptosDTO = new ArrayList<>();

		listaConceptos.forEach( m -> listaConceptosDTO.add(new ConceptoLaboralDTO(m)));     //convierto a objeto DTO para devolver

		return listaConceptosDTO;
    }
}