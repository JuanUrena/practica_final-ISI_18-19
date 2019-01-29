package urjc.isi.pruebasSparkJava;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import urjc.isi.pruebasSparkJava.SlopeOneFilter;
import urjc.isi.pruebasSparkJava.Injector;

public class PredictTest {

	private String s;
	Injector connector = new Injector("JDBC_DATABASE_URL");
	SlopeOneFilter slopeOneFilter = new SlopeOneFilter(connector);


// BLACK BOX TESTS

	@Test
	public void happyPath(){
		slopeOneFilter.predict(3);
	}



// WHITE BOX TESTS




}
