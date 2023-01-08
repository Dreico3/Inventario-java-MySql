package com.alura.jdbc.pruebas;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;

import com.alura.jdbc.factory.ConnectionFactory;

public class PruevaPoolConexiones {
	public static void main(String[] args) throws SQLException {
		/*hacemos estamos haciendo la prueva de 10 connections simultaneas*/
		ConnectionFactory conexionfrabrica = new ConnectionFactory();
		for (int i = 0; i<20;i++) {
			Connection conexion = conexionfrabrica.recuperaConexion();
			System.out.println("habriendo la conexion numero "+(i+1));
		}
	}
}
