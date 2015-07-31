/**
 * 
 */
package com.bhd.cd.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author bhd
 *
 */
public class SessionUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();
	
	/**
	 * Default Constructor
	 */
	public SessionUtil() {
	}
	
	/**
	 * Create SessionFactory from the hibernate configuration file
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();  
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    /**
     * @return the sessionFactory object
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

} 
 