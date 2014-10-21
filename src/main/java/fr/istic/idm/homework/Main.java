package fr.istic.idm.homework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

/**
 * 
 * @author LHOMLME Anthony
 *
 */
public class Main {

	public static void main(String[] args) {
		System.out.println("===== LHOMME Anthony =====");
		
		// Information de connexion
        String userName = "sa";
        String password = "";
        String url = "jdbc:hsqldb:hsql://localhost/";

        try {
			Class.forName("org.hsqldb.jdbcDriver").newInstance();
	        try (Connection con = DriverManager.getConnection(url, userName, password)) {
	        	
	        	DSLContext create = DSL.using(con, SQLDialect.HSQLDB);
	        	example1(create);
	        	example2(create);
	        	example3(create);
	        	
	        } catch (SQLException e) {
				System.err.println("Erreur SQL ("+e.getErrorCode()+"): "+e.getMessage());
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Récupére la liste des utilisateurs née entre 1900 et 2000
	 * 
	 * @sql
	 * 	SELECT * FROM user
	 * 	WHERE year >= 1900
	 * 	AND year <= 2000
	 */
	private static void example1(DSLContext create) {
    	Result<Record> result = create.select()
    			.from("user")
    			.where("year >= 1900")
    			.and("year <= 2000")
    			.fetch();

		System.out.println("---------- Example 1 ----------");
		
    	System.out.println("Name\tYear");
    	for (Record r : result) {
    	    String name = (String) r.getValue("NAME");
    	    int year = (int) r.getValue("YEAR");

    	    System.out.println(name+'\t'+year);
    	}

		System.out.println("-------------------------------");
	}

	/**
	 * Récupére la liste des utilisateurs ainsi que leurs ville de naissance
	 * 
	 * @sql
	 * 	SELECT * FROM user
	 * 	JOIN ville ON user.idVille = ville.id
	 */
	private static void example2(DSLContext create) {
    	Result<Record> result = create.select()
    			.from("user")
    			.join("ville")
    			.on("user.idVille = ville.id")
    			.fetch();

		System.out.println("---------- Example 2 ----------");
		
    	System.out.println("Name\tYear\tVille");
    	for (Record r : result) {
    	    String name = (String) r.getValue("NAME"); //user.name
    	    int year = (int) r.getValue("YEAR");
    	    String ville = (String) r.getValue(5); //ville.name

    	    System.out.println(name+'\t'+year+'\t'+ville);
    	}

		System.out.println("-------------------------------");
	}

	/**
	 * Récupére la liste des villes non ratachées à un utilisateur
	 * 
	 * @sql
	 * 	SELECT * FROM user
	 * 	LEFT OUTTER JOIN ville ON ville.id = user.idVille
	 * 	WHERE user.idVille IS NULL
	 */
	private static void example3(DSLContext create) {
    	Result<Record> result = create.select()
    			.from("ville")
    			.leftOuterJoin("user")
    			.on("ville.id = user.idVille")
    			.where("user.idVille IS NULL")
    			.fetch();

		System.out.println("---------- Example 3 ----------");
		
    	System.out.println("Ville");
    	for (Record r : result) {
    	    String ville = (String) r.getValue("NAME");
    	    System.out.println(ville);
    	}

		System.out.println("-------------------------------");
	}
}
