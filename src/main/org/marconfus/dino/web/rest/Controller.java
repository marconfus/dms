package org.marconfus.dino.web.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
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
	public String setPlayer(@PathParam("id") String id, @Context HttpServletRequest httpRequest) {
		logger.info("Selecting player " + id);
		PlayerDevice player = PlayerDeviceDb.getInstance().findByID(id);
		AppConfig.getInstance().setSelectedPlayer(player);
		
		return ("OK");
	}
	
	@GET
	@Path("/player")
	@Produces(MediaType.TEXT_HTML)
	public String getPlayer() {
		PlayerDevice player=AppConfig.getInstance().getSelectedPlayer();
		String name;
		if (player==null) {
			name="None";
		} else {
			name=player.getName();
		}
		return (name);
	}
	
	@GET
	@Path("/currentstation")
	@Produces(MediaType.TEXT_HTML)
	public String getCurrentStation() {
		UpnpPlayerDevice player = (UpnpPlayerDevice) AppConfig.getInstance().getSelectedPlayer();
		
		if (player==null) {
			return ("Unkown");
		} else {
			return(player.getCurrentStation().getName());
		}
	}
	
	@SuppressWarnings("rawtypes")
	@GET
	@Path("/stations")
	@Produces(MediaType.APPLICATION_JSON)
	public String getStations() throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		ArrayList<HashMap> stations = new ArrayList<HashMap>();
		
		for (RadioStation rs : RadioStationDb.getInstance().getStations()) {
			HashMap<String, String> stationMap = new HashMap<String, String>();
			stationMap.put("id", rs.getID());
			stationMap.put("name", rs.getName());
			stations.add(stationMap);
		}
		String json = mapper.writeValueAsString(stations);
		
		return (json);
	}
	
	@SuppressWarnings("rawtypes")
	@GET
	@Path("availablePlayers")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAvailablePlayers() throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		ArrayList<HashMap> players = new ArrayList<HashMap>();

		for (PlayerDevice player : PlayerDeviceDb.getInstance().getPlayers()) {
			HashMap<String, String> playerMap = new HashMap<String, String>();
			playerMap.put("id", player.getID());
			playerMap.put("name", player.getName());
			
			players.add(playerMap);
		}
		String json = mapper.writeValueAsString(players);
		return (json);
	}
}
