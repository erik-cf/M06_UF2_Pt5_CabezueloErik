package program;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/*
 * Aquesta classe gestiona la SessionFactory d'Hibernate
 */
public class HibernateUtil {

	// Declarem i inicialitzem la SessionFactory
	private static SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	
	/*
	 * Metode que retorna la SessionFactory
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
}
