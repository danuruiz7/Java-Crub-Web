package com.tracom.conexion;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;


public class Conexion {
	
	private static BasicDataSource ds = null;
	
	private static DataSource getDataSource() {
		if (ds == null) {
			ds = new BasicDataSource();
			ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
			ds.setUsername("root");
			ds.setPassword("");
			ds.setUrl("jdbc:mysql://localhost:3306/crud");
			// Definimos el tamaño del pool de conexiones
			ds.setInitialSize(50);
			ds.setMaxIdle(10);
			ds.setMaxTotal(20);
			ds.setMaxWaitMillis(5000);
		}
		return ds;
	}
	
	public static Connection getConexion() throws SQLException {
		return getDataSource().getConnection();
	}

}
