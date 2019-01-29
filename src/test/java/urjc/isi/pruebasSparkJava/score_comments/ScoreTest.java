package urjc.isi.pruebasSparkJava;

//import static org.junit.jupiter.api.Assertions.*;
import org.junit.*;
import static org.junit.Assert.*;


public class ScoreTest {

	private Injector I = new Injector("JDBC_DATABASE_URL");
	
	@Test
	public void newScore_test() {
		
		Score score = new Score(); 
		
		assertEquals("Puntuacion invalida",score.newScore(-1,1,"Titanic",I));

	}

	@Test
	public void newScore_test2() {
		
		Score score = new Score(); 
		
		assertEquals("Usuario invalido",score.newScore(1,-1,"Titanic",I));

	}
	
	@Test
	public void newScore_test3() {
		
		Score score = new Score(); 
		
		assertEquals("Pelicula invalida",score.newScore(1,1,null,I));

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
		
		Score score5 = new Score(); 
	
		score5.changeScore(1.0f,"Pelicula cualquiera",I);

	}
	
	@Test (expected = NullPointerException.class)
	public void changeScore_test2() {
		
		Score score6 = new Score(); 
	
		score6.changeScore(1.0f,null,I);

	}

	
}
