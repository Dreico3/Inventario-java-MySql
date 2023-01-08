package com.alura.jdbc.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.alura.jdbc.modelo.Categoria;
import com.alura.jdbc.modelo.Producto;

public class CategoriaDAO {
	private Connection con;
	public CategoriaDAO(Connection con) {
		this.con=con;
	}

	public List<Categoria> listar() {
		// TODO Auto-generated method stub
		List<Categoria> resultado = new ArrayList<>();
		try {
			System.out.println("select * from categoria locaso");
			final PreparedStatement statement= con.prepareStatement("select * from categoria");
			try(statement){
				final ResultSet resultSet = statement.executeQuery();//esta nueva funcion trae la respuesta sin tener que unsar el stateement.excute()	
				try(resultSet){
					while(resultSet.next()) {
						resultado.add(new Categoria(resultSet.getInt("id"),resultSet.getString("nombre")));
					}
				}
			}
			return resultado;
			
		}catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
		
	}

	public List<Categoria> listarConProductos() {
		// TODO Auto-generated method stub
		List<Categoria> resultado = new ArrayList<>();
		try {
			System.out.println("select * from categoria");
			final PreparedStatement statement= con.prepareStatement("select c.id, c.nombre, p.id,p.nombre,p.cantidad from categoria c inner join productos p on c.id = p.categoria_id");
			try(statement){
				final ResultSet resultSet = statement.executeQuery();//esta nueva funcion trae la respuesta sin tener que unsar el stateement.excute()	
				try(resultSet){
					while(resultSet.next()) {
						
						int idCategoria = resultSet.getInt("c.id");
						String nombreCategoria = resultSet.getString("c.nombre");
						var categoria = resultado
								.stream()
								.filter(cat-> cat.getId().equals(idCategoria))
								.findAny().orElseGet(()->{
									Categoria cat = new Categoria(idCategoria, nombreCategoria);
									resultado.add(cat);
									return cat;
								});
						//resultado.add(new Categoria(idCategoria,nombreCategoria));
						Producto producto =new Producto(resultSet.getInt("p.id"),resultSet.getString("p.nombre"),resultSet.getInt("p.cantidad"));
						categoria.agregar(producto);
					}
				}
			}
			return resultado;
			
		}catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
}
