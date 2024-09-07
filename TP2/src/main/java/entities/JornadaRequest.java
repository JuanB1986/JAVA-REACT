package entities;

import lombok.Getter;

public class JornadaRequest {

	@Getter
	private Integer idEmpleado;
	@Getter
	private Integer idConcepto;
	@Getter
	private String fecha;
	@Getter
	private Integer horasTrabajadas;

}
