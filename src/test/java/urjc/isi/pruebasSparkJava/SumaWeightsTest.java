package urjc.isi.pruebasSparkJava;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;

import urjc.isi.pruebasSparkJava.SlopeOneFilter;
import urjc.isi.pruebasSparkJava.SlopeOneFilter.Node;
import urjc.isi.pruebasSparkJava.Injector;

public class SumaWeightsTest {

	private String s;
	Injector connector = new Injector("JDBC_DATABASE_URL");
	SlopeOneFilter so = new SlopeOneFilter(connector);

	int user;
	int nItems;
	String reString;
	String expString;

	@Before
	public void setUp()
	{
		user = 0;
		nItems = 0;
		retString = null;
		expString = null;
	}


	@After
	public void tearDown()
	{
		user = 0;
		nItems = 0;
		reString = null;
		expString = null;
	}



// BLACK BOX TESTS

	@Test
	public void happyPath(){
		expectedSum = 6;
		Map<Integer, Integer> w1 = new HashMap<Integer, Integer>();
                Map<Integer, Integer> w2 = new HashMap<Integer, Integer>();
		Map<Integer, Map<Integer, Integer>> weights = new HashMap<Integer, <Integer, Integer>>();

		w1.put(3, 3);
		w2.put(2, 3);
		weights.put(1, w1);
		weights.put(1, w2);

		Sum = so.sumaWeights(weights, 1);

		assertEquals(expectedSum, Sum);
	}



// WHITE BOX TESTS




}
