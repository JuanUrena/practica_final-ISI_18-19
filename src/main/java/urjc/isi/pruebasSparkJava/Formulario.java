package urjc.isi.pruebasSparkJava;


public class Formulario {
	static String formulary=
			"<form action='/comment' method='post'> "
				+ "Comment:<br> <input type='text' name='comment'> "
				+ "User: <input type='number' name='user' required/><br> "
				+ "<input type='submit' value='Comentar'> "
				+"<input type='hidden' name='film' value='3'>"
			+ ""
			+ "</form> <form action='/score' method='post'> "
				+ "Score(1-5):<br> <input type='number' name='score' min='1' max='5'> "
				+ "User: <input type='number' name='user' required/><br> "
				+ "<input type='submit' value='Puntuar'> </form>"
				+"<input type='hidden' name='film' value='3'";

}
