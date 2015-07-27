package de.xailabs.client;

import java.util.List;
import java.util.Vector;

import de.xailabs.interfaces.IContact;


public class Controller {

	private ServerConnection serverConnection;
	private CommandObject commandObject;
	private SwingGUI gui;
	private List<IContact> contacts;
	
	public void start() {
		serverConnection = new ServerConnection("127.0.0.1", 13337, this);
		serverConnection.startConnection();
		SwingGUI gui = new SwingGUI();
		setGUI(gui);
		gui.setController(this);
		viewAllContacts();
		gui.buildGUI();
	}
	
	private Vector<Vector<String>> convertToTableVector(List<IContact> contacts) {
		Vector<Vector<String>> tableData = new Vector<Vector<String>>();
		Vector<String> intermediaryVector;
		for(IContact contact : contacts) {
			intermediaryVector = new Vector<String>();
			intermediaryVector.add(String.valueOf(contact.getId()));
			intermediaryVector.add(contact.getName());
			intermediaryVector.add(contact.getPhonenumber());
			intermediaryVector.add(contact.getNotes());
			tableData.add(intermediaryVector);
		}
		return tableData;
	}

	public void deleteContact(IContact contact) {
		commandObject = new CommandObject("delete contact", contact);
		sendAndReceive();
	}
	
	public void viewAllContacts() {
		commandObject = new CommandObject("view all contacts");
		sendAndReceive();
	}

	private void updateTableData(Vector<Vector<String>> tableData) {
		gui.setTableData(tableData);
	}
	
	public void refreshGUI() {
		gui.refreshTable();
	}

	public void updateContact(Contact contact) {
		commandObject = new CommandObject("update contact", contact);
		sendAndReceive();
	}

	public void searchContact(String searchparameter) {
		commandObject = new CommandObject("search contact", searchparameter);
		sendAndReceive();
	}

	public void newContact(Contact contact) {
		commandObject = new CommandObject("new contact", contact);
		sendAndReceive();
	}
	
	public void sendAndReceive() {
		contacts = serverConnection.sendCommand(commandObject);
		updateTableData(convertToTableVector(contacts));
	}
	
	private void setGUI(SwingGUI gui) {
		this.gui = gui;
	}
}
