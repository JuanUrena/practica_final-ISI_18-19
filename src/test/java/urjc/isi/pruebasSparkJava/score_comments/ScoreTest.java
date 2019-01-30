package urjc.isi.pruebasSparkJava.score_comments;

//import static org.junit.jupiter.api.Assertions.*;
import org.junit.*;

import urjc.isi.pruebasSparkJava.Injector;
import urjc.isi.pruebasSparkJava.Score;

import static org.junit.Assert.*;

import java.util.List;


public class ScoreTest {

	Injector I = new Injector("JDBC_DATABASE_URL");
	
	@Test
	public void newScore_test() {
		
		Score score = new Score(); 
		
		assertEquals("Puntuacion invalida",score.newScore(-1,1,120338,I));

	}

	@Test
	public void newScore_test2() {
		
		Score score = new Score(); 
		
		assertEquals("Usuario invalido",score.newScore(1,-1,120338,I));

	}
	
	@Test
	public void newScore_test3() {
		
		Score score = new Score(); 
		
		assertEquals("Pelicula invalida",score.newScore(1,1,-1,I));

	}

	@Test
	public void newScore_test4() {
		
		Score score = new Score(); 
		
		assertEquals("Pelicula invalida",score.newScore(1,1,1,I));

	}
	
	
	//Suponemos que el injector funciona correctamente y no falla, por eso no hacemos test de injector

	@Test //(expected = NullPointerException.class)
	public void getMeanScore_test() {
		
		Score score = new Score(); 
		
		assertNotNull(score.getMeanScore(120338,I));
	}
	
	//Comprobamos que el float de score sea mayor o igual que cero
	@Test 
	public void changeScore_test() {
		
		Score score = new Score(); 
		List<String> movieFields = I.filterByName("Titanic");
		
		//Media real
		float mean = Float.parseFloat(movieFields.get(3));
		
		//Pongo la media a uno y compruebo el cambio.
		score.changeScore(1,120338,I);
		movieFields = I.filterByName("Titanic");
		int mean_test=Integer.parseInt(movieFields.get(3));
		
		//Vuelvo a poner media real
		score.changeScore(mean,120338,I);
		
		assertEquals(1,mean_test);

	}
	
}
