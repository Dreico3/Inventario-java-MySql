package com.alura.jdbc.modelo;

public class Producto {
	private Integer id;
	private String nombre;
	private String descripcion;
	private Integer cantidad;
	private Integer id_categoria;

	public Producto(String nombre, String descripcion, Integer cantidad, Integer id_categoria) {
		// TODO Auto-generated constructor stub
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
		this.id_categoria =id_categoria;
	}

	public Producto(int id, String nombre, String descripcion, int cantidad) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
	}

	public Producto(int id, String nombre, int cantidad) {
		// TODO Auto-generated constructor stub
		this.nombre=nombre;
		this.id=id;
		this.cantidad=cantidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		// TODO Auto-generated method stub
		return this.id;
	}
	
	public Integer getId_categoria() {
		return this.id_categoria;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("{ID):%d, nombre:%s, Descripcion:%s, cAntidad:%d}", this.id, this.nombre, this.descripcion,
				this.cantidad);
	}
}
