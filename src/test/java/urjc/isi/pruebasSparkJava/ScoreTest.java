package urjc.isi.pruebasSparkJava;

//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import org.junit.*;


class ScoreTest {
	
	Score S = new Score();
	
	//Comprobamos si hemos añadido la nueva puntuacion correctamente
	@Test
	void testNewScore() {
		assertEquals("Puntuacion añadida",S.newScore(1,1,"Titanic"));
		//fail("Not yet implemented");
	}

	//Comprobamos si devolvemos la media correctamente
	@Test
	void testGetScor() {
		assertEquals(4,S.getScore("Titanic"));
		//fail("Not yet implemented");
	}
	

	//Comprueba si se ha cambiado la puntuacion de la pelicula correctamente con una nueva puntuacion permitida.
	//Necesitamos la funcion en Score.java para completar el else ultimo
	//@Test
	//void testChangeScore() {
		
		//fail("Not yet implemented");
	//}

}
