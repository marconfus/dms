package org.marconfus.dino.model;

import java.util.UUID;


public abstract class PlayerDevice {

	public enum PlayerDeviceType {
		UnknownPlayerDevice, UpnpPlayerDevice, MpdPlayerDevice;
	}
	
	String Name = null;
	String ID = null;
	PlayerDeviceType Type = PlayerDeviceType.UnknownPlayerDevice;

	public PlayerDevice(){
	}
	
	public PlayerDevice(String name){
		this.Name = name;
		UUID uuid = UUID.randomUUID();
		this.ID = uuid.toString();
	}

	public PlayerDevice(String name, String id){
		this.Name = name;
		this.ID = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public PlayerDeviceType getType() {
		return Type;
	}

	public void setType(PlayerDeviceType type) {
		Type = type;
	}

	
	
}
