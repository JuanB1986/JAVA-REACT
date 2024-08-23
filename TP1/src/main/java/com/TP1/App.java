package com.TP1;

import testing.TestApp;

/******************************************************************************************
 * Nombre y Apellido:	Juan M. Banquero
 * Fecha:				22/08/2024
 * Descripción:			Laboratorio JAVA-REACT: Trabajo Práctico 1
 ******************************************************************************************
 * NOTAS:
 * 	1) El precio de compra de los productos se calcula:
 * 			PrecioDeCompra = PrecioUnitario * (1 - PorcentajeDescuento/100).
 *
 *  2) El precio de venta se calcula en base al precio unitario del producto.
 *  		PrecioVenta = PrecioUnitario * (1 + PorcentajeGanancia/100).
 *
 ******************************************************************************************
 * OPERACIÓN DE LA TIENDA:
 * 	La tienda puede ejecutar compras y ventas de productos. Al comprar, se usa el mismo
 *  critério de compra, que al comprar en un mayorista. El Mayorista tiene un precio de
 *  lista (PrecioUnitario) y nos aplica un descuento a cada producto por la compra.
 *
 * 	En la operación de venta aplicamos un porcentaje de ganancia al producto,
 * 	sobre el precio de lista (PrecioiUnitario) del Mayorista, para maximizar la utilidad.
 ******************************************************************************************
 *	Constructor:
 *		Tienda( String nombre, float SaldoInicial, int numMaximoDeProductosEnStock);
 *
 *	Métodos:
 *		+compar();
 *		+vender();
 *		+agregarAlCarrito(Producto producto, int cantidad);
 *		+vaciarCarrito();
 *
 *	COMPRA: se ejecuta el metodo "agregarAlCarrito" y se pasa el producto y la cantidad
 *			a comprar como argumentos, luego se ejecuta el metodo "comprar". Este mismo,
 *			devolverá el ticket de la compra una vez ejecutado.
 *
 *	VENDER:	Igual que en la compra pero se ejecuta el método "vender".
 ***************************************************************************************/

public class App
{
    public static void main( String[] args )
    {

		TestApp.crearObjetos();

		//TestApp.verificarMaximoDeProductosEnStockAComprar();

		//TestApp.verificarPorcentajeDescuentoParaCompra();

		//TestApp.verificarSaldoInsuficienteEnCompra();

		//TestApp.venderUnProductoQueNoEstaEnStock();

		//TestApp.verificarSaldoAlVender();

		//TestApp.venderMaximoDeNProductos();

		//TestApp.venderProductoConMenorStock();

		//TestApp.venderProductoNoDisponibleEnStock();

		//TestApp.verificarCaloriasEnBebidas();

		//TestApp.verificarLimiteDePorcentajesDescuentoYGanancia();

		TestApp.verificarListaFiltrada(11.2f);		//PASO PORCENTAJE DE DESCUENTO A FILTRAR, POR PARÁMETRO.
    }
}





