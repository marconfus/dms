package org.marconfus.dino.playground;

import org.marconfus.dino.threads.UpnpDiscoverThread;

public class UpnpPlay {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UpnpDiscoverThread udt = new UpnpDiscoverThread();
		udt.start();

	}

}
