package models;

import lombok.Getter;
import lombok.Setter;

public abstract class Producto {

	@Getter private String id;						//Unique - Readonly - (Parámetro calculado).
	@Getter @Setter private String descripcion;
	@Getter @Setter private int cantidadEnStock;
	@Getter @Setter private float precioUnitario;
	@Getter @Setter private float porcentajeGanancia;
	@Getter @Setter private boolean disponibleParaVenta;


	protected Producto(int id,String descripcion, float precioUnitario, float porcentajeGanancia, boolean disponibleParaVenta){
		this.id=this.setId(id);
		this.cantidadEnStock=0;
		this.descripcion=descripcion;
		this.precioUnitario=precioUnitario;
		this.porcentajeGanancia=porcentajeGanancia;
		this.disponibleParaVenta=disponibleParaVenta;
	}

	protected abstract String setId(int id);


	public float getPrecio() {
		return this.precioUnitario;
	}

	public float getPrecio(float porcentajeDescuento) {
		return this.precioUnitario -= porcentajeDescuento/100.0f;
	}

	public void sumarStock(int cantidad) {
		this.cantidadEnStock += cantidad;
	}

	public void restarStock(int cantidad) {
		this.cantidadEnStock -= cantidad;
	}

}
