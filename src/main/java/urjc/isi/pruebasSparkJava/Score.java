package urjc.isi.pruebasSparkJava;

import java.net.URISyntaxException;

import spark.Request;
import spark.Response;

public class Score {
	
	
		//Guardo la nueva puntuacion
		public String newScore(int score, int user, String film) {
			if (score<0 || score>10) {
				throw new IllegalArgumentException("Puntuacion invalida");
			}else if (user<0) {
				throw new IllegalArgumentException("Usuario invalido");
			}else if (film.equals(null)) {
				throw new IllegalArgumentException("Pelicula invalida");
			}else {
				Injector connector = new Injector("JDBC_DATABASE_URL");
				connector.insertUser(user);
				//int filmID=connector.filterByName(film)
				//Problema, no tengo manera de sacar el idfilm
				connector.insertRating(2, user, score);
				
			return ("Puntuacion añadida");
			}
		}
		
		//Obtengo la nueva media 
		public int getScore(String film) {
			if (film.equals(null)) {
				throw new IllegalArgumentException("Pelicula invalida");
			}else {
				Injector connector = new Injector("JDBC_DATABASE_URL");
				int media =connector.meanScores(film);
				return media;
			}
		}
		
		//Actualizo la media
		public void changeScore(int score, String film) {
			if (score<0 || score>10) {
				throw new IllegalArgumentException("Puntuacion invalida");
			}else if (film.equals(null)) {
				throw new IllegalArgumentException("Pelicula invalida");
			}else {
				Injector connector = new Injector("JDBC_DATABASE_URL");
			//Llamar a la función para cambiar la puntuacion de la pelicula, pedir. 
			//Parace no estar hecha
			}
		}
		
		public String postScore(Request request) throws ClassNotFoundException, URISyntaxException {
			
			//Saco la puntuacion a int
			String score_string=request.queryParams("score");
			int score=Integer.parseInt(score_string);
			//Saco el usuario a int
			String user_string=request.queryParams("user");
			int user=Integer.parseInt(user_string);
			//Saco la pelicula.
			String film=request.queryParams("film");
			
			try {
				String result=newScore(score, user, film);
				score=getScore(film);
				changeScore(score, film);
				return result;
			}catch(IllegalArgumentException e) {
				return e.getMessage();
			}
		}
}
