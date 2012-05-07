package com.preguicoso.server.dbgenerator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public static Connection createConnection() throws ClassNotFoundException,
			SQLException {
		Class.forName("org.postgresql.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:postgresql://localhost/cep", "postgres", "student");

		return conn;
	}

}
