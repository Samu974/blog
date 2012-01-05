package edu.ecm.blog.util;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

public class DerbyTest {
	   @Test
	   public void createDb() throws ClassNotFoundException, SQLException {
	      // chargement du driver
	      Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

	      // création d'une connexion
	      DriverManager.getConnection("jdbc:derby:target/testdb;create=true");
	   }
	}