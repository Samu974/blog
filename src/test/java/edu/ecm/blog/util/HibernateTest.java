package edu.ecm.blog.util;



import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.ecm.blog.domain.Author;
import edu.ecm.blog.domain.Post;

public class HibernateTest {

	private SessionFactory sessionFactory;


	@Before
	public void createSessionFactory() {
		Configuration configuration = new Configuration();

		
		configuration.setProperty("hibernate.dialect",
				"org.hibernate.dialect.DerbyDialect");
		configuration.setProperty("hibernate.connection.url",
				"jdbc:derby:target/testdb;create=true");
		configuration.setProperty("hibernate.connection.driver_class",
				"org.apache.derby.jdbc.EmbeddedDriver");
		configuration.setProperty("hibernate.hbm2ddl.auto", "create-drop");

		configuration.addAnnotatedClass(Author.class);
		configuration.addAnnotatedClass(Post.class);
		
		sessionFactory=configuration.buildSessionFactory();

		

	}
	
	@Test
	public void saveAuthor() {
	    Author author = new Author();
	    Session session = sessionFactory.openSession();
	    author.setName("Harpo Marx");
	    author.setEmail("harpo.marx@gmail.com");



	    session.save(author);

	    session.close();
	}
	
	
	@Test
	public void findAuthor() {
	    saveAuthor();

	    Session session = sessionFactory.openSession();

	    Query query = session.createQuery("from Author where name = :name");

	    query.setString("name", "Harpo Marx");

	    List<Author> authors = query.list();

	    session.close();

	    Assert.assertEquals(1, authors.size());
	    Assert.assertEquals("Harpo Marx", authors.get(0).getName());
	}
	
	@After
	public void cleanDb() {
	    Session session = sessionFactory.openSession();

	    Transaction transaction = session.beginTransaction();

	    session.createQuery("delete from Author").executeUpdate();

	    transaction.commit();

	    session.close();

	    sessionFactory.close();
	}
}
