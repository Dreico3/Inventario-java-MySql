package com.alura.jdbc.pruebas;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.alura.jdbc.factory.ConnectionFactory;

public class PruevasDelete {
	public static void main(String[] args) throws SQLException{
		Connection con = new ConnectionFactory().recuperaConexion();
		Statement statement = con.createStatement();
		statement.execute("delete from productos where ID = 453");
		System.out.println("elementos borrrados "+statement.getUpdateCount());
	}
}
