package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import entitiesDTO.ConceptoLaboralDTO;
import services.ConceptoLaboralService;

@RestController
@RequestMapping("/concepto_laboral")
public class ConceptoLaboralController {

    @Autowired
    private ConceptoLaboralService conceptoLaboralService;

    @GetMapping
    public ResponseEntity<List<ConceptoLaboralDTO>> getConceptos(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "nombre", required = false) String nombre) {

        List<ConceptoLaboralDTO> conceptos = conceptoLaboralService.getConceptos(id, nombre);

        return new ResponseEntity<>(conceptos, HttpStatus.OK);
    }
}
