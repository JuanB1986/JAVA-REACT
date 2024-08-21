package tp_Java;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import DAO.Tienda;
import TestApp.TestApp;
import models.Bebidas;
import models.Envasados;
import models.Limpieza;
import models.TipoProductoLimpieza;

public class App
{
    public static void main( String[] args )
    {


		TestApp.crearObjetos();

		//TestApp.verificarMaximoDeProductosEnStockAComprar();

		//TestApp.verificarPorcentajeDescuentoParaCompra();

		//TestApp.verificarSaldoInsuficienteEnCompra();

		//TestApp.venderUnProductoQueNoEstaEnStock();

		/**
		 * El porcentaje de ganancia se aplica sobre el precio unitario sin descuento del producto
		 */
		//TestApp.verificarSaldoAlVender();

		//TestApp.venderMaximoDeNProductos();

		//TestApp.venderProductoConMenorStock();
		
		TestApp.venderProductoNoDisponibleEnStock();
		

    	//System.out.println(LaTiendaDeAPU.obtenerComestiblesConMenorDescuento(8.0f));*/

    }
}





