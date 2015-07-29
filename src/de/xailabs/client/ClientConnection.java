package de.xailabs.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import de.xailabs.interfaces.IContact;

public class ClientConnection {
	private String hostName;
    private int portNumber;
    private Socket echoSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in; 
    
    public ClientConnection(String hostName, int portNumber) {
    	this.hostName = hostName;
    	this.portNumber = portNumber;
    }
    
    public void startConnection() {
	    try {
	        echoSocket = new Socket(hostName, portNumber);
	        out = new ObjectOutputStream(echoSocket.getOutputStream());
	        in = new ObjectInputStream(echoSocket.getInputStream());
	    	System.out.println("Connection established");
	    } catch (UnknownHostException e) {
	        System.err.println("Don't know about host " + hostName);
	        System.exit(1);
	    } catch (IOException e) {
	        System.err.println("Couldn't get I/O for the connection to " + hostName);
	        System.exit(1);
	    }
    }
    
	public Object sendAndGet(CommandObject commandObject) {
    	Object returnObject = null;
    	try {
			out.writeObject(commandObject);
			out.flush();
			returnObject = in.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
    	return returnObject;
    }
    
    public void sendCommand(CommandObject commandObject) {
    	try {
			out.writeObject(commandObject);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void closeConnection() {
    	try {
    		in.close();
    		out.close();
			echoSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
