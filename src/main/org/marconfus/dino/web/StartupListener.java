package org.marconfus.dino.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.marconfus.dino.model.RadioStationDb;
import org.marconfus.dino.threads.UpnpDiscoverThread;

public class StartupListener implements ServletContextListener {
	
	private final static Logger logger = Logger.getLogger(StartupListener.class); 

	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	public void contextInitialized(ServletContextEvent arg0) {
		
		logger.info("Let the games begin!");
		UpnpDiscoverThread udt = new UpnpDiscoverThread();
		udt.start();
		
		RadioStationDb.getInstance().readStreamsFile();
	}
	
}
