package org.marconfus.dino.model;

import org.teleal.cling.UpnpService;

public class AppConfig {

	private static AppConfig appconfig = null;
	private PlayerDevice selectedPlayer = null;
	UpnpService upnpService;
	
	public UpnpService getUpnpService() {
		return upnpService;
	}

	public void setUpnpService(UpnpService upnpService) {
		this.upnpService = upnpService;
	}

	public static AppConfig getInstance(){
		if (appconfig == null) {
			appconfig = new AppConfig();
		}
		return (appconfig);
	}

	public PlayerDevice getSelectedPlayer() {
		return selectedPlayer;
	}

	public void setSelectedPlayer(PlayerDevice selectedPlayer) {
		this.selectedPlayer = selectedPlayer;
	}

	
}
