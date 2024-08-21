package models;

import lombok.Getter;
import lombok.Setter;

public class Limpieza extends Producto {

	@Getter @Setter TipoProductoLimpieza tipoAplicacion;

	public Limpieza(int id, String descripcion, float precioUnitario, float porcentajeGanancia, float porcentajeDescuento, boolean disponibleParaVenta,TipoProductoLimpieza tipoAplicacion){

		super(id, descripcion,  precioUnitario,  porcentajeGanancia,  porcentajeDescuento, disponibleParaVenta );

		this.setId(id);
		this.porcentajeDescuento = this.setPorcentajeDescuento(porcentajeDescuento);	//Valida porcentaje Descuento
		this.porcentajeGanancia = this.setPorcentajeGanancia(porcentajeGanancia);		//Valida porcentaje Ganancia
		this.tipoAplicacion = tipoAplicacion;
	}


	@Override
	public float setPorcentajeGanancia(float porcentaje) {
		if (this.tipoAplicacion==TipoProductoLimpieza.COCINA || this.tipoAplicacion==TipoProductoLimpieza.MULTIUSO)
		{
			this.porcentajeGanancia = this.validaNumeroReal(porcentaje ,25.0f, 10.0f);
		}
		else
		{
			this.porcentajeGanancia = this.validaNumeroReal(porcentaje ,25.0f, 0.0f);
		}
		return this.porcentajeGanancia;
	}

	@Override
	public float setPorcentajeDescuento(float porcentaje) {
		this.porcentajeDescuento = this.validaNumeroReal(porcentaje ,10.0f, 0.0f);
		return this.porcentajeDescuento;
	}

	@Override
	public float getPrecioVenta() {
		return this.getPrecioUnitario()*(1 + this.getPorcentajeGanancia()/100.0f);
	}

	@Override
	public float getPrecioCompra() {
		return this.getPrecioUnitario()*(1 - this.getPorcentajeDescuento()/100.0f);
	}


	@Override
	public String setId(int id) {
		return "AZ"+String.format("%03d", id);
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
