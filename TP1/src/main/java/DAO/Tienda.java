package DAO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;
import models.Bebidas;
import models.Envasados;
import models.Producto;

public class Tienda {

	private final int MAX_UNIDADES_A_VENDER = 12;
	private final int MAX_PRODUCTOS_A_VENDER = 3;

	@Getter private List<Producto> stockProductos = new ArrayList<>();
	@Getter @Setter private String nombre;
	@Getter @Setter private int numMaxProductosEnStock;
	@Getter private float saldoCaja;

	private List<Producto> productosCompraVenta = new ArrayList<>();
	private List<Integer> cantidadesCompraVenta = new ArrayList<>();

	public Tienda(String nombre, float saldoInicial, int numMaxProductosEnStock) {
		this.numMaxProductosEnStock = numMaxProductosEnStock;
		this.nombre = nombre;
		this.saldoCaja = saldoInicial;
		this.productosCompraVenta.clear();
		this.cantidadesCompraVenta.clear();
	}

	public Tienda getTienda() {
		return this;
	}


	public void agregarAlCarrito(Producto producto, int cantidad) {
		productosCompraVenta.add(producto);
		cantidadesCompraVenta.add(cantidad);
	}


	public void vaciarCarrito() {
		productosCompraVenta.clear();
		cantidadesCompraVenta.clear();
	}


	public int getCantidadTotalDeProductos() {

		int totalProductos = 0;

		if (this.stockProductos.size()==0) {
			return 0;
		}

		for (Producto elementos : this.stockProductos) {
			totalProductos += elementos.getCantidadEnStock();
		}
		return totalProductos;
	}


	/**
	 * PARTE 3: Filtrado de la lista
	 */
	public String obtenerComestiblesConMenorDescuento(float descuento)
	{
		List<Producto> productosFiltrados = new ArrayList<>();
		productosFiltrados = this.stockProductos.stream().filter(m -> ((m instanceof Bebidas) && !((Bebidas) m).isImportado() && m.getPorcentajeDescuento() < descuento)	//Saca las bebidas importadas y filtra por descuento.
				||	((m instanceof Envasados)&& m.getPorcentajeDescuento() < descuento)																							//Envasados: filtra por porcentaje de descuento.
				).collect(Collectors.toList());

		productosFiltrados.sort(Comparator.comparing(Producto::getPrecioVenta));

		List<String> lista = productosFiltrados.stream().map(m -> (m.getDescripcion()+" (%Desc: "+m.getPorcentajeDescuento()+" - Precio Venta: $"+m.getPrecioVenta()+")").toUpperCase()).toList();

		String ret = String.join("\n", lista);

		return ret;
	}


	/**
	 * Método comprar un producto, por x cantidad de unidades.
	 */
	public String comprar() {

		String ticketDeVenta="********************** TICKET DE COMPRA (KWIK-E-MART) **********************\n";
		int punteroCantidad = 0;

		for(Producto producto : this.productosCompraVenta) {

			if (producto.getPrecioCompra()*this.cantidadesCompraVenta.get(punteroCantidad) > this.saldoCaja) {
				ticketDeVenta+=" --Saldo insuficiente para comprar--\n";
				ticketDeVenta += "****************************************************************************";
				this.vaciarCarrito();
				return ticketDeVenta;
			}

			if (this.getCantidadTotalDeProductos() + this.cantidadesCompraVenta.get(punteroCantidad) >= this.numMaxProductosEnStock) {
				ticketDeVenta+=" --Stock máximo de productos alcanzados para comprar--\n";
				ticketDeVenta += "****************************************************************************";
				this.vaciarCarrito();
				return ticketDeVenta;
			}

			this.saldoCaja -= producto.getPrecioCompra()*this.cantidadesCompraVenta.get(punteroCantidad);

			/**
			 * Veo si el producto que voy a comprar se encuentra la lista.
			 * Sino, lo agrega como un nuevo producto y su cantidad comprada.
			 */
			if (this.stockProductos.contains(producto)) {
				int puntero= this.stockProductos.indexOf(producto);
				this.stockProductos.get(puntero).sumarStock(this.cantidadesCompraVenta.get(punteroCantidad));
			}
			else
			{
				Producto envasados = producto;
				envasados.setCantidadEnStock(this.cantidadesCompraVenta.get(punteroCantidad));
				this.stockProductos.add(producto);
			}
			ticketDeVenta+=" "+producto.getId()+ " - "+producto.getDescripcion()+" - Cant: "+ this.cantidadesCompraVenta.get(punteroCantidad)+" x " + " $"+producto.getPrecioCompra()+"\n";
			punteroCantidad++;

		}
		ticketDeVenta+="SALDO EN CAJA: $"+this.getSaldoCaja()+"\n" ;
		ticketDeVenta += "****************************************************************************";
		this.vaciarCarrito();
		return ticketDeVenta;
	}



	/**
	 * Metodo vender Productos de la Lista carrito
	 */
	public String vender() {

		float totalVendido=0.0f;
		String ticketDeVenta="********************** TICKET DE VENTA (KWIK-E-MART) ***********************\n";
		String mensajeAgregadoATicket="";

		/**
		 * Itera ambas listas para buscar el producto por ID.
		*/
		int indiceCantidades = 0;
		int cantidadDeProductosVendidos=0;

		for (Producto productoVenta : this.productosCompraVenta) {

			/**
			 * Máximo de número productos a vender alcanzado. Lo seteo en la constante MAX_PRODUCTOS_A_VENDER
			 */
			if(cantidadDeProductosVendidos>= MAX_PRODUCTOS_A_VENDER) {
				ticketDeVenta+=" --MÁXIMO ALCANZADO DE: " + MAX_PRODUCTOS_A_VENDER+" PRODUCTOS PARA VENDER--\n";
				break;
			}

			for (Producto productoStock : this.stockProductos) {

				/**
				 * Imprime en el ticket, si el producto no está disponible para la venta.
				 */
				if (productoVenta.getId() == productoStock.getId() &&  !productoStock.isDisponibleParaVenta()) {
					mensajeAgregadoATicket += " (Producto no disponible para la venta)";
				}


				if (productoVenta.getId() == productoStock.getId() &&  productoStock.isDisponibleParaVenta())
				{
					/**
					 * Chequeo si la cantidad del producto a vender es menor o igual a lo requerido.
					 * sino, limito la cantidad. Lo dejo seteado en una constante (MAX_UNIDADES_A_VENDER)
					 */
					if (this.cantidadesCompraVenta.get(indiceCantidades) > MAX_UNIDADES_A_VENDER)
					{
						this.cantidadesCompraVenta.set(indiceCantidades, MAX_UNIDADES_A_VENDER);
						mensajeAgregadoATicket += " (No se puede vender más de " +MAX_UNIDADES_A_VENDER+" unidades)";
					}

					/**
					 * Si la cantidad de unidades a vender es mas grande que la de stock, VENDO SOLO STOCK DISPONIBLE.
					 */
					if (productoStock.getCantidadEnStock() < this.cantidadesCompraVenta.get(indiceCantidades))
					{
						totalVendido += productoStock.getCantidadEnStock()*productoStock.getPrecioVenta();
						mensajeAgregadoATicket += " (Stock menor al solicitado)";
						this.cantidadesCompraVenta.set(indiceCantidades, productoStock.getCantidadEnStock());
						productoStock.setCantidadEnStock(0);
						productoStock.setDisponibleParaVenta(false);
					}
					else
					{
						totalVendido += this.cantidadesCompraVenta.get(indiceCantidades)*productoStock.getPrecioVenta();
						productoStock.restarStock(this.cantidadesCompraVenta.get(indiceCantidades));

						/**
						 * Si vendo la cantidad total de unidades de un producto, lo EXCLUYO de la venta.
						 */
						if(productoStock.getCantidadEnStock()==0) {
							productoStock.setDisponibleParaVenta(false);
						}
					}
					cantidadDeProductosVendidos++;
				}
			}
			ticketDeVenta += " "+productoVenta.getId()+" - "+productoVenta.getDescripcion()+"- Cant: "+this.cantidadesCompraVenta.get(indiceCantidades) +" x $"+productoVenta.getPrecioVenta()+mensajeAgregadoATicket+"\n";
			mensajeAgregadoATicket="";
			indiceCantidades++;
		}

		this.vaciarCarrito();
		/**
		 * Actualiza el saldo en caja y devuelve el ticket.
		 */
		this.saldoCaja += totalVendido;
		ticketDeVenta += "Total Vendido: $"+ String.valueOf(totalVendido)+"\n";
		ticketDeVenta += "SALDO EN CAJA $: $"+ String.valueOf(this.saldoCaja)+"\n";
		ticketDeVenta += "****************************************************************************";
		return ticketDeVenta;

	}
}
