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
		if (contacts != null) {
			for(IContact contact : contacts) {
				intermediaryVector = new Vector<String>();
				intermediaryVector.add(String.valueOf(contact.getId()));
				intermediaryVector.add(contact.getName());
				intermediaryVector.add(contact.getPhonenumber());
				intermediaryVector.add(contact.getNotes());
				tableData.add(intermediaryVector);
			}
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
		refreshGUI();
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
	
	/**
	 * Refreshes the table with the locally stored contacts.
	 */
	public void refreshGUI() {
		updateTableData(convertToTableVector(contacts));
		gui.refreshTable();
	}

	/**
	 * Updates a contact in SQL and refreshes the table with the new information.
	 * @param contact The changed contact
	 * @param selectedRow The contacts row in the table
	 */
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
	
	/**
	 * Makes the GUI bring up a warning prompt when a contact is out of sync.
	 */
	public void outOfSyncWarning() {
		gui.outOfSyncWarning();
	}

	/**
	 * Searches for a contact by name and notes and refreshes table with the found information.
	 * @param searchparameter The parameter being looked for 
	 */
	public void searchContact(String searchparameter) {
		commandObject = new CommandObject("search contact", searchparameter);
		sendAndReceiveContacts();
	}

	/**
	 * Creates a new contact in SQL and in the locally stored list, the updates the table.
	 * @param contact The new contact
	 */
	public void newContact(Contact contact) {
		commandObject = new CommandObject("new contact", contact);
		int id = (Integer) serverConnection.sendAndGet(commandObject);
		contact.setId(id);
		contacts.add(contact);
		refreshGUI();
	}
	
	/**
	 * Sends out a command and receives a list of contacts back.
	 */
	@SuppressWarnings("unchecked")
	public void sendAndReceiveContacts() {
		contacts = (List<IContact>) serverConnection.sendAndGet(commandObject);
		updateTableData(convertToTableVector(contacts));
	}
	
	/**
	 * Sets the gui this controller will influence.
	 * @param gui The gui in question
	 */
	private void setGUI(SwingGUI gui) {
		this.gui = gui;
	}
	
	/**
	 * Checks the version of the contact being edited.
	 * @param contact The contact
	 * @return Boolean True if the contacts are in sync, false if they aren't
	 */
	public boolean checkVersion(IContact contact) {
		boolean congruent;
		CommandObject commandObject = new CommandObject("check version", contact);
		congruent = (Boolean) serverConnection.sendAndGet(commandObject);
		return congruent;
	}
	
	/**
	 * Returns a contact from the locally stored list.
	 * @param selectedRow The position of the contact in the list
	 * @return Contact being looked for
	 */
	public IContact getContact(int selectedRow) {
		IContact contact = contacts.get(selectedRow);
		return contact;
	}
}
