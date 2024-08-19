package DAO;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import models.EstadoCompra;
import models.Producto;

public class Tienda {

	private final int MAX_UNIDADES_A_VENDER = 12;
	private final int MAX_PRODUCTOS_A_VENDER = 3;

	@Getter private List<Producto> stockProductos = new ArrayList<>();
	@Getter @Setter private String nombre;
	@Getter @Setter private int numMaxProductosEnStock;
	@Getter private float saldoCaja;

	public Tienda(String nombre, float saldoInicial, int numMaxProductosEnStock) {
		this.numMaxProductosEnStock = numMaxProductosEnStock;
		this.nombre = nombre;
		this.saldoCaja = saldoInicial;
	}



	/**
	 * Método comprar producto
	 */
	public EstadoCompra comprar(Producto producto, int cantidad) {

		if (producto.getPrecio()*cantidad > this.saldoCaja) {
			return EstadoCompra.SALDO_INSUFICIENTE;
		}

		if (this.getCantidadTotalDeProductos()+cantidad >= this.numMaxProductosEnStock) {
			return EstadoCompra.MAXIMO_STOCK_ALCANZADO;
		}

		this.saldoCaja -= producto.getPrecio()*cantidad;

		if (this.stockProductos.contains(producto)) {
			int puntero= this.stockProductos.indexOf(producto);
			this.stockProductos.get(puntero).restarStock(cantidad);
		}
		else
		{
			Producto envasados = producto;
			envasados.setCantidadEnStock(cantidad);
			this.stockProductos.add(producto);
		}

		return EstadoCompra.OK;
	}



	/**
	 * Metodo: obtener cantidad todal de productos en la tienda.
	 */
	private int getCantidadTotalDeProductos() {

		int totalProductos = 0;

		for (Producto elementos : this.stockProductos) {
			totalProductos += elementos.getCantidadEnStock();
		}
		return totalProductos;
	}




	/**
	 * Metodo vender
	 */
	public String vender(List<Producto> listaProductosAVender, List<Integer> listaCantidadesAVender) {
		float totalVendido=0.0f;
		float descuentoVenta = 0.0f;
		String ticketDeVenta="********************** KWIK-E-MART **********************\n";


		/**
		 * Itera ambas listas para buscar el producto por ID.
		*/
		int indiceCantidades = 0;
		int cantidadDeProductosVendidos=0;

		for (Producto productoVenta : listaProductosAVender) {

			/**
			 * Máximo de n productos a vender alcanzado.
			 */
			if(cantidadDeProductosVendidos>=3) {
				ticketDeVenta+="MÁXIMO ALCANZADO DE: "+MAX_PRODUCTOS_A_VENDER+" PRODUCTOS PARA VENDER\n";
				break;
			}

			for (Producto productoStock : this.stockProductos) {

				/**
				 * Imprime en el ticket, que el producto no está disponible para la venta.
				 */
				if (productoVenta.getId() == productoStock.getId() &&  !productoStock.isDisponibleParaVenta()) {
					ticketDeVenta += productoStock.getId()+"-"+productoStock.getDescripcion()+"(Producto no disponible para la venta)\n";
				}

				if (productoVenta.getId() == productoStock.getId() &&  productoStock.isDisponibleParaVenta())
				{
					/**
					 * Si la cantidad a vender es mas grande que la de stock, VENDO SOLO STOCK DISPONIBLE.
					 * Sino se resta del stock lo pedido para vender.
					*/
					if (productoStock.getCantidadEnStock() < listaCantidadesAVender.get(indiceCantidades))
					{
						totalVendido += productoStock.getCantidadEnStock()*productoStock.getPrecio(descuentoVenta);
						ticketDeVenta += productoStock.getId()+"-"+productoStock.getDescripcion()+"- Cant: "+listaCantidadesAVender.get(indiceCantidades)+" x $"+productoStock.getPrecio(descuentoVenta)+" (Stock menor al solicitado para vender)\n";
						productoStock.setCantidadEnStock(0);
						productoStock.setDisponibleParaVenta(false);
					}
					else
					{
						totalVendido += listaCantidadesAVender.get(indiceCantidades)*productoStock.getPrecio(descuentoVenta);
						ticketDeVenta += productoStock.getId()+"-"+productoStock.getDescripcion()+"- Cant: "+listaCantidadesAVender.get(indiceCantidades) +" x $"+productoStock.getPrecio(descuentoVenta)+"\n";
						productoStock.restarStock(listaCantidadesAVender.get(indiceCantidades));

						if(productoStock.getCantidadEnStock()==0) {
							productoStock.setDisponibleParaVenta(false);
						}
					}
					cantidadDeProductosVendidos++;
					indiceCantidades++;
				}
			}
		}


		/**
		 * Actualiza el saldo en caja y devuelve el ticket.
		 */
		this.saldoCaja += totalVendido;
		ticketDeVenta += "Total Vendido: $"+ String.valueOf(totalVendido)+"\n";
		ticketDeVenta += "*********************************************************";
		return ticketDeVenta;

	}
}
