package org.marconfus.dino.threads;

import org.apache.log4j.Logger;
import org.marconfus.dino.model.AppConfig;
import org.marconfus.dino.upnp.DinoRegistryListener;
import org.marconfus.dino.upnp.DinoUpnpServiceConfiguration;
import org.teleal.cling.UpnpService;
import org.teleal.cling.UpnpServiceImpl;
import org.teleal.cling.model.message.header.STAllHeader;

public class UpnpDiscoverThread extends Thread {
	
	private static final Logger logger = Logger.getLogger(UpnpDiscoverThread.class);
	
	public void run() {
		
		DinoRegistryListener listener = new DinoRegistryListener();
		// This will create necessary network resources for UPnP right away
        logger.info("Starting Upnp discovery...");
        UpnpService upnpService = new UpnpServiceImpl(new DinoUpnpServiceConfiguration(), listener);

        // Send a search message to all devices and services, they should respond soon
        upnpService.getControlPoint().search(new STAllHeader());

        AppConfig.getInstance().setUpnpService(upnpService);
        
        while (true) {
        	try {
				sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}

}
