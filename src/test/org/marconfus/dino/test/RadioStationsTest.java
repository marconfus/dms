package org.marconfus.dino.test;

import org.junit.Test;
import org.marconfus.dino.model.RadioStation;
import org.marconfus.dino.model.RadioStationDb;

public class RadioStationsTest {

	@Test
	public void testReadStreamsFile() {
		RadioStationDb rsdb = RadioStationDb.getInstance();
		
		rsdb.readStreamsFile();
		for (RadioStation r : rsdb.getStations()){
			System.out.println(r.getName() + " " + r.getPrimaryURL());
		}
	}

}
