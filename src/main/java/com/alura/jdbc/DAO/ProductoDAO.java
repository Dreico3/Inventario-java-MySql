package com.alura.jdbc.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.alura.jdbc.factory.ConnectionFactory;
import com.alura.jdbc.modelo.Producto;

public class ProductoDAO {
	final private Connection con;
	
	public ProductoDAO(Connection con) {
		this.con= con;
	}
	
	public void modificar(String nombre, String descripcion, Integer id, Integer cantidad) {
	
		try{
			final PreparedStatement statement = con
					.prepareStatement("update productos set nombre=?, descripcion=?, cantidad=? where id = ?");
			// statement.execute(String.format("update productos set
			// nombre='%s',descripcion='%s', cantidad='%d' where ID=%d",nombre, descripcion,
			// cantidad, id));
			try (statement) {

				statement.setString(1, nombre);
				statement.setString(2, descripcion);
				statement.setInt(3, cantidad);
				statement.setInt(4, id);
				statement.execute();
				System.out.println("numero de productos modificados " + statement.getUpdateCount());

			}
		}catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	
	public int eliminar(Integer id) {
		// TODO Auto-generated method stub
		try{
			final PreparedStatement statement = con.prepareStatement("delete from productos where ID = ?");
			// statement.execute(String.format("delete from productos where ID = %d", id));
			try (statement) {
				statement.setInt(1, id);
				statement.execute();
				return statement.getUpdateCount();// debuelve eel numero de filas modificadas
			}
		}catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	
	public List<Producto> listar() {

		try{
			final Statement statement = con.createStatement();
			try (statement) {
				boolean result = statement.execute("select * from productos");
				ResultSet resultset = statement.getResultSet();
				List<Producto> resultado = new ArrayList<>();
				while (resultset.next()) {
					Producto fila = new Producto(resultset.getInt("ID"), resultset.getString("nombre"),
							resultset.getString("descripcion"), resultset.getInt("CANTIdad"));
					resultado.add(fila);
				}
				return resultado;
			}
		}catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	public void guardar(Producto producto){
		// TODO Auto-generated method stub
		try{
			
			final PreparedStatement statement = con.prepareStatement(
					"insert into productos(nombre,descripcion,cantidad,categoria_id) values(?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			try (statement) {
				
				asignacionVariablesExtraible(producto, statement);//NOTA:esta funcion solo fue para hacer tests
				
				System.out.println("haciendo el commit");
				
				
			} 	
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("aqui esta el roback");
		}		
	}
	
	private void asignacionVariablesExtraible(Producto producto ,PreparedStatement statement) throws SQLException {
		// if(cantidad<50) throw new RuntimeException(); SIMULACION DE ERROR EN MEDIO DE
		// UNA TRANSACCION
		statement.setString(1, producto.getNombre()); // ?1
		statement.setString(2, producto.getDescripcion());// ?2
		statement.setInt(3, producto.getCantidad()); // ?3
		statement.setInt(4, producto.getId_categoria());
		statement.execute();
		// es donde see guardan las respuestas sql que se icieron con el statement
		//resiviendo el resultado dela consulta
		final ResultSet resultset=statement.getGeneratedKeys();
		try(resultset){
			while (resultset.next()) {
				producto.setId(resultset.getInt(1));//getInt(en la posicion 1 tine la id que fue creada);
				System.out.println(producto);
			}
		}
	}

	public List<Producto> listar(Integer idCategoria) {
		try{
			
			System.out.println("select * from productos where categoria_id = ?");
			
			final PreparedStatement statement = con.prepareStatement("select * from productos where categoria_id = ?");
			try (statement) {
				statement.setInt(1, idCategoria);
				ResultSet resultset = statement.executeQuery();
				List<Producto> resultado = new ArrayList<>();
				while (resultset.next()) {
					Producto fila = new Producto(resultset.getInt("ID"), resultset.getString("nombre"),
							resultset.getString("descripcion"), resultset.getInt("CANTIdad"));
					resultado.add(fila);
				}
				return resultado;
			}
		}catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		} 
	}
}
