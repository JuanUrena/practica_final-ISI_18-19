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

	public void buildMaps(){
		// Crear diffMap y weightMap a partir de data.
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
