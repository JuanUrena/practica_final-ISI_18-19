package urjc.isi.pruebasSparkJava;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author nshandra, jaimefdez96, AlbertoCoding
 * <p>
 * data: userID | titleID | score<br>
 * diffMap: titleID-A | titleID-B | difference<br>
 * weightMap: titleID-A | titleID-B | weight<br>
 * predictionsN: userID | titleID | prediction
 */
public class SlopeOneFilter {

	Map<Integer, Map<Integer, Double>> data;
	Map<Integer, Map<Integer, Double>> diffMap;
	Map<Integer, Map<Integer, Integer>> weightMap;
	Map<Integer, Map<Integer, Double>> predictions;

	public SlopeOneFilter() {
//		data  = new HashMap<>();
//		JDBC call
//		buildMaps();
//		for (int user : data.keySet()){
//			predict(user);
//		}
	}

	//Introduzco las diferencias totales de cada pareja de películas: movie_A movie_B total_diff
	public void setDiffMap(int movie_A, int movie_B, Double diff, Map<Integer,Double> movie_diff) {
		if(this.diffMap.get(movie_A) == null) {
			movie_diff = new HashMap<Integer,Double>();
			movie_diff.put(movie_B,diff);
			this.diffMap.put(movie_A,movie_diff);
		}else{
			movie_diff = this.diffMap.get(movie_A);
			if(movie_diff.get(movie_B) == null) {
				movie_diff.put(movie_B,diff);
			}else {
				movie_diff.put(movie_B,diff + movie_diff.get(movie_B));
			}
		}
	}

	//Introduzco la frecuencia de cada pareja de peliculas: movie_A movie_B weight
	public void setWeightMap(int movie_A, int movie_B,Map<Integer,Integer> movie_weight) {
		if(this.weightMap.get(movie_A) == null) {
			movie_weight = new HashMap<Integer,Integer>();
			movie_weight.put(movie_B,1);
			this.weightMap.put(movie_A,movie_weight);
		}else {
			movie_weight = this.weightMap.get(movie_A);
			if(movie_weight.get(movie_B) == null) {
				movie_weight.put(movie_B,1);
			}else {
				movie_weight.put(movie_B,movie_weight.get(movie_B) + 1);
			}
		}
	}

	//Calculo y establezco la diferencia promedio de cada pareja apoyandome de la frecuencia
	public void setAvgDiff() {
		for(Map.Entry<Integer, Map<Integer, Double>> entry: this.diffMap.entrySet()) {
			int movie_A = entry.getKey();
			Map<Integer,Double> movie = entry.getValue();
			for(Map.Entry<Integer, Double> my_movie: movie.entrySet()) {
				Map<Integer,Integer> movie_weight = this.weightMap.get(movie_A);
				int movie_B = my_movie.getKey();
				int weight = movie_weight.get(movie_B);
				my_movie.setValue(my_movie.getValue()/weight);
			}
		}
	}

	public void buildMaps(){
		// Crear diffMap y weightMap a partir de data.
		this.diffMap = new HashMap<Integer,Map<Integer,Double>>();
		this.weightMap = new HashMap<Integer,Map<Integer,Integer>>();
		Map<Integer,Double> movie_B_diff = new HashMap<Integer,Double>();
		Map<Integer,Integer> movie_B_weight = new HashMap<Integer,Integer>();

		for(Map.Entry<Integer, Map<Integer, Double>> entry: data.entrySet()) {
			Map<Integer,Double> user_movies = entry.getValue();
			for(Map.Entry<Integer, Double> movie: user_movies.entrySet()) {
				int movie_A = movie.getKey();
				for(Map.Entry<Integer, Double> other_movie: user_movies.entrySet()) {
					int movie_B = other_movie.getKey();
					if(movie_A == movie_B) {
						continue;
					}

					Double score_A = movie.getValue();
					Double score_B = other_movie.getValue();
					Double diff = score_A - score_B;

					setDiffMap(movie_A,movie_B,diff,movie_B_diff);
					setWeightMap(movie_A,movie_B,movie_B_weight);
				}
			}
		}
		setAvgDiff();
	}

	public void predict(int user) {
		// Crear predictionsN para ese usuario determinado.
	}

	public void recommend(int user, int nItems) {
		// Mostrar nItems predicciones con mayor puntuación.
		Map<Integer, Double> predictionList = predictions.get(user);
		Iterator<Entry<Integer, Double>> itr = predictionList.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).iterator();

		for (int i=0; i < nItems; i++) {
			itr.hasNext();
			System.out.println(itr.next());
		}
	}

	public static void main(String args[]){
		SlopeOneFilter so = new SlopeOneFilter();
		so.predictions = new HashMap<>();
		so.predictions.put(1, new HashMap<Integer, Double>());
		so.predictions.get(1).put(1 , 8.0);
		so.predictions.get(1).put(2 , 1.0);
		so.predictions.get(1).put(3 , 10.0);
		so.recommend(1, 2);
		System.out.println(so.predictions);
	}
}
