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
public class SlopeOneFilterNode {

	class Node {
		int titleID;
		double prediction;

		public Node(int t, double p) {
			titleID = t;
			prediction = p;
		}
		
		public Object getKey() { 
			return titleID; 
		}

		public Object getValue() { 
			return prediction; 
		}

		public String toString() {
			return "titleID: " + getKey() + ", Prediction: " + getValue();
		}		
	}
	
	class NodeComp implements Comparator<Node> {
		@Override
		public int compare(Node d1, Node d2) {
			if(d2.prediction<d1.prediction) {
				return -1;
			} else if(d1.prediction<d2.prediction) {
				return 1;
			} else {
				return 0;
			}
		}
	}
	
	Map<Integer, Map<Integer, Double>> data;
	Map<Integer, Map<Integer, Double>> diffMap;
	Map<Integer, Map<Integer, Integer>> weightMap;
	Map<Integer, LinkedList<Node>> predictionsN;

	public SlopeOneFilterNode() {
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
	
	public void recommendN(int user, int nItems) {
		// Mostrar nItems predicciones con mayor puntuación.
		LinkedList<Node> predictionList = predictionsN.get(user);
		predictionList.sort(new NodeComp());
		
		ListIterator<Node> itrator = predictionList.listIterator();
		 
		for (int i=0; i < nItems; i++) {
			itrator.hasNext();
			System.out.println(itrator.next());
		}
	}
	
	public static void main(String args[]){
		SlopeOneFilterNode so = new SlopeOneFilterNode();
		
		so.predictionsN = new HashMap<>();
		so.predictionsN.put(1, new LinkedList<Node>());

		so.predictionsN.get(1).add( so.new Node(1 , 8.0));
		so.predictionsN.get(1).add( so.new Node(2 , 1.0));
		so.predictionsN.get(1).add( so.new Node(3 , 10.0));

		so.recommendN(1, 2);
		System.out.println(so.predictionsN);
	}
}