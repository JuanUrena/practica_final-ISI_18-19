package urjc.isi.pruebasSparkJava;

import java.net.URISyntaxException;
import java.util.List;

import spark.Request;

public class Score {
	
	
		//Guardo la nueva puntuacion    COMPLETO!!!
		public String newScore(int score, int user, String film, Injector I) {
			if (score<0 || score>10) {
				throw new IllegalArgumentException("Puntuacion invalida");
			}else if (user<0) {
				throw new IllegalArgumentException("Usuario invalido");
			}else if (film.equals(null)) {
				throw new IllegalArgumentException("Pelicula invalida");
			}else {
				I.insertUser(user);
				List<String> info_film=I.filterByName(film);
				int id_film=Integer.parseInt(info_film.get(6));
				I.insertRating(id_film, user, score);
			}			
			return ("Puntuacion añadida");
		}
		
		//Obtengo la nueva media    COMPLETO!!!
		public int getScore(String film, Injector I) {
			if (film.equals(null)) {
				throw new IllegalArgumentException("Pelicula invalida");
			}else {
				int media =I.meanScores(film);
				return media;
			}
		}
		
		//Actualizo la media   INCOMPLETO, FALTA LA FUNCION DE BBDD
		public void changeScore(int score, String film, Injector I) {
			if (score<0 || score>10) {
				throw new IllegalArgumentException("Puntuacion invalida");
			}else if (film.equals(null)) {
				throw new IllegalArgumentException("Pelicula invalida");
			}else {
			//Llamar a la función para cambiar la puntuacion de la pelicula, pedir. 
			//Parace no estar hecha
			}
		}
		
		
		
		public String postScore(Request request, Injector I) {
			
			//Saco la puntuacion a int
			String score_string=request.queryParams("score");
			int score=Integer.parseInt(score_string);
			//Saco el usuario a int
			String user_string=request.queryParams("user");
			int user=Integer.parseInt(user_string);
			//Saco la pelicula.
			String film=request.queryParams("film");
			
			try {
				String result=newScore(score, user, film, I);
				score=getScore(film, I);
				changeScore(score, film, I);
				return result;
			}catch(IllegalArgumentException e) {
				return e.getMessage();
			}
		}
}
