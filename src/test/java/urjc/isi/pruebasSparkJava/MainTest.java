package urjc.isi.pruebasSparkJava;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;

public class MainTest {
	
	private String s;
	@Before //Set up - called before every test method.
	public void setUp()
	{
		connection = null;
    		try {
      			connection = DriverManager.getConnection("jdbc:sqlite:Database/IMDb.db");
      			Bbdd.eraseBBDD(connection); // Prepare SQL to create table
    		} catch(SQLException e {
	      		System.err.println(e.getMessage());
	    		}
		}

	}

	@After // Tear Down - Called after every test method.
	public void tearDown()
	{
		try{
			if(connection != null){
        		connection.close();
      			}
    		} catch(SQLException e) {
        		System.err.println(e);
   	 	}
	}

	@Test //(expected = NullPointerException.class)
	public void testInsertNullMovie()
	{
		Main.insertFilm(connection, null, "2019", null);
	}

	@Test (expected=NullPointerException.class)
	public void testInsertNullActor() throws SQLException {
		Main.insertActor(connection, null);
	}

	
	@Test (expected=NullPointerException.class)
  	public void testInsertNullWorks() throws 	SQLException {
    	Main.insertWorks_In(connection, null, null);
	}	
}
