package urjc.isi.pruebasSparkJava;

import static org.junit.Assert.*;
import org.junit.*;

import urjc.isi.pruebasSparkJava.Injector;
import urjc.isi.pruebasSparkJava.SlopeOneFilter;

public class GetIndexTest {
	
	Injector connector = new Injector("JDBC_DATABASE_URL");
	SlopeOneFilter so = new SlopeOneFilter(connector);
	
	int user;
	double value;
	int retInt;
	int expInt;

	@Before
	public void setUp()
	{
		user = 0;
		value = 0;
		retInt = 0;
		expInt = 0;
	}
	
	@After
	public void tearDown()
	{
		user = 0;
		value = 0;
		retInt = 0;
		expInt = 0;
	}

	@Test
	public void testForEmptyList()
	{		
		user = 2;
		value = 10;
		expInt = 0;
		so.predictions.get(user).clear();
		retInt = so.getIndex(user, value);
		assertEquals(expInt, retInt);
	}
	
	@Test
	public void testForTwoGreaterThanValueEntries()
	{		
		user = 1;
		value = 2;
		expInt = 2;
		so.predictions.get(user).clear();
		so.predictions.get(user).add(so.new Node(1, 10));
		so.predictions.get(user).add(so.new Node(2, 9));
		retInt = so.getIndex(user, value);
		assertEquals(expInt, retInt);
	}
	
	@Test
	public void testForValueGreaterThanEntries()
	{		
		user = 1;
		value = 10;
		expInt = 0;
		so.predictions.get(user).clear();
		so.predictions.get(user).add(so.new Node(1, 10));
		so.predictions.get(user).add(so.new Node(2, 9));
		retInt = so.getIndex(user, value);
		assertEquals(expInt, retInt);
	}
	
}