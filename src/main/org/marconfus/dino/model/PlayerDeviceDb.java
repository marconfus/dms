package org.marconfus.dino.model;

import java.util.ArrayList;

import org.marconfus.dino.model.PlayerDevice.PlayerDeviceType;

public class PlayerDeviceDb {

	public static PlayerDeviceDb Database = null;
	
	private ArrayList<PlayerDevice> players;
	
	public PlayerDeviceDb(){
		players = new ArrayList<PlayerDevice>();
	}
	
	public static PlayerDeviceDb getInstance(){
		if (Database != null) {
			return (Database);
		} else {
			Database = new PlayerDeviceDb();
			return (Database);
		}
	}
	
	public PlayerDevice findByID(String ID) {
		for (PlayerDevice p : players) {
			if (p.getID().equals(ID)) {
				return (p);
			}
		}
		return (null);
	}
	
	public void removePlayer(String ID){
		PlayerDevice fp = null;
		for (PlayerDevice p : players) {
			if (p.getID().equals(ID)) {
				fp = p;
			}
		}
		if (fp != null) {
			players.remove(fp);
		}
	}
	
	public void removePlayer(PlayerDevice player) {
		players.remove(player);
	}
	
	public void addPlayer(PlayerDevice player) {
		Boolean found = false;
		
		// don't add a player twice
		for (PlayerDevice p : players) {
			if (player.getID().equals(p.getID())) {
				found = true;
			}
		}
		
		if (! found) {
			players.add(player);
		}
	}
	
	public ArrayList<PlayerDevice> getPlayers() {
		return players;
	}
	
	public ArrayList<PlayerDevice> getPlayers(PlayerDeviceType type) {
		ArrayList<PlayerDevice> rplayers = new ArrayList<PlayerDevice>();
		for (PlayerDevice player : players) {
			if (player.getType() == type) {
				rplayers.add(player);
			}
		}
		return rplayers;
	}
}
