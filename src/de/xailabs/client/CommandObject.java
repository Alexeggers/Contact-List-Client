package de.xailabs.client;

import java.io.Serializable;

import de.xailabs.interfaces.ICommandObject;
import de.xailabs.interfaces.IContact;

public class CommandObject implements ICommandObject, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1117449400457826005L;
	private String command;
	private IContact contact;
	private String searchparameter;
	
	public CommandObject(String command, IContact contact) {
		this.command = command;
		this.contact = contact;
	}
	
	public CommandObject(String command, String searchparameter) {
		this(command);
		this.searchparameter = searchparameter;
	}
	
	public CommandObject(String command) {
		this.command = command;
		this.contact = null;
	}
	
	@Override
	public void setCommand(String command) {
		this.command = command;
	}
	
	@Override
	public String getCommand() {
		return command;
	}
	
	@Override
	public void setContact(IContact contact) {
		this.contact = contact;
	}

	@Override
	public IContact getContact() {
		return contact;
	}

	@Override
	public void setSearchparameter(String searchparameter) {
		this.searchparameter = searchparameter;
	}

	@Override
	public String getSearchparameter() {
		return searchparameter;
	}
	
	
}
