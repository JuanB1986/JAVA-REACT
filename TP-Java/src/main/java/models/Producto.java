package models;

import lombok.Getter;
import lombok.Setter;

public abstract class Producto {

	@Getter private String id;						//Unique - Readonly - (Parámetro calculado).
	@Getter @Setter private String descripcion;
	@Getter @Setter private int cantidadEnStock;
	@Getter @Setter private float precioUnitario;
	@Getter protected float porcentajeGanancia;
	@Getter protected float porcentajeDescuento;
	@Getter @Setter private boolean disponibleParaVenta;


	protected Producto(int id,String descripcion, float precioUnitario, float porcentajeGanancia, float porcentajeDescuento, boolean disponibleParaVenta){
		this.id=this.setId(id);
		this.cantidadEnStock=0;
		this.descripcion=descripcion;
		this.precioUnitario=precioUnitario;
		this.porcentajeGanancia=porcentajeGanancia;
		this.disponibleParaVenta=disponibleParaVenta;
	}

	protected abstract String setId(int id);
	public abstract float setPorcentajeGanancia(float porcentaje);
	public abstract float setPorcentajeDescuento(float porcentaje);
	public abstract float getPrecioVenta();
	public abstract float getPrecioCompra();


	public void sumarStock(int cantidad) {
		this.cantidadEnStock += cantidad;
	}

	public void restarStock(int cantidad) {
		this.cantidadEnStock -= cantidad;
	}







}
