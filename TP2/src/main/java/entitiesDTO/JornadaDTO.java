package entitiesDTO;

import entities.ConceptoLaboral;
import entities.Empleado;
import lombok.Getter;
import lombok.Setter;

public class JornadaDTO {

    private Integer id;
    @Getter @Setter
    private Integer nroDocumento;
    @Getter @Setter
    private String nombreCompleto;
    @Getter @Setter
    private String fecha;
    @Getter @Setter
    private String conceptoLaboral;
    @Getter @Setter
    private Integer horasTrabajadas;

    public JornadaDTO(Empleado empleado, ConceptoLaboral conceptoLaboral, String fecha, int horasTrabajadas) {

    	this.id = empleado.getId();

    	this.nroDocumento = empleado.getNroDocumento();

    	this.nombreCompleto = empleado.getNombre() + " " +empleado.getApellido();

    	this.conceptoLaboral=conceptoLaboral.getNombre();

    	this.fecha = fecha;

    	this.horasTrabajadas = horasTrabajadas;

    }
}

