package urjc.isi.pruebasSparkJava.injector;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import urjc.isi.pruebasSparkJava.Injector;

public class FilterByRatingTest {

	// Test fixture
	Injector connector = new Injector("JDBC_DATABASE_URL");
	private String rating;
	
	
	// Test 1: A1 = T => hay películas con valoración mayor en la BD.
	// Lo comprobamos con "5"
	// Recorre el camino de prueba i = [1, 2, 4, 5, 6, 7, 8, 6, 7, 8, 6, 9]
	@Test
	public void test1() {
		List<String> expected = new ArrayList<String>();
		expected.add("Venom");								// movie 1
		//películas que correspondan
		
		rating = "5";
    	List<String> movies = connector.filterByRating(rating);
    	
		assertEquals(expected, movies);
	}
	
	
	// Test 2: A2 = F => no hay películas con una valoración mayor en la BD.
	// Lo comprobamos con "10.1"
	// Recorre el camino de prueba ii = [1, 2, 4, 5, 6, 9] 
	@Test
	public void test2() {
		rating = "10.1";
		List<String> movies = connector.filterByRating(rating);
		
		assertTrue(movies.isEmpty());
	}
}
