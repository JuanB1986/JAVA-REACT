package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import exceptions.UserRepeatedIDExceptionError;
import lombok.Getter;
import lombok.Setter;

public class Envasados extends Producto {

	private static List<Integer> ListId = new ArrayList<>();

	@Getter private String envase;
	@Getter private boolean importado;
	@Getter @Setter LocalDate fechaVencimiento;
	@Getter @Setter float calorias;					//Parámetro calculado.


	public Envasados(int id, String descripcion, float precioUnitario, float porcentajeGanancia, boolean disponibleParaVenta, String envase, boolean importado, LocalDate fechaVencimiento)throws UserRepeatedIDExceptionError {

		super(id, descripcion,  precioUnitario,  porcentajeGanancia,  disponibleParaVenta );

		if (ListId.contains(id)) {
			throw new UserRepeatedIDExceptionError();
		}

		Envasados.ListId.add(id);
		this.importado=importado;
		this.fechaVencimiento=fechaVencimiento;
		this.envase=envase;
	}


	@Override
	public String setId(int id) {
		return "AB"+String.format("%03d", id);
	}


}
