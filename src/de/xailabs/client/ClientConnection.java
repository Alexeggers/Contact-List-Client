package de.xailabs.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

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
    
    /**
     * Starts the connection with the server.
     */
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
    
    /**
     * Sends out a command and receives an object back.
     * @param commandObject The object containing the command and, depending on the command, a contact or search parameter
     * @return An object that will be handled by the method calling sendAndGet
     */
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
    
	/**
	 * Sends out a command to the server.
	 * @param commandObject The object containing the command and, depending on the command, a contact or search parameter
	 */
    public void sendCommand(CommandObject commandObject) {
    	try {
			out.writeObject(commandObject);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    /**
     * Closes the connection to the server.
     */
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
