package models;

import java.util.ArrayList;
import java.util.List;

import exceptions.UserRepeatedIDExceptionError;
import lombok.Getter;
import lombok.Setter;

public class Limpieza extends Producto {

	private static List<Integer> ListId = new ArrayList<>();

	@Getter @Setter TipoProductoLimpieza tipoAplicacion;

	public Limpieza(int id, String descripcion, float precioUnitario, float porcentajeGanancia, boolean disponibleParaVenta,TipoProductoLimpieza tipoAplicacion) throws UserRepeatedIDExceptionError {

		super(id, descripcion,  precioUnitario,  porcentajeGanancia,  disponibleParaVenta );

		if (ListId.contains(id)) {
			throw new UserRepeatedIDExceptionError();
		}

		Limpieza.ListId.add(id);
		this.tipoAplicacion = tipoAplicacion;
	}


	@Override
	public String setId(int id) {
		return "AZ"+String.format("%03d", id);
	}


}
