package entitiesDTO;

import entities.ConceptoLaboral;
import lombok.Getter;

public class ConceptoLaboralDTO {

    private Integer id;
    @Getter
    private Integer hsMaximo;
    @Getter
    private Integer hsMinimo;
    @Getter
    private Boolean laborable;
    @Getter
    private String nombre;

    public ConceptoLaboralDTO(ConceptoLaboral conceptoLaboral) {
    	this.hsMaximo=conceptoLaboral.getHs_maximo();
    	this.hsMinimo=conceptoLaboral.getHs_minimo();
    	this.laborable=conceptoLaboral.isLaborable();
    	this.nombre = conceptoLaboral.getNombre();
    }
}