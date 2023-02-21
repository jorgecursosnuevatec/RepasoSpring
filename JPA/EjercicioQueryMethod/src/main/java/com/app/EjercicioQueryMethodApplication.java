package com.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.app.entity.Producto;
import com.app.repository.ProductoRepository;

@SpringBootApplication
public class EjercicioQueryMethodApplication implements CommandLineRunner {

	@Autowired
	private ProductoRepository productoRepository;

	public static void main(String[] args) {
		SpringApplication.run(EjercicioQueryMethodApplication.class, args);
	}

	public void run(String... args) throws Exception {

		cargaDeDatos();

		// Obtengo todos los productos
		//productoRepository.getAllProducts().forEach(System.out::println);

		// Obtengo solo los productos de un rango de precios
		/*List<Producto> precios = productoRepository.getPrecioRango(30, 70);
		for(Producto precioProducto : precios) {
			System.out.println("CODIGO PRODUCTO: " + precioProducto.getPrecio());
		}*/
		
		// Obtengo solo los productos que se encuentren activos
		/*List<Producto> activos = productoRepository.getAllProductosActivos(true);
		for(Producto productoActivo : activos) {
			System.out.println("CODIGO PRODUCTO: " + productoActivo.getCodigo() + " - " + productoActivo.getActivo());
		}*/
		
		// Obtengo solo los productos que correspondan con los datos posicionales pasados
		/*List<Producto> posicionales = productoRepository.getDatosPosicionales(2, 50);
		for(Producto productoPosicional : posicionales) {
			System.out.println("CODIGO PRODUCTO: " + productoPosicional.getCodigo() + " - ID: " + productoPosicional.getId() + " - PRECIO: " + productoPosicional.getPrecio());
		}*/
		
		// Obtengo solo los productos que correspondan con los datos posicionales pasados y ademas estan ordenados
		/*List<Producto> posicionalesOrdenados = productoRepository.getDatosOrdenados();
		for(Producto ordenados : posicionalesOrdenados) {
			System.out.println("CODIGO PRODUCTO: " + ordenados.getCodigo() + " - ID: " + ordenados.getId() + " - PRECIO: " + ordenados.getPrecio());
		}*/
		
		// Obtengo los productos que correspondan con un vendedor
		/*List<Producto> productosVendedor = productoRepository.getProductosVendedor("Ikea");
			for(Producto vendedor : productosVendedor) {
			System.out.println("CODIGO PRODUCTO: " + vendedor.getCodigo() + " - ID: " + vendedor.getId() + " - PRECIO: " + vendedor.getPrecio());
		}*/
		
	}
	
	private void cargaDeDatos() {
		productoRepository.save(new Producto(1, "AA0000", "Ikea", 20, true));
		productoRepository.save(new Producto(2, "AA0001", "Fnac", 30, true));
		productoRepository.save(new Producto(3, "AA0002", "Media Markt", 40, false));
		productoRepository.save(new Producto(4, "AA0003", "Ikea", 50, true));
		productoRepository.save(new Producto(5, "AA0004", "El corte ingles", 60, true));
		productoRepository.save(new Producto(6, "AA0005", "Ikea", 70, false));
		productoRepository.save(new Producto(7, "AA0006", "Fnac", 80, true));
		productoRepository.save(new Producto(8, "AA0007", "Media Markt", 90, false));
		productoRepository.save(new Producto(9, "AA0008", "Fnac", 100, true));
		productoRepository.save(new Producto(10, "AA0009", "Primark", 110, false));
	}

}
