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
    List<IContact> contacts;
    
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
    
    @SuppressWarnings("unchecked")
	public List<IContact> sendAndGet(CommandObject commandObject) {
    	try {
			out.writeObject(commandObject);
			out.flush();
			contacts = (List<IContact>) in.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
    	return contacts;
    }
    
    public void sendCommand(CommandObject commandObject) {
    	try {
			out.writeObject(commandObject);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public boolean checkVersion(CommandObject commandObject) {
    	boolean congruent = false;
    	try {
    		out.writeObject(commandObject);
    		out.flush();
    		congruent = (Boolean) in.readObject();
    	} catch (IOException e) {
    		e.printStackTrace();
    	} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	return congruent;
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
