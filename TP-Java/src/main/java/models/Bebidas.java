package models;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

public class Bebidas extends Producto {

	@Getter private boolean importado;
	@Getter @Setter float graduacionAlcoholica;
	@Getter @Setter LocalDate fechaVencimiento;
	@Getter float calorias;							//Parámetro calculado.


	public Bebidas(int id, String descripcion, float precioUnitario, float porcentajeGanancia,float porcentajeDescuento, boolean disponibleParaVenta, boolean importado, LocalDate fechaVencimiento, float graduacionAlcoholica){

		super(id, descripcion,  precioUnitario,  porcentajeGanancia, porcentajeDescuento, disponibleParaVenta );

		this.setId(id);
		this.porcentajeDescuento = this.setPorcentajeDescuento(porcentajeDescuento);	//Valida porcentaje Descuento
		this.porcentajeGanancia = this.setPorcentajeGanancia(porcentajeGanancia);		//Valida porcentaje Ganancia
		this.importado=importado;
		this.fechaVencimiento=fechaVencimiento;
		this.graduacionAlcoholica=graduacionAlcoholica;
		this.calorias=this.setCalorias(this.getCalorias());
	}


	@Override
	public float setPorcentajeGanancia(float porcentaje) {
		this.porcentajeGanancia = this.validaNumeroReal(porcentaje ,20.0f, 0.0f);
		return this.porcentajeGanancia;
	}

	@Override
	public float setPorcentajeDescuento(float porcentaje) {
		this.porcentajeDescuento = this.validaNumeroReal(porcentaje ,10.0f, 0.0f);
		return this.porcentajeDescuento;
	}

	@Override
	public float getPrecioVenta() {
		if (this.isImportado()) {
			float precioVenta = this.getPrecioUnitario()*(1 + this.getPorcentajeGanancia()/100.0f);
			return precioVenta += precioVenta*0.12f;											//Aplica impuesto del 12% a los importados.
		}
		return getPrecioCompra()*(1 + this.getPorcentajeGanancia()/100.0f);
	}

	@Override
	public float getPrecioCompra() {
		return this.getPrecioUnitario()*(1 - this.getPorcentajeDescuento()/100.0f);
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


	private float validaNumeroReal(float valor, float max, float min) {
		float retVal=valor;

		if (valor<min) {
			retVal=min;
		}
		if (valor>max) {
			retVal=max;
		}
		return retVal;
	}

}
