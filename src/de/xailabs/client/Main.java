package de.xailabs.client;

public class Main {

	public static void main(String[] args) {
		ServerConnection sc = new ServerConnection("127.0.0.1", 13337);
		sc.startConnection();
	}

}
