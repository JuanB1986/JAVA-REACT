package entitiesDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import entities.Empleado;
import lombok.Getter;
import lombok.Setter;

public class EmpleadoDTO {

	@Getter
	private int id;

	@Getter
	private int nroDocumento;

	@Getter
	private String nombre;

	@Getter
	private String apellido;

	@Getter @Setter
	private String email;

	@Getter
	private String fechaNacimiento;

	@Getter
	private String fechaIngreso;

	@Getter
	private String fechaCreacion;

	public EmpleadoDTO(Empleado empleado) {
		this.id = empleado.getId();
		this.nroDocumento = empleado.getNroDocumento();
		this.nombre = empleado.getNombre();
		this.apellido = empleado.getApellido();
		this.email = empleado.getEmail();
		this.fechaNacimiento = empleado.getFechaNacimiento();
		this.fechaIngreso = empleado.getFechaIngreso();
		this.fechaCreacion = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}

}
