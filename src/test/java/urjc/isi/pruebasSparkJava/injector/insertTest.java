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

public class MainTest {
	
	Injector connection = new Injector("JDBC_DATABASE_URL");


	//Test1: insertFilm
	@Test 
	public void testInsertFullFieldsMovie()
	{
		connection.insertFilm("Kill Bill: Volumen 3", "2020", "Accion");
	}

	//Test2: insertFilm
	@Test 
	public void testInsertNullGenre()
	{
		connection.insertFilm("Interstellar 2", "2022", null);
	}

	//Test3: insertFilm
	@Test (expected = NullPointerException.class)
	public void testInsertNullMovie()
	{
		connection.insertFilm(null, "2019", null);
	}

	//Test4: insertFilm
	@Test (expected = NullPointerException.class)
	public void testInsertNullMovie&Year()
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
