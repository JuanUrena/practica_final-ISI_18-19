package urjc.isi.pruebasSparkJava;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;



public class CommentTest {
	
	
	private Injector I = new Injector("JDBC_DATABASE_URL");

	
	//tests for newComment(String text, int user, String film) 
	
	// test for null comment
	@Test
	public void testforNullText(){
		Comment comment = new Comment();
		assertEquals ("Comentario invalido", comment.newComment(null, 5, "Kill Bill: Volumen 3", I));
	}
	
	//test for invalid user
	@Test 
	public void testforInvalidUser(){
		Comment comment = new Comment();
		assertEquals("Usuario invalido", comment.newComment("comentario", -1, "Kill Bill: Volumen 3", I));
	}
	
	//test for invalid film
	@Test
	public void testforNullFilm(){
		Comment comment = new Comment();
		assertEquals ("Pelicula invalida", comment.newComment("comentario", 4, null, I));
	}
	
	
	//test for commentsFilm(String film)
	
	//test for invalid film
	@Test 
	public void testforInvalidFilm(){
		Comment comment = new Comment();
		assertEquals ("Pelicula invalida", comment.commentsFilm(null, I));
	}
	
	//test for commentToString (String matrix_coment[][])
	
	@Test 
	public void testforNullComments(){
		Comment comment = new Comment();
		assertEquals("Comentario invalido", comment.commentToString(null));
	}
	
	//happy path tests
	
	@Test
	public void newCommentTest(){
		Comment comment = new Comment();
		assertEquals ("Comentario almacenado", comment.newComment("comentario", 4, "Titanic", I));
	}
		
	@Test
	public void commentsFilmTest(){
		Comment comment = new Comment();
		String resultc;
		resultc = comment.commentsFilm("Titanic", I);
		assertNotNull("Comments cant be null", resultc);
		
	}
	
	@Test
	public void commentToStringTest(){
		Comment comment = new Comment();
		String resultc;
		String [][] comments = {{"comentario 1", "comentario 2"}};
		resultc = comment.commentToString(comments);
		assertNotNull("Comments cant be null", resultc);
	}
	/*	
	@Test
	public void postCommentTest(){
		Request req;
		Comment comment = new Comment();
		String resultc;
		resultc = comment.postComment(req, I);
		assertNotNull("comments cant be null", resultc);
	}*/

}
