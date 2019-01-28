package urjc.isi.pruebasSparkJava;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.util.Map;
import java.util.HashMap;


public class Injector {

	private static Connection c;

	public Injector(String name) {
		try {		    
		    String dbUrl = System.getenv(name);
		    c = DriverManager.getConnection(dbUrl);

			c.setAutoCommit(false);
		}catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}

	public static void insertFilm(String data1, String data2, String data3){
    		String sql="";
		//Comprobar elementos que son distintos que null
    		if(data1 == null || data2 == null){
    			throw new NullPointerException();
    		}
    			sql = "INSERT INTO movies (title, year, genres) VALUES(?,?,?)";

    		try (PreparedStatement pstmt = c.prepareStatement(sql)) {
    			pstmt.setString(1, data1);
    			pstmt.setString(2, data2);
    			pstmt.setString(3, data3);
    			pstmt.executeUpdate();
    		} catch (SQLException e) {
    	   	 System.out.println(e.getMessage());
    		}
    	}

	public static void insertActor(String data1){
    		String sql="";
		//Comprobar elementos que son distintos que null
    		if(data1 == null){
    			throw new NullPointerException();
    		}
    			sql = "INSERT INTO workers (primaryName) VALUES(?)";

    		try (PreparedStatement pstmt = c.prepareStatement(sql)) {
    			pstmt.setString(1, data1);
    			pstmt.executeUpdate();
    		} catch (SQLException e) {
    	   	 	System.out.println(e.getMessage());
    		}
    	}
	

	public List<String> filterByName(String film) {
		String sql = "SELECT * FROM movies WHERE title = "+"'"+film+"'";
		List<String> result = new ArrayList<String>();

    	try (PreparedStatement pstmt = c.prepareStatement(sql)) {
    		ResultSet rs = pstmt.executeQuery();
    		c.commit();
    		rs.next();
    		String id=Integer.toString(rs.getInt("titleID"));
            String title = rs.getString("title");
            String year = Integer.toString(rs.getInt("year"));
            String runtimeMinutes = Integer.toString(rs.getInt("runtime_minutes"));
            String averageRating = (Double.toString(rs.getDouble("average_rating"))).substring(0, 3);
            String numVotes = Integer.toString(rs.getInt("num_votes"));
            String genres = rs.getString("genres");
            result.add(title);
            result.add(year);
            result.add(runtimeMinutes);
            result.add(averageRating);
            result.add(numVotes);
            result.add(genres);
            result.add(id);
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    	return result;
	}

	public List<String> filterByYear(String year) {
		String sql = "SELECT * FROM movies WHERE year = "+"'"+year+"'";
		List<String> result = new ArrayList<String>();

    	try (PreparedStatement pstmt = c.prepareStatement(sql)) {
    		ResultSet rs = pstmt.executeQuery();
    		c.commit();
    		while(rs.next()) {
                String title = rs.getString("title");
                result.add(title);
            }
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    	return result;
	}

	public List<String> filterByDuration(Integer minutes) {
		String sql = "SELECT * FROM movies WHERE runtimeMinutes <= "+minutes;
		List<String> result = new ArrayList<String>();

    	try (PreparedStatement pstmt = c.prepareStatement(sql)) {
    		ResultSet rs = pstmt.executeQuery();
    		c.commit();
    		while(rs.next()) {
                String title = rs.getString("title");
                result.add(title);
            }
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    	return result;
	}

	public List<String> filterByRating(Double rating) {
		String sql = "SELECT * FROM movies WHERE averageRating >= "+rating;
		List<String> result = new ArrayList<String>();

    	try (PreparedStatement pstmt = c.prepareStatement(sql)) {
    		ResultSet rs = pstmt.executeQuery();
    		c.commit();
    		while(rs.next()) {
                String title = rs.getString("title");
                result.add(title);
            }
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    	return result;
	}

	public Integer meanScores(String film) {
		String sql = "SELECT avg(score) FROM ratings JOIN movies ON movies.titleID = ratings.titleID WHERE movies.title LIKE "+'"'+film+'"' + " GROUP BY ratings.titleID";
    	Integer result = 0;

    	try (PreparedStatement pstmt = c.prepareStatement(sql)) {
    		ResultSet rs = pstmt.executeQuery();
    		c.commit();
    		result = rs.getInt("avg(score)");
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    	return result;
	}

	public Integer contar(String name_table,String name_col) {
		String sql = "SELECT COUNT("+name_col+") FROM "+ name_table;
		Integer result = 0;

		try (PreparedStatement pstmt = c.prepareStatement(sql)) {
    		ResultSet rs = pstmt.executeQuery();
    		c.commit();
    		result = rs.getInt("COUNT("+name_col+")");
    	} catch (SQLException e) {

    		System.out.println(e.getMessage());
    	}
		return result;
	}

	public String[][] userandcomments(String film){
		String sql = "SELECT comment,clientID FROM Comments JOIN movies ON movies.titleID = Comments.titleID JOIN clients ON clients.clientID=movies.clientID WHERE movies.title LIKE "+"+film+"+" GROUP BY clientID";

		String name_col= "clientID";
		String name_col2= "commentID";
		String table = "Comments";
		String table2 = "clients";
		Integer total_comment = 0;
		Integer total_clients = 0;
		total_comment = contar(table,name_col);
		total_clients = contar(table2,name_col2);
		String[][] result = new String[total_clients][total_comment];
		Integer aux = 0;

		try (PreparedStatement pstmt = c.prepareStatement(sql)) {
    		ResultSet rs = pstmt.executeQuery();
    		c.commit();

    		while(rs.next()) {
    			aux = rs.getInt("ClientID");
    			for (int i = 0; i< total_comment;i++) {
    				if (result[aux-1][i] == null) {
    					result[aux-1][i] = rs.getString("comment");
    					break;
    				}
    			}
   		}
		} catch (SQLException e) {

    		System.out.println(e.getMessage());
    	}
		return result;
	}

	public List<String> filterByGenre(String genre) {
		String sql = "SELECT title FROM movies WHERE genres LIKE "+'"'+"%"+genre+"%"+'"';
		List<String> result = new ArrayList<String>();

    	try (PreparedStatement pstmt = c.prepareStatement(sql)) {
    		ResultSet rs = pstmt.executeQuery();
    		c.commit();
    		while(rs.next()) {

                String title = rs.getString("title");
                result.add(title);
            }
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    	return result;
	}

	public List<String> filterByActorActress(String name) {
		String sql = "SELECT title FROM movies JOIN works_in ON movies.titleID=works_in.titleID ";
		sql+= "JOIN workers ON workers.nameID=works_in.nameID ";
		sql += "WHERE workers.primaryName LIKE "+'"' + name +'"';
		sql += " and (worksas LIKE 'actor' or worksas LIKE 'actress')";
		List<String> result = new ArrayList<String>();

    	try (PreparedStatement pstmt = c.prepareStatement(sql)) {
    		ResultSet rs = pstmt.executeQuery();
    		c.commit();
    		while(rs.next()) {
                String title = rs.getString("title");
                result.add(title);
            }
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    	return result;
	}

	public void makeDataHashMap(Map<Integer, Map<Integer, Double>> data) {
		String sql = "SELECT * FROM ratings ORDER BY clientid;";

		try (PreparedStatement pstmt = c.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
			Integer titleid = rs.getInt("titleid");
			Integer clientid = rs.getInt("clientid");
			Double score = rs.getDouble("score");

			if (!data.containsKey(clientid)) {
				data.put(clientid, new HashMap<Integer, Double>());
			}

			data.get(clientid).put(titleid, score);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}


    public Boolean searchRating(Integer titleID, Integer clientID) {
		String sql = "SELECT score FROM ratings WHERE titleID = "+ titleID;
		sql += " and clientID = "+ clientID;
		try (PreparedStatement pstmt = c.prepareStatement(sql)) {
			ResultSet rs= pstmt.executeQuery();
			rs.next();
			rs.getInt("score");
			return true;
		}catch (SQLException e) {
			return false;
		}
	}

    public void insertRating(Integer titleID, Integer clientID, Integer score) {
	String sql= new String();

    	if(searchRating(titleID, clientID)) {
    		sql = "UPDATE ratings SET score=" + score;
    		sql += " WHERE titleID=" + titleID + " and clientID="+ clientID;
    		try (PreparedStatement pstmt = c.prepareStatement(sql)) {
        		pstmt.executeUpdate();
        		c.commit();
        	} catch (SQLException e) {
        		System.out.println(e.getMessage());
        	}
    	}else {
    		sql = "INSERT INTO ratings(titleID, clientID,score) VALUES(?,?,?)";
    		try (PreparedStatement pstmt = c.prepareStatement(sql)) {
        		pstmt.setInt(1, titleID);
        		pstmt.setInt(2, clientID);
        		pstmt.setInt(3, score);
        		pstmt.executeUpdate();
        		c.commit();
        	} catch (SQLException e) {
        		System.out.println(e.getMessage());
        	}
    	}
    }

    public Boolean searchUser(Integer clientID) {
		String sql = "SELECT clientID FROM clients WHERE clientID = "+ clientID;
		try (PreparedStatement pstmt = c.prepareStatement(sql)) {
			ResultSet rs= pstmt.executeQuery();
			rs.next();
			rs.getInt("clientID");
			return true;
		}catch (SQLException e) {
			return false;
		}
	}

	public void insertUser(Integer clientid) {
		String sql= new String();

    	if(!searchUser(clientid) ){
    		sql = "INSERT INTO clients(clientID) VALUES(clientid)";
    		try (PreparedStatement pstmt = c.prepareStatement(sql)) {
        		pstmt.executeUpdate();
        		c.commit();
        	} catch (SQLException e) {
        		System.out.println(e.getMessage());
        	}
    	}
    }

//titleid, clientID y comment
//NOMBRE TABLA: comments(Hay que crearla)
    public void insertComments(Integer titleid, Integer clientid, String comments) {
   		String sql= "INSERT INTO comments(titleID, clientID,comment) VALUES("+titleid+","+clientid+","+comments+")";

       	try (PreparedStatement pstmt = c.prepareStatement(sql)) {
           	pstmt.executeUpdate();
           	c.commit();
           } catch (SQLException e) {
          	System.out.println(e.getMessage());
        }
   	}

	public void close() {
        try {
            c.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
