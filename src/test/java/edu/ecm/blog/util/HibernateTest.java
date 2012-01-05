package edu.ecm.blog.util;

import static org.junit.Assert.fail;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import edu.ecm.blog.domain.Author;
import edu.ecm.blog.domain.Post;

public class HibernateTest {

	@Test
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

		SessionFactory sessionFactory = configuration.buildSessionFactory();

	}
}
