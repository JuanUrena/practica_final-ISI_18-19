package urjc.isi.pruebasSparkJava;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ScoreTest {

	//Puntuacion invalida si se quiere guardar una puntuación fuera del rango 1-10
	@Test
	public void newScore_test1() {
		Score score1 = new Score();
		assertEquals("Puntuacion invalida",score1.newScore(-1,1,"Titanic"));
		//fail("Not yet implemented");
	}
	
	//Todo usuario tiene que tener un ID positivo
	@Test
	public void newScore_test2() {
		Score score2 = new Score();
		assertEquals("Usuario invalido",score2.newScore(1,-1,"Titanic"));
	}
	
	//Puntuacion referente a una pelicula invalida o vacia
	@Test
	public void newScore_test3() {
		Score score3 = new Score();
		assertEquals("Pelicula invalida",score3.newScore(1,1,""));
	}
	
	//Puntuacion añadida correctamente a la pelicula con puntuacion e ID de usuario correctos
	@Test
	public void newScore_test4() {
		Score score4 = new Score();
		assertEquals("Puntuacion añadida",score4.newScore(1,1,"Titanic"));
	}
	
	//Conseguir la media de puntuacion añadiendo una puntuacion errónea
	@Test
	public void getScore_test1() {
		Score score5 = new Score();
		assertEquals("Pelicula invalida",score5.getScor(""));
		
	}
	
	//Actualizacion de la media de una pelicula errónea
	@Test
	public void getScore_test2() {
		Score score6 = new Score();
		assertEquals("4",score6.getScor("4"));	
	}
	
	//Puntuacion invalida al querer cambiar la puntuacion de una pelicula
	@Test
	public void changeScore_test1() {
		Score score7 = new Score();
		//assertEquals("Puntuacion invalida",score7.changeScore(-1,"Titanic"));
	}
	
	//Puntuacion invalida al querer cambiar la puntuacion de una pelicula erronea
		@Test
		public void changeScore_test2() {
			Score score8 = new Score();
			//assertEquals("Pelicula invalida",score8.changeScore(-1,"Titanic"));
		}
	
	//Necesitamos la funcion del grupo de Alvaro para tener la nueva puntuacion
	//@Test
	//public void changeScore_test1() {
		//Score score8 = new Score();	
	//}
	
	
	
	
}
