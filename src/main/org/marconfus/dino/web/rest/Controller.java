package org.marconfus.dino.web.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.marconfus.dino.model.AppConfig;
import org.marconfus.dino.model.PlayerDevice;
import org.marconfus.dino.model.PlayerDeviceDb;
import org.marconfus.dino.model.RadioStation;
import org.marconfus.dino.model.RadioStationDb;
import org.marconfus.dino.upnp.UpnpPlayerDevice;

@Path("/")
public class Controller {

	private static final Logger logger = Logger.getLogger(Controller.class);

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHello() {
		return "Hello Jersey";
	}

	@GET
	@Path("/play/{id}")
	@Produces(MediaType.TEXT_HTML)
	public String playStation (@PathParam("id") String id, @Context HttpServletRequest httpRequest) {
		logger.info("Switching to station " + id);
		UpnpPlayerDevice player = (UpnpPlayerDevice) AppConfig.getInstance().getSelectedPlayer();
		RadioStation station = RadioStationDb.getInstance().findById(id);
		logger.info("Stationname :" + station.getName());
		player.playStation(station);
		return ("OK");
	}

	@GET
	@Path("/stop")
	@Produces(MediaType.TEXT_HTML)
	public String stop (@PathParam("id") String id, @Context HttpServletRequest httpRequest) {
		logger.info("Switching to station " + id);
		UpnpPlayerDevice player = (UpnpPlayerDevice) AppConfig.getInstance().getSelectedPlayer();
		player.stop();
		return ("OK");
	}
	
	
	@GET
	@Path("/selectplayer/{id}")
	@Produces(MediaType.TEXT_HTML)
	public String selectStation(@PathParam("id") String id, @Context HttpServletRequest httpRequest) {
		logger.info("Selecting player " + id);
		PlayerDevice player = PlayerDeviceDb.getInstance().findByID(id);
		AppConfig.getInstance().setSelectedPlayer(player);
		
		return ("OK");
	}
	
}
