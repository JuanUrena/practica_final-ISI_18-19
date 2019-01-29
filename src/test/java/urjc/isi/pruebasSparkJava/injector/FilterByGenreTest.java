package urjc.isi.pruebasSparkJava.injector;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import urjc.isi.pruebasSparkJava.Injector;

public class FilterByGenreTest {

	// Test fixture
	Injector connector = new Injector("JDBC_DATABASE_URL");
	private String genre;
	
	// Test 2: A2 = F => no hay películas con ese género en la BD.
	// Lo comprobamos con "Teclado"
	// Recorre el camino de prueba ii = [1, 2, 4, 5, 6, 9] 
	@Test
	public void test2() {
		genre = "Teclado";
		List<String> movies = connector.filterByGenre(genre);
		
		assertTrue(movies.isEmpty());
	}
	//como la lista de géneros no está definida, las películas que vayan
	//añadiendo irán aumentando la lista por lo que no puedo comprobar 
	//las películas de cada género
}
