package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Sql {

	protected Connection connect = null;
	protected Statement statement = null;
	protected PreparedStatement preparedStatement = null;
	protected ResultSet resultSet = null;

	// for connection with database
	// info of database => port , database name , user name , password
	public Sql() {
	};

	public Sql(String port, String databaseName, String userName, String password) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/" + databaseName, userName, password);
			statement = connect.createStatement();
		} catch (Exception ex) {
			ex.getStackTrace();
		}
	}

	// Loading message
	public static void LoadingMessage() {
		System.out.println("Loading...");
	}

}
