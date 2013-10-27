package org.marconfus.dino.upnp;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.marconfus.dino.model.AppConfig;
import org.marconfus.dino.model.PlayerDevice;
import org.marconfus.dino.model.RadioStation;
import org.teleal.cling.controlpoint.ActionCallback;
import org.teleal.cling.model.action.ActionInvocation;
import org.teleal.cling.model.meta.Action;
import org.teleal.cling.model.meta.RemoteDevice;
import org.teleal.cling.model.meta.RemoteService;

public class UpnpPlayerDevice extends PlayerDevice {

	private static final Logger logger = Logger
			.getLogger(UpnpPlayerDevice.class);

	RemoteDevice upnpDevice;
	RemoteService remoteService;
	
	RadioStation currentStation;

	public UpnpPlayerDevice(String name, String id) {
		super(name, id);
		// TODO Auto-generated constructor stub
	}

	public UpnpPlayerDevice(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public UpnpPlayerDevice(RemoteDevice device, RemoteService rs) {
		super(device.getDetails().getFriendlyName(), device.getIdentity()
				.getUdn().getIdentifierString());
		this.upnpDevice = device;
		this.remoteService = rs;
	}

	public void playStream(String URL) {

		// FIXME: make configurable
		if (getName().equals("DENON")) {
			if ( ! URL.matches(".*\\....$") ) {
				if (URL.matches(".*:\\d+$")) {
					URL = URL + "/";
				}
				URL = URL + ";play.mp3";
			}
		}
		logger.info("playStream " + URL + " on " + upnpDevice + " service "
				+ remoteService);
		
		stop();
		setAVTransportURI(URL);
		play();

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setAVTransportURI(String URL) {
		Action setAVTransportURIAction = remoteService
				.getAction("SetAVTransportURI");
		ActionInvocation setAVTransportURIInvocation = new ActionInvocation(
				setAVTransportURIAction);
		setAVTransportURIInvocation.setInput("InstanceID", "0");
		setAVTransportURIInvocation.setInput("CurrentURI", URL);
		setAVTransportURIInvocation.setInput("CurrentURIMetaData", "NONE");
		new ActionCallback.Default(setAVTransportURIInvocation, AppConfig
				.getInstance().getUpnpService().getControlPoint()).run();

		if (setAVTransportURIInvocation.getFailure() != null) {
			logger.error(setAVTransportURIInvocation.getFailure());
		}
		
	}
	
	public void playStation(RadioStation station) {
		String URL;
		try {
			URL = station.getStreamURL();
			playStream(URL);
			currentStation = station;
		} catch (Exception e) {
			logger.error(e);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void play(){
		Action playAction = remoteService.getAction("Play");
		ActionInvocation playInvocation = new ActionInvocation(playAction);
		playInvocation.setInput("InstanceID", "0");
		
		if (remoteService.getAction("Play").getInputArgument("Speed") != null) {
			playInvocation.setInput("Speed", "1");
		}
		
		new ActionCallback.Default(playInvocation, AppConfig.getInstance()
				.getUpnpService().getControlPoint()).run();
		
		if (playInvocation.getFailure() != null) {
			logger.error(playInvocation.getFailure());
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void stop() {
		Action stopAction = remoteService.getAction("Stop");
		ActionInvocation stopInvocation = new ActionInvocation(stopAction);
		stopInvocation.setInput("InstanceID", "0");
		new ActionCallback.Default(stopInvocation, AppConfig.getInstance()
				.getUpnpService().getControlPoint()).run();
		
		if (stopInvocation.getFailure() != null) {
			logger.error(stopInvocation.getFailure());
		}
	}

	public RadioStation getCurrentStation() {
		return currentStation;
	}
}
