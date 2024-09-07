package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "empleados")
public class Empleado {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter
	private int id;

	@Getter @Setter
	private int nroDocumento;

	@Getter @Setter
	private String nombre;

	@Getter @Setter
	private String apellido;

	@Getter @Setter
	private String email;

	@Getter @Setter
	private String fechaNacimiento;

	@Getter @Setter
	private String fechaIngreso;

}
