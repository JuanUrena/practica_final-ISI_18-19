package urjc.isi.pruebasSparkJava.injector;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import urjc.isi.pruebasSparkJava.Injector;

public class FilterByGenreTest {

	// Test fixture
	Injector connector = new Injector("JDBC_DATABASE_URL");
	private String genre;
	
	
	// Test 1: A1 = T => hay películas con ese género en la BD.
	// Lo comprobamos con "Acción"
	// Recorre el camino de prueba i = [1, 2, 4, 5, 6, 7, 8, 6, 7, 8, 6, 9]
	@Test
	public void test1() {
		List<String> expected = new ArrayList<String>();
		expected.add("Venom");								// movie 1
		//películas con ese género
		
		genre = "Acción";
    	List<String> movies = connector.filterByGenre(genre);
    	
		assertEquals(expected, movies);
	}
	
	
	// Test 2: A2 = F => no hay películas con ese género en la BD.
	// Lo comprobamos con "Futbol"
	// Recorre el camino de prueba ii = [1, 2, 4, 5, 6, 9] 
	@Test
	public void test2() {
		genre = "Futbol";
		List<String> movies = connector.filterByGenre(genre);
		
		assertTrue(movies.isEmpty());
	}
}
