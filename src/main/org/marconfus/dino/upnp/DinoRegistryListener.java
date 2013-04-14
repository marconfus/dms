package org.marconfus.dino.upnp;


import org.apache.log4j.Logger;
import org.marconfus.dino.model.PlayerDeviceDb;
import org.teleal.cling.model.meta.Action;
import org.teleal.cling.model.meta.ActionArgument;
import org.teleal.cling.model.meta.LocalDevice;
import org.teleal.cling.model.meta.RemoteDevice;
import org.teleal.cling.model.meta.RemoteService;
import org.teleal.cling.registry.Registry;
import org.teleal.cling.registry.RegistryListener;

public class DinoRegistryListener implements RegistryListener {

	final static Logger logger = Logger.getLogger(DinoRegistryListener.class);
	
	public void remoteDeviceDiscoveryStarted(Registry registry,
			RemoteDevice device) {
		logger.debug("Discovery started: " + device.getDisplayString());
	}

	public void remoteDeviceDiscoveryFailed(Registry registry,
			RemoteDevice device, Exception ex) {
		logger.debug("Discovery failed: " + device.getDisplayString()
				+ " => " + ex);
	}

	public void remoteDeviceAdded(Registry registry, RemoteDevice device) {
		logger.info("Remote device available: " + device.getDisplayString()
				+ " (" + device.getDetails().getFriendlyName() + ")");
		logger.info("  > UDN: " +device.getIdentity().getUdn().getIdentifierString());
		
		for (RemoteService rs : device.getServices()) {
			logger.info("    Service: " + rs.toString());
			
			if (rs.getServiceType().getType().equalsIgnoreCase("AVTransport")) {
				for (Action action : rs.getActions()) {
					logger.info("     > Action : " + action.getName());
					for (ActionArgument aa : action.getInputArguments()) {
						logger.info("       > Actionargument : " + aa.getName());
					}
				}
				UpnpPlayerDevice player = new UpnpPlayerDevice(device, rs);
				PlayerDeviceDb.getInstance().addPlayer(player);
			}
		}
	}

	public void remoteDeviceUpdated(Registry registry, RemoteDevice device) {
		logger.debug("Remote device updated: "
				+ device.getDisplayString());
	}

	public void remoteDeviceRemoved(Registry registry, RemoteDevice device) {
		String ID = device.getIdentity().getUdn().getIdentifierString();
		PlayerDeviceDb.getInstance().removePlayer(ID);
		
		logger.info("Remote device removed: "
				+ device.getDisplayString() + " UDN: " + ID);
	}

	public void localDeviceAdded(Registry registry, LocalDevice device) {
		logger.debug("Local device added: " + device.getDisplayString());
	}

	public void localDeviceRemoved(Registry registry, LocalDevice device) {
		System.out
				.println("Local device removed: " + device.getDisplayString());
	}

	public void beforeShutdown(Registry registry) {
		logger.debug("Before shutdown, the registry has devices: "
				+ registry.getDevices().size());
	}

	public void afterShutdown() {
		logger.debug("Shutdown of registry complete!");

	}

}
