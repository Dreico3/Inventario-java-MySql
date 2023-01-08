package com.alura.jdbc.controller;

import java.awt.Taskbar.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alura.jdbc.DAO.ProductoDAO;
import com.alura.jdbc.factory.ConnectionFactory;
import com.alura.jdbc.modelo.Categoria;
import com.alura.jdbc.modelo.Producto;

public class ProductoController {
	private ProductoDAO productoDAO;

	public ProductoController() {
		ConnectionFactory factory =new ConnectionFactory();
		productoDAO = new ProductoDAO(factory.recuperaConexion());
	}

	public void modificar(String nombre, String descripcion, Integer id, Integer cantidad) {
		// TODO
		productoDAO.modificar(nombre, descripcion, id, cantidad);
	}

	public int eliminar(Integer id) {
		// TODO
		return productoDAO.eliminar(id);
	}

	public List<Producto> listar() {
		// TODO
		return productoDAO.listar();
	}

	public void guardar(Producto producto) {
		// TODO

		// SEGUDNDA (recomendada)
		productoDAO.guardar(producto);

	}
	public List<Producto> listar(Categoria categoria){
		return productoDAO.listar(categoria.getId());
	}

	private void guardarANTIGUITA(Producto producto) {
		final Connection con = new ConnectionFactory().recuperaConexion();
		try (con) {

			con.setAutoCommit(false);// sirve para q todas las transacciones o cambios en la base de datos no sean
										// automaticas

			/* hay dos formas de inyectar un script SQL */
			// PRIMERA
			Statement statement = con.createStatement();
			statement.execute(
					String.format("insert into productos(nombre,descripcion,cantidad) values('%s','%s',%s)",
							producto.getNombre(), producto.getDescripcion(), producto.getCantidad()),
					Statement.RETURN_GENERATED_KEYS);
			/*
			 * Generalmete cunado statement.execute es ejecutado te debuelve un boolean true
			 * si tiene en su interor una lista false si no contiene nada perro como no nos
			 * dice nada q retorne un false en la creacion de productos podemos hacer que
			 * nos retorne los ID creados en un ResultSet. colocando al final esto
			 * Statement.RETURN_GENERATED_KEYS ejem
			 * statement.execute(consultaSQL,Statement.RETURN_GENERATED_KEYS);
			 */
			System.out.println("aquio hacemo un commirt a la base  dedatos");
			con.commit();// con esta linea reien se ara el ccommit
			final ResultSet resultset = statement.getGeneratedKeys();// con esta linea recuperamos el ResultSet

			try (resultset) {
				while (resultset.next()) {
					System.out.println(producto);
				}
				producto.setId(resultset.getInt(1));
			}

		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

}
