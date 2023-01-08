package com.alura.jdbc.modelo;

import java.lang.annotation.Retention;
import java.util.ArrayList;
import java.util.List;

public class Categoria {
	
	Integer id;
	String nombre;
	
	private List<Producto> productos;
	
	public Categoria(Integer id,String nombre) {
		this.id=id;
		this.nombre=nombre;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void seNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.nombre;
	}

	public void agregar(Producto producto) {
		if(this.productos==null) {
			this.productos = new ArrayList<>();
		}
		this.productos.add(producto);
		
	}

	public List<Producto> getProductos() {
		// TODO Auto-generated method stub
		return this.productos;
	}

}
