package models;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

public class Envasados extends Producto {

	@Getter private String envase;
	@Getter private boolean importado;
	@Getter @Setter LocalDate fechaVencimiento;
	@Getter @Setter float calorias;					//Parámetro calculado.


	public Envasados(int id, String descripcion, float precioUnitario, float porcentajeGanancia, float porcentajeDescuento, boolean disponibleParaVenta, String envase, boolean importado, LocalDate fechaVencimiento){

		super(id, descripcion,  precioUnitario,  porcentajeGanancia, porcentajeDescuento, disponibleParaVenta );

		this.setId(id);
		this.porcentajeDescuento = this.setPorcentajeDescuento(porcentajeDescuento);	//Valida porcentaje Descuento
		this.porcentajeGanancia = this.setPorcentajeGanancia(porcentajeGanancia);		//Valida porcentaje Ganancia
		this.importado=importado;
		this.fechaVencimiento=fechaVencimiento;
		this.envase=envase;
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
		return "AB"+String.format("%03d", id);
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
