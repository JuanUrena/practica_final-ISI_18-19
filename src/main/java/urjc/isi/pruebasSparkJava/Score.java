package urjc.isi.pruebasSparkJava;

import java.net.URISyntaxException;

import spark.Request;
import spark.Response;

public class Score {
	//Puntuaciones
	
		//Guardo la nueva puntuacion
		public static void newScore(int score, int user, String film) {
			//Obtener el id de la pelicula
			//Llamar a la funcion para añadir. 
			System.out.println("añadida");
		}
		
		//Obtengo la nueva media 
		public static int getScor (String name) {
			
			//Llamara a una funcion que me devuelva todas las puntuaciones 
			//referentes al nombre que me pasan, de ese vector saco la media
			int scores []= {1,3,4,1,5,2};
			
			int media=0;
			for (int i=0; i < scores.length; i++) {
				 media = media + scores[i];
			}
			media = media / scores.length;
			return media;
		}
		
		//Actualizo la media
		public static void changeScore(int score, String name) {
			//Llamar a la función para cambiar la puntuacion de la pelicula, pedir. 
		}
		
		public static String postScore(Request request) throws ClassNotFoundException, URISyntaxException {
			System.out.println("Puntuando");
			String result = new String("Puntuacion");			
			String score_string=request.queryParams("score");
			int score=Integer.parseInt(score_string);
			String user_string=request.queryParams("ID");
			System.out.println(user_string);
			int user=Integer.parseInt(user_string);
			String film=request.queryParams("film");
			newScore(score, user, film);
			score=getScor(film);
			changeScore(score, film);
			return result;
		}
	
		public static String getScore (Request request) {
			String resp="<form action='/comment' method='post'> Comment:<br> <input type='text' name='comment'> User: <input type='number' name='ID' required/><br> <input type='submit' value='Comentar'> </form> <form action='/score' method='post'> Score(1-5):<br> <input type='number' name='score' min='1' max='5'> User: <input type='number' name='ID' required/><br> <input type='submit' value='Puntuar'> </form>";
			return resp;
		}
}
