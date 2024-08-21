package TestApp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import DAO.Tienda;
import models.Bebidas;
import models.Envasados;
import models.Limpieza;
import models.TipoProductoLimpieza;

public class TestApp {


	static final float PORC_DESC_BE = 10.0f;
	static final float PORC_DESC_LI = 10.0f;
	static final float PORC_DESC_EN = 10.0f;

	static final float PORC_GAN_BE = 15.0f;
	static final float PORC_GAN_LI = 15.0f;
	static final float PORC_GAN_EN = 15.0f;

	/**
	 * Instacio las listas de objetos de productos, Bebidas, Envasados y Limpieza;
	 */
	private static List<Limpieza> listaLimpieza = new ArrayList<>();
	private static List<Bebidas> listaBebidas = new ArrayList<>();
	private static List<Envasados> listaEnvasados = new ArrayList<>();


	private static void TestApp() {}

	public static void crearObjetos() {
		listaLimpieza.add( new Limpieza(1,"Lavandina",2210.0f,PORC_GAN_LI,PORC_DESC_LI,true,TipoProductoLimpieza.MULTIUSO) );
		listaLimpieza.add( new Limpieza(2,"Jabón Liquido",3210.0f,PORC_GAN_LI,PORC_DESC_LI,true,TipoProductoLimpieza.ROPA));
		listaLimpieza.add( new Limpieza(3,"Mr. Musculo Antigrasa",1253.0f,PORC_GAN_LI,PORC_DESC_LI,true,TipoProductoLimpieza.COCINA));
		listaLimpieza.add( new Limpieza(4,"Shampoo",780.0f,PORC_GAN_LI,PORC_DESC_LI,true,TipoProductoLimpieza.BAÑO));
		listaLimpieza.add( new Limpieza(5,"Pasta de dientes",365.0f,PORC_GAN_LI,PORC_DESC_LI,true,TipoProductoLimpieza.BAÑO));

		listaBebidas.add( new Bebidas(1,"Coca Cola",3250.0f, PORC_GAN_BE,PORC_DESC_BE,true,false,LocalDate.of(2029,4,17),0.0f) );
		listaBebidas.add( new Bebidas(2,"Cerveza Hoegaarden",4000.0f, PORC_GAN_BE,PORC_DESC_BE,true,true,LocalDate.of(2027,8,1),6.5f) );
		listaBebidas.add( new Bebidas(3,"Vino Tinto",6450.0f, PORC_GAN_BE,PORC_DESC_BE,true,false,null,12.6f));
		listaBebidas.add( new Bebidas(4,"Cerveza Grolsh",7700.0f, PORC_GAN_BE,PORC_DESC_BE,true,true,LocalDate.of(2033,4,4),10.2f));

		listaEnvasados.add(new Envasados(1,"Salchichas", 10.0f, PORC_GAN_EN,PORC_DESC_EN,true,"Plástico",false,LocalDate.of(2025,1,1)));
		listaEnvasados.add(new Envasados(2,"Arvejas", 652.5f, PORC_GAN_EN,PORC_DESC_EN,true,"Lata Metalica 300g",false,LocalDate.of(2024,12,12)));
		listaEnvasados.add(new Envasados(3,"Leche", 1652.5f, PORC_GAN_EN,PORC_DESC_EN,true,"Envase Plástico",false,LocalDate.of(2025,2,2)));
		listaEnvasados.add(new Envasados(4,"Fideos", 352.5f, PORC_GAN_EN,PORC_DESC_EN,true,"Envoltorio Plástico",false,LocalDate.of(2026,3,6)));
		listaEnvasados.add(new Envasados(5,"Sopa Ramen", 1352.5f, PORC_GAN_EN,PORC_DESC_EN,true,"Envoltorio Plástico",true,LocalDate.of(2028,3,6)));
	}


	public static void verificarMaximoDeProductosEnStockAComprar() {

		Tienda LaTiendaDeAPU = new Tienda("KWIK-E-MART", 10000, 10);

		for (int i=0; i<5; ++i) {

			LaTiendaDeAPU.agregarAlCarrito(listaEnvasados.get(0), 3);
		}

		System.out.println(LaTiendaDeAPU.comprar());
	}



	public static void verificarPorcentajeDescuentoParaCompra() {
		Tienda LaTiendaDeAPU = new Tienda("KWIK-E-MART", 10000, 20);

		System.out.println(listaBebidas.get(1).getDescripcion()+ " P.u: $"+listaBebidas.get(1).getPrecioUnitario() + " % Dto: "+listaBebidas.get(1).getPorcentajeDescuento()+" Precio compra: $"+listaBebidas.get(1).getPrecioCompra());
		System.out.println(listaBebidas.get(2).getDescripcion()+ " P.u: $"+listaBebidas.get(2).getPrecioUnitario() + " % Dto: "+listaBebidas.get(2).getPorcentajeDescuento()+" Precio compra: $"+listaBebidas.get(2).getPrecioCompra());

		System.out.println("Saldo Esperado: $"+ (LaTiendaDeAPU.getSaldoCaja()-listaBebidas.get(1).getPrecioCompra()-listaBebidas.get(2).getPrecioCompra()));

		LaTiendaDeAPU.agregarAlCarrito(listaBebidas.get(1), 1);
		LaTiendaDeAPU.agregarAlCarrito(listaBebidas.get(2), 1);
		System.out.println(LaTiendaDeAPU.comprar());
	}


	public static void verificarSaldoInsuficienteEnCompra() {
		Tienda LaTiendaDeAPU = new Tienda("KWIK-E-MART", 10000, 20);

		System.out.println(listaBebidas.get(1).getDescripcion()+ " P.u: $"+listaBebidas.get(1).getPrecioUnitario() + " % Dto: "+listaBebidas.get(1).getPorcentajeDescuento()+" Precio compra: $"+listaBebidas.get(1).getPrecioCompra());

		LaTiendaDeAPU.agregarAlCarrito(listaBebidas.get(1), 2);
		System.out.println(LaTiendaDeAPU.comprar());

		LaTiendaDeAPU.agregarAlCarrito(listaBebidas.get(1), 1);
		System.out.println(LaTiendaDeAPU.comprar());

	}


	public static void venderUnProductoQueNoEstaEnStock() {
		Tienda LaTiendaDeAPU = new Tienda("KWIK-E-MART", 10000, 20);

		LaTiendaDeAPU.agregarAlCarrito(listaBebidas.get(1), 1);
		LaTiendaDeAPU.agregarAlCarrito(listaLimpieza.get(1), 1);

		System.out.println(LaTiendaDeAPU.comprar());

		LaTiendaDeAPU.agregarAlCarrito(listaLimpieza.get(2), 1);
		System.out.println(LaTiendaDeAPU.vender());

	}


	public static void verificarSaldoAlVender() {
		Tienda LaTiendaDeAPU = new Tienda("KWIK-E-MART", 50000, 20);

		LaTiendaDeAPU.agregarAlCarrito(listaBebidas.get(1), 4);
		System.out.println(LaTiendaDeAPU.comprar());

		LaTiendaDeAPU.agregarAlCarrito(listaBebidas.get(1), 2);
		System.out.println(LaTiendaDeAPU.vender());
	}


	public static void venderMaximoDeNProductos() {
		Tienda LaTiendaDeAPU = new Tienda("KWIK-E-MART", 50000, 20);

		LaTiendaDeAPU.agregarAlCarrito(listaBebidas.get(0), 2);
		LaTiendaDeAPU.agregarAlCarrito(listaBebidas.get(1), 2);
		LaTiendaDeAPU.agregarAlCarrito(listaBebidas.get(2), 2);
		LaTiendaDeAPU.agregarAlCarrito(listaLimpieza.get(0), 2);
		LaTiendaDeAPU.agregarAlCarrito(listaLimpieza.get(1), 2);
		System.out.println(LaTiendaDeAPU.comprar());

		LaTiendaDeAPU.agregarAlCarrito(listaBebidas.get(0), 1);
		LaTiendaDeAPU.agregarAlCarrito(listaBebidas.get(1), 1);
		LaTiendaDeAPU.agregarAlCarrito(listaBebidas.get(2), 1);
		LaTiendaDeAPU.agregarAlCarrito(listaLimpieza.get(0), 1);
		System.out.println(LaTiendaDeAPU.vender());
	}


	public static void venderProductoConMenorStock() {
		Tienda LaTiendaDeAPU = new Tienda("KWIK-E-MART", 50000, 20);

		LaTiendaDeAPU.agregarAlCarrito(listaLimpieza.get(1), 2);
		System.out.println(LaTiendaDeAPU.comprar());

		LaTiendaDeAPU.agregarAlCarrito(listaLimpieza.get(1), 5);
		System.out.println(LaTiendaDeAPU.vender());
	}

	
	public static void venderProductoNoDisponibleEnStock() {
		Tienda LaTiendaDeAPU = new Tienda("KWIK-E-MART", 50000, 20);

		LaTiendaDeAPU.agregarAlCarrito(listaLimpieza.get(1), 2);
		System.out.println(LaTiendaDeAPU.comprar());

		LaTiendaDeAPU.agregarAlCarrito(listaLimpieza.get(1), 2);
		System.out.println(LaTiendaDeAPU.vender());
		
		LaTiendaDeAPU.agregarAlCarrito(listaLimpieza.get(1), 1);
		System.out.println(LaTiendaDeAPU.vender());
	}

}
