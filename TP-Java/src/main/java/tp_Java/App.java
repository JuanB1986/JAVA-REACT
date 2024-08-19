package tp_Java;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import DAO.Tienda;
import config.PorcentajeGanancias;
import exceptions.UserRepeatedIDExceptionError;
import models.Bebidas;
import models.Envasados;
import models.Limpieza;
import models.Producto;
import models.TipoProductoLimpieza;

public class App
{
    public static void main( String[] args )
    {
    	/**
    	 * Instacio listas de objetos de productos;
    	 */

    	List<Limpieza> listaProductosLimpieza = new ArrayList<>();
    	try {
    		listaProductosLimpieza.add( new Limpieza(1,"Lavandina",2210.0f,PorcentajeGanancias.Limpieza(),true,TipoProductoLimpieza.MULTIUSO) );
    		listaProductosLimpieza.add( new Limpieza(2,"Jabón Liquido",3210.0f,PorcentajeGanancias.Limpieza(),true,TipoProductoLimpieza.ROPA));
    		listaProductosLimpieza.add( new Limpieza(3,"Mr. Musculo Antigrasa",1253.0f,PorcentajeGanancias.Limpieza(),true,TipoProductoLimpieza.COCINA));
    		listaProductosLimpieza.add( new Limpieza(4,"Shampoo",780.0f,PorcentajeGanancias.Limpieza(),true,TipoProductoLimpieza.BAÑO));
    		listaProductosLimpieza.add( new Limpieza(5,"Pasta de dientes",365.0f,PorcentajeGanancias.Limpieza(),true,TipoProductoLimpieza.BAÑO));
    	}catch(UserRepeatedIDExceptionError e) {System.out.println("ID Repetidos - Productos de Limpieza");}

    	List<Bebidas> listaBebidas = new ArrayList<>();
    	try {
    		listaBebidas.add( new Bebidas(1,"Coca Cola",3250.0f, PorcentajeGanancias.Bebidas(),true,false,LocalDate.of(2029,4,17),0.0f) );
    		listaBebidas.add( new Bebidas(2,"Cerveza Hoegardeen",4110.0f, PorcentajeGanancias.Bebidas(),true,true,LocalDate.of(2027,8,1),6.5f) );
    		listaBebidas.add( new Bebidas(3,"Vino Tinto",6450.0f, PorcentajeGanancias.Bebidas(),true,false,null,12.6f));
    		listaBebidas.add( new Bebidas(4,"Cerveza Grolsh",7700.0f, PorcentajeGanancias.Bebidas(),true,true,LocalDate.of(2033,4,4),10.2f));
    	}catch(UserRepeatedIDExceptionError e) {System.out.println("ID Repetidos - Bebidas");}

    	List<Envasados> listaEnvasados = new ArrayList<>();
    	try {
    		listaEnvasados.add(new Envasados(1,"Salchichas", 1000.0f, PorcentajeGanancias.Envasados(),true,"Plástico",false,LocalDate.of(2025,1,1)));
    		listaEnvasados.add(new Envasados(2,"Arbejas", 652.5f, PorcentajeGanancias.Envasados(),true,"Lata Metalica 300g",false,LocalDate.of(2024,12,12)));
    		listaEnvasados.add(new Envasados(3,"Leche", 1652.5f, PorcentajeGanancias.Envasados(),true,"Envase Plástico",false,LocalDate.of(2025,2,2)));
    		listaEnvasados.add(new Envasados(4,"Fideos", 352.5f, PorcentajeGanancias.Envasados(),true,"Envoltorio Plástico",false,LocalDate.of(2026,3,6)));
    		listaEnvasados.add(new Envasados(5,"Sopa Ramen", 1352.5f, PorcentajeGanancias.Envasados(),true,"Envoltorio Plástico",true,LocalDate.of(2028,3,6)));
    	}catch(UserRepeatedIDExceptionError e) {System.out.println("ID Repetidos - Envasados");}


    	/**
    	 * Instacio objeto TIENDA y compra productos.
    	 */
    	Tienda tiendaDeApu = new Tienda("Kwik-E-Mart", 20000, 12);

    	System.out.println(tiendaDeApu.getSaldoCaja());
    	System.out.println(tiendaDeApu.comprar(listaEnvasados.get(0), 2));
    	System.out.println(tiendaDeApu.comprar(listaBebidas.get(1), 2));
    	System.out.println(tiendaDeApu.comprar(listaProductosLimpieza.get(1), 2));
    	System.out.println(tiendaDeApu.comprar(listaProductosLimpieza.get(2), 2));

    	System.out.println("numero de prod: "+tiendaDeApu.getStockProductos().size());

    	/**
    	 * Prueba de venta de productos. Genera lista de pedidos para vender y sus cantidades.
    	*/
    	List<Producto> paraVender = new ArrayList<>();
    	List<Integer> cantidadesParaVender = new ArrayList<>();

    	paraVender.add(listaEnvasados.get(0));			cantidadesParaVender.add(1);
    	paraVender.add(listaBebidas.get(1));			cantidadesParaVender.add(1);
    	paraVender.add(listaProductosLimpieza.get(1));	cantidadesParaVender.add(1);
    	paraVender.add(listaProductosLimpieza.get(2));	cantidadesParaVender.add(1);

    	System.out.println("SALDO: "+tiendaDeApu.getSaldoCaja());
    	System.out.println(tiendaDeApu.vender(paraVender, cantidadesParaVender));
    	System.out.println("SALDO: "+tiendaDeApu.getSaldoCaja());

    }
}





