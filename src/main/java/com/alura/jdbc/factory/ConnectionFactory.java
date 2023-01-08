package com.alura.jdbc.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	
	private DataSource datasourse;
	
	public ConnectionFactory() {
		var pooledDataSourse = new ComboPooledDataSource();
		//enves  de conectarnos de foma directa como lo haciamos con recuperaConexion() lo aremos on la pool
		pooledDataSourse.setJdbcUrl("jdbc:mysql://localhost/control_de_stock?useTimeZone=true&serverTimeZone=UTC");
		pooledDataSourse.setUser("root");
		pooledDataSourse.setPassword("Tigres132");
		//cantidad de conexiones maximas permitidas
		pooledDataSourse.setMaxPoolSize(10);
		this.datasourse=pooledDataSourse;
	}
	public Connection recuperaConexion(){
		/*return DriverManager.getConnection(
				"jdbc:mysql://localhost/control_de_stock?useTimeZone=true&serverTimeZone=UTC",
				"root", 
				"Tigres132");*/
		try {			
			return this.datasourse.getConnection();
		}catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
}
