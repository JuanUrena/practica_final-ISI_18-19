package urjc.isi.pruebasSparkJava;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.*;
import java.util.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class insertTest {
	
	Injector connection = new Injector("JDBC_DATABASE_URL");

	//Test1: insertFilm
	@Test 
	public void testInsertFullFieldsMovie()
	{
		Boolean name = False;	
		Boolean year = False;	
		Boolean resultado = False;

		connection.insertFilm("Kill Bill: Volumen 3", "2020", "Accion");
		String sql = "SELECT title FROM movies WHERE title = Kill Bill: Volumen 3";
		try (PreparedStatement pstmt = c.prepareStatement(sql)) {
			ResultSet rs= pstmt.executeQuery();
			rs.next();
			rs.getInt("title");
			name = True;
		}catch (SQLException e) {
			name = False;
		}

		String sql = "SELECT year FROM movies WHERE year = 2020";
		try (PreparedStatement pstmt = c.prepareStatement(sql)) {
			ResultSet rs= pstmt.executeQuery();
			rs.next();
			rs.getInt("year");
			year = True;
		}catch (SQLException e) {
			year = False;
		}
		resultado = name & year;
		assertTrue("No se ha añadido a la base de datos", resultado);
	}

	//Test3: insertFilm
	@Test (expected = NullPointerException.class)
	public void testInsertNullMovie()
	{
		connection.insertFilm(null, "2019", null);
	}

	//Test4: insertFilm
	@Test (expected = NullPointerException.class)
	public void testInsertNullMovieandYear()
	{
		connection.insertFilm(null, null, null);
	}

	//Test1: insertActor
	@Test (expected = NullPointerException.class)
	public void testInsertNullActor() throws SQLException
	{
		connection.insertActor(null);
	}

	//Test1: insertWorks_In
	//@Test (expected = NullPointerException.class)
  	//public void testInsertNullWorks() throws SQLException
	//{
    //		Main.insertWorks_In(connection, null, null);
	//}	
}
