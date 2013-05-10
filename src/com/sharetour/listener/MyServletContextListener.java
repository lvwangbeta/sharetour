package com.sharetour.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.sharetour.db.ConnectionPool;
/**
 * Application Lifecycle Listener implementation class MyServletContextListener
 *
 */
public class MyServletContextListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public MyServletContextListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
    	
        // TODO Auto-generated method stub
    	ServletContext context = arg0.getServletContext();
    	ConnectionPool.setPoolFromParam("jdbc:mysql://172.30.48.29:3306/d830e97b407104c25a9535461208a9470", 
    									"u74ktCJ81MdBT", "ppIEca9zHLkZm", 
    									"com.mysql.jdbc.Driver", 
    									5, 100, 30, 10000);
    	ConnectionPool db =  ConnectionPool.getInstance(); 
    	
    	context.setAttribute("database", db);
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    }
	
}
