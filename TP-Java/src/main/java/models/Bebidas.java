package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import exceptions.UserRepeatedIDExceptionError;
import lombok.Getter;
import lombok.Setter;

public class Bebidas extends Producto {

	private static List<Integer> ListId = new ArrayList<>();

	@Getter private boolean importado;
	@Getter @Setter float graduacionAlcoholica;
	@Getter @Setter LocalDate fechaVencimiento;
	@Getter float calorias;						//Parámetro calculado.


	public Bebidas(int id, String descripcion, float precioUnitario, float porcentajeGanancia, boolean disponibleParaVenta, boolean importado, LocalDate fechaVencimiento, float graduacionAlcoholica)throws UserRepeatedIDExceptionError {

		super(id, descripcion,  precioUnitario,  porcentajeGanancia,  disponibleParaVenta );

		if (ListId.contains(id)) {
			throw new UserRepeatedIDExceptionError();
		}

		Bebidas.ListId.add(id);
		this.importado=importado;
		this.fechaVencimiento=fechaVencimiento;
		this.graduacionAlcoholica=graduacionAlcoholica;
		this.calorias=this.setCalorias(this.getCalorias());

	}


	@Override
	public String setId(int id) {
		return "AC"+String.format("%03d", id);
	}


	public float setCalorias(float calorias) {

		if (this.getGraduacionAlcoholica()>=2.1f && this.getGraduacionAlcoholica() <= 4.5f) {
			return calorias * 1.25f;
		}
		if (this.getGraduacionAlcoholica()>4.5f) {
			return calorias * 1.5f;
		}
		return calorias;
	}

}
