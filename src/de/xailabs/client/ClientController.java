package de.xailabs.client;

import java.util.List;
import java.util.Vector;

import de.xailabs.interfaces.IContact;


public class ClientController {

	private ClientConnection serverConnection;
	private CommandObject commandObject;
	private SwingGUI gui;
	private List<IContact> contacts;
	
	public void start() {
		serverConnection = new ClientConnection("127.0.0.1", 13337);
		serverConnection.startConnection();
		SwingGUI gui = new SwingGUI();
		setGUI(gui);
		gui.setController(this);
		viewAllContacts();
		gui.buildGUI();
	}
	
	/**
	 * Converts a list of Contacts into a vector that can be used to create a JTable.
	 * @param contacts List of contacts.
	 * @return Vector to be used in creating a JTable.
	 */
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

	/**
	 * Deletes a contact in SQL and sets new tableData.
	 * @param contact Contact to be deleted.
	 */
	public void deleteContact(int selectedRow) {
		IContact contact = getContact(selectedRow);
		commandObject = new CommandObject("delete contact", contact);
		serverConnection.sendCommand(commandObject);
		contacts.remove(selectedRow);
	}
	
	/**
	 * Updates tableData to correspond to current state of SQL table.
	 */
	public void viewAllContacts() {
		commandObject = new CommandObject("view all contacts");
		sendAndReceiveContacts();
	}
	
	/**
	 * Set tableData in gui
	 * @param tableData
	 */
	private void updateTableData(Vector<Vector<String>> tableData) {
		gui.setTableData(tableData);
	}
	
	public void refreshGUI() {
		gui.refreshTable();
	}

	public void updateContact(Contact contact, int selectedRow) {
		if(checkVersion(contact)) {
			commandObject = new CommandObject("update contact", contact);
			serverConnection.sendCommand(commandObject);
			contacts.set(selectedRow, contact);
			contacts.get(selectedRow).incrementVersion();
			updateTableData(convertToTableVector(contacts));
		} else {
			commandObject = new CommandObject("view all contacts");
			sendAndReceiveContacts();
		}
	}
	
	public void outOfSyncWarning() {
		gui.outOfSyncWarning();
	}

	public void searchContact(String searchparameter) {
		commandObject = new CommandObject("search contact", searchparameter);
		sendAndReceiveContacts();
	}

	public void newContact(Contact contact) {
		commandObject = new CommandObject("new contact", contact);
		sendAndReceiveContacts();
	}
	
	@SuppressWarnings("unchecked")
	public void sendAndReceiveContacts() {
		contacts = (List<IContact>) serverConnection.sendAndGet(commandObject);
		updateTableData(convertToTableVector(contacts));
	}
	
	private void setGUI(SwingGUI gui) {
		this.gui = gui;
	}
	
	public boolean checkVersion(IContact contact) {
		boolean congruent;
		CommandObject commandObject = new CommandObject("check version", contact);
		congruent = (Boolean) serverConnection.sendAndGet(commandObject);
		return congruent;
	}
	
	public IContact getContact(int selectedRow) {
		IContact contact = contacts.get(selectedRow);
		return contact;
	}
}
