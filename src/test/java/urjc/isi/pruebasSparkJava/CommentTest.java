package urjc.isi.pruebasSparkJava;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public class CommentTest {
	
	private Comment comment;
	private Connection connection;
	@Before // called before every test method.
	public void setUp(){
		connection = null;
		try {
  			connection = DriverManager.getConnection("jdbc:sqlite:Database/IMDb.db");
		} catch(SQLException e) {
      		System.err.println(e.getMessage());
		}
	}
	
	@After // called after every test method.
 	public void tearDown()
 	{
 		try{
 			if(connection != null){
         		connection.close();
       			}
     		} catch(SQLException e) {
     			e.printStackTrace();
    	 	}
 	}
	
	//tests for newComment(String text, int user, String film) 
	
	// test for null comment
	@Test (expected = IllegalArgumentException.class)
	public void testforNullText(){
		comment.newComment(null, 5, "Kill Bill: Volumen 3");
	}
	
	//test for invalid user
	@Test (expected = IllegalArgumentException.class)
	public void testforInvalidUser(){
		comment.newComment("comentario", -1, "Kill Bill: Volumen 3");
	}
	
	//test for invalid film
	@Test (expected = IllegalArgumentException.class)
	public void testforNullFilm(){
		comment.newComment("comentario", 4, null);
	}
	
	//test for commentsFilm(String film)
	
	//test for invalid film
	@Test (expected = IllegalArgumentException.class)
	public void testforInvalidFilm(){
		comment.commentsFilm(null);
	}
	
	//happy path tests
	
	@Test
	public void newCommentTest(){
		assertEquals ("comentario", "Comentario Almacenado", comment.newComment("comentario", 4, "Kill Bill: Volumen 3"));
	}
		
	@Test
	public void commentsFilmTest(){
		String resultc;
		resultc = comment.commentsFilm("Kill Bill: Volumen 3");
		assertNotNull("Comments cant be null", resultc);
		
	}
}
