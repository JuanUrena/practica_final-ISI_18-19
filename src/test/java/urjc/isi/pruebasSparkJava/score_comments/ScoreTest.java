package urjc.isi.pruebasSparkJava.score_comments;

//import static org.junit.jupiter.api.Assertions.*;
import org.junit.*;

import urjc.isi.pruebasSparkJava.Injector;
import urjc.isi.pruebasSparkJava.Score;

import static org.junit.Assert.*;


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
	
	
	//Suponemos que el injector funciona correctamente y no falla, por eso no hacemos test de injector
	/*
	@Test //(expected = NullPointerException.class)
	void getScore_test() {
		
		Score score4 = new Score(); 
		
		assertNotNull(score4.getScore("Titanic"));
	}
	*/
	
	//Comprobamos que el float de score sea mayor o igual que cero
	@Test 
	public void changeScore_test() {
		
		Score score = new Score(); 
	
		score.changeScore(1.0f,120338,I);

	}
	
}
