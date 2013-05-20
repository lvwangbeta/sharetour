package com.sharetour.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.sharetour.db.ConnectionPool;
import com.sharetour.db.MongoDBPool;

/**
 * Application Lifecycle Listener implementation class MyServletContext
 *
 */
public class MyServletContext implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public MyServletContext() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce) {
        // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce) {
        MongoDBPool.close();
        ConnectionPool.close();
    }
	
}
