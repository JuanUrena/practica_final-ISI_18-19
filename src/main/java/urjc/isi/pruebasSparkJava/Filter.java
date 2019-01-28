package urjc.isi.pruebasSparkJava;

import java.util.List;

// Clase encargada de todo lo relacionado con el filtrado.

import spark.Request;

public class Filter {

	// Método encargado de mostrar al usuario el menú con las distintas
	// opciones para filtrar (HTML devuelto al hacer GET sobre /filter).
	public static String showFilterMenu() {
		String menu = 	"<h1>Búsqueda personalizada de películas</h1>" + 
						"<p>Elija el criterio por el que desea filtrar en nuestra base de datos.</p>" +
						"<hr>" +
						"<h4>1. Filtrar por nombre de la película.</h4>" +
						"<form action='/filter_name' method='post'>" +
							"<label for='film'>Nombre de la película: </label>" + 
							"<input type='text' name='film' id='film'> " +
							"<input type='submit' value='Buscar'>" +
						"</form>" +
						"<hr>" +
						"<h4>2. Filtrar por año de estreno de la película.</h4>" +
						"<form action='/filter_year' method='post'>" +
							"<label for='year'>Año de la película: </label>" + 
							"<input type='text' name='year' id='year'> " +
							"<input type='submit' value='Buscar'>" +
						"</form>" +
						"<hr>" +
						"<h4>3. Filtrar por nombre de un actor/actriz que participa en la película.</h4>" +
						"<form action='/filter_actoractress' method='post'>" +
							"<label for='actoractress'>Actor/actriz que participa en la película: </label>" + 
							"<input type='text' name='actoractress' id='actoractress'> " +
							"<input type='submit' value='Buscar'>" +
						"</form>" +
						"<hr>";
		return menu;
	}

	// Método encargado de mostrar al usuario toda la información sobre
	// la película cuyo nombre ha introducido (HTML devuelto al hacer
	// POST sobre /filter_name).
	public static String showFilmByName(Injector conn, Request req) {
		String response, result;
		
		Formulario f=new Formulario();
		Comment c=new Comment();
		
    	List<String> movieFields = conn.filterByName(req.queryParams("film"));
    	
    	if (movieFields.isEmpty()) {
    		response = "<p>Desafortunadamente, no se ha encontrado ninguna película "
    				+ "con nombre " + "'" + req.queryParams("film") + "'" + " en la "
    				+ "base de datos.</p>";
    	} else {
        	result = "<table border=2" +
						"<tr>" +
							"<th>Título</th>" +
							"<th>Año</th>" +
							"<th>Duración</th>" +
							"<th>Puntuación media</th>" +
							"<th>Número de votos</th>" +
							"<th>Géneros</th>" +
						"</tr>" +
						"<tr align='center'>" +
							"<td>" + movieFields.get(0) + "</td>" +
							"<td>" + movieFields.get(1) + "</td>" +
							"<td>" + movieFields.get(2) + " min" + "</td>" +
							"<td>" + movieFields.get(3) + "</td>" +
							"<td>" + movieFields.get(4) + "</td>" +
							"<td>" + movieFields.get(5) + "</td>" +
						"</tr>" +
					"</table>";
        	response = result+ f.formulary;
        	response=response+c.commentsFilm("titulo");
    	}
    	return response;
	}
	
	// Método encargado de mostrar al usuario todas las películas estrenadas
	// en el año que ha introducido (HTML devuelto al hacer POST sobre /filter_year).
	public static String showFilmByYear(Request req) {
    	// Los siguientes valores están puestos a modo de prueba. Hay que
    	// sustituirlos por los valores que devuelva la función que busque en la BD.
    	String peli1 = "película de prueba 1";
    	String peli2 = "película de prueba 2";
    	String peli3 = "película de prueba 3";
		
    	String table = "<table border=2" +
							"<tr>" +
								"<th>Año: " + req.queryParams("year") + "</th>" +
							"</tr>" +
							"<tr>" +
								"<td>" + peli1 + "</td>" +
							"</tr>" +
							"<tr>" +
								"<td>" + peli2 + "</td>" +
							"</tr>" +
							"<tr>" +
								"<td>" + peli3 + "</td>" +
							"</tr>" +	
						"</table>";
    	return table;
	}
	
	// Método encargado de mostrar al usuario todas las películas en las que
	// participa el actor/actriz que ha introducido (HTML devuelto al hacer POST
	// sobre /filter_actoractress).
	public static String showFilmByActorActress(Request req) {
    	// Los siguientes valores están puestos a modo de prueba. Hay que
    	// sustituirlos por los valores que devuelva la función que busque en la BD.
    	String peli1 = "película de prueba 1";
    	String peli2 = "película de prueba 2";
    	String peli3 = "película de prueba 3";
    	
    	String table = "<table border=2" +
							"<tr>" +
								"<th>Actor/Actriz: " + req.queryParams("actoractress") + "</th>" +
							"</tr>" +
							"<tr>" +
								"<td>" + peli1 + "</td>" +
							"</tr>" +
							"<tr>" +
								"<td>" + peli2 + "</td>" +
							"</tr>" +
							"<tr>" +
								"<td>" + peli3 + "</td>" +
							"</tr>" +	
						"</table>";
    	return table;
	}
	
	// Método encargado de mostrar al usuario todas las películas con una duración
	// menor a la que se ha introducido (HTML devuelto al hacer POST sobre /filter_duration)
	public static String showFilmByDuration(Request req) {
    	// Los siguientes valores están puestos a modo de prueba. Hay que
    	// sustituirlos por los valores que devuelva la función que busque en la BD.
    	String peli1 = "película de prueba 1";
    	String peli2 = "película de prueba 2";
    	String peli3 = "película de prueba 3";
		
    	String table = "<table border=2" +
							"<tr>" +
								"<th>Año: " + req.queryParams("duration") + "</th>" +
							"</tr>" +
							"<tr>" +
								"<td>" + peli1 + "</td>" +
							"</tr>" +
							"<tr>" +
								"<td>" + peli2 + "</td>" +
							"</tr>" +
							"<tr>" +
								"<td>" + peli3 + "</td>" +
							"</tr>" +	
						"</table>";
    	return table;
	}

	// Método encargado de mostrar al usuario todas las películas con el genero
	// que se ha introducido (HTML devuelto al hacer POST sobre /filter_genre)
	public static String showFilmByGenre(Request req) {
    	// Los siguientes valores están puestos a modo de prueba. Hay que
    	// sustituirlos por los valores que devuelva la función que busque en la BD.
    	String peli1 = "película de prueba 1";
    	String peli2 = "película de prueba 2";
    	String peli3 = "película de prueba 3";
		
    	String table = "<table border=2" +
							"<tr>" +
								"<th>Año: " + req.queryParams("genre") + "</th>" +
							"</tr>" +
							"<tr>" +
								"<td>" + peli1 + "</td>" +
							"</tr>" +
							"<tr>" +
								"<td>" + peli2 + "</td>" +
							"</tr>" +
							"<tr>" +
								"<td>" + peli3 + "</td>" +
							"</tr>" +	
						"</table>";
    	return table;
	}

	// Método encargado de mostrar al usuario todas las películas con una valoración
	// mayor a la que se ha introducido (HTML devuelto al hacer POST sobre /filter_rating)
	public static String showFilmByRating(Request req) {
    	// Los siguientes valores están puestos a modo de prueba. Hay que
    	// sustituirlos por los valores que devuelva la función que busque en la BD.
    	String peli1 = "película de prueba 1";
    	String peli2 = "película de prueba 2";
    	String peli3 = "película de prueba 3";
		
    	String table = "<table border=2" +
							"<tr>" +
								"<th>Año: " + req.queryParams("rating") + "</th>" +
							"</tr>" +
							"<tr>" +
								"<td>" + peli1 + "</td>" +
							"</tr>" +
							"<tr>" +
								"<td>" + peli2 + "</td>" +
							"</tr>" +
							"<tr>" +
								"<td>" + peli3 + "</td>" +
							"</tr>" +	
						"</table>";
    	return table;
	}
}
