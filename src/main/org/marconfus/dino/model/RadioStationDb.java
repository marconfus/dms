package org.marconfus.dino.model;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.log4j.Logger;

public class RadioStationDb {

	private ArrayList<RadioStation> stations;
	public static RadioStationDb Database = null;
	final static Logger logger = Logger.getLogger(RadioStationDb.class);

	public static RadioStationDb getInstance() {
		if (Database != null) {
			return (Database);
		} else {
			Database = new RadioStationDb();
			return (Database);
		}
	}

	public void readStreamsFile() {
		InputStream fstream = this.getClass().getClassLoader()
				.getResourceAsStream("streams");

		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		try {
			while ((strLine = br.readLine()) != null) {
				try {
					String name = strLine.split(";")[0];
					String url = strLine.split(";")[1];
					String ID =  strLine.split(";")[3];
					RadioStation r = new RadioStation(ID, name);
					r.addURL(url);
					this.addStation(r);
					logger.info(name + " " + url);
				} catch (ArrayIndexOutOfBoundsException e) {

				}
			}
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public RadioStationDb() {
		stations = new ArrayList<RadioStation>();
	}

	public ArrayList<RadioStation> getStations() {
		return (stations);
	}

	public void addStation(RadioStation station) {
		stations.add(station);
	}

	public RadioStation findById(String id) {
		
		RadioStation station = null;
		for (RadioStation s : stations) {
			if (s.getID().equals(id)) {
				station = s;
			}
		}
		return station;
	}

}
