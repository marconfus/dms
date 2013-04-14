package org.marconfus.dino.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.apache.http.client.ClientProtocolException;
import org.marconfus.dino.misc.StreamHelper;

public class RadioStation {

	String Name;
	String ID;
	String Genre;
	
	ArrayList<String> URLs;

	
	public RadioStation(String name) {
		super();
		Name = name;
		
		UUID uuid = UUID.randomUUID();
		ID = uuid.toString();
		URLs = new ArrayList<String>();
	}

	public RadioStation(String id, String name) {
		super();
		Name = name;
		ID = id;
		URLs = new ArrayList<String>();	}

	public void addURL(String url){
		URLs.add(url);
	}
	
	public String getPrimaryURL(){
		if (URLs.size() > 0) {
			return (URLs.get(0));
		} else {
			return (null);
		}
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

	public String getGenre() {
		return Genre;
	}

	public void setGenre(String genre) {
		Genre = genre;
	}

	public String getStreamURL() throws ClientProtocolException, IOException {
		return (StreamHelper.parseURL(getPrimaryURL()));
	}
	
}
