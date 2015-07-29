package de.xailabs.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class SwingGUI {

	private JFrame contactListWindow;

	private JPanel buttonPanel;
	private JPanel tablePanel;

	private JButton deleteContactButton;
	private JButton newContactButton;
	private JButton searchForContactButton;
	private JButton updateContactButton;
	private JButton viewAllContactsButton;

	private ContactTable contactTable;

	private static Vector<String> columnNames;
	private Vector<Vector<String>> tableData;

	private ClientController controller;

	static {
		columnNames = new Vector<String>();
		columnNames.add("ID");
		columnNames.add("Name");
		columnNames.add("Phone Number");
		columnNames.add("Notes");
	}

	/**
	 * Builds the GUI
	 */
	public void buildGUI() {
		contactListWindow = new JFrame("Contact List");
		contactListWindow.setLocationRelativeTo(null);
		contactListWindow.setBackground(Color.WHITE);
		contactListWindow.setBounds(0, 0, 1024, 768);

		buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.setBackground(Color.WHITE);
		tablePanel = new JPanel();
		tablePanel.setBackground(Color.WHITE);
		
		buildButtons();

		buttonPanel.add(newContactButton);
		buttonPanel.add(searchForContactButton);
		buttonPanel.add(updateContactButton);
		buttonPanel.add(deleteContactButton);
		buttonPanel.add(viewAllContactsButton);

		refreshTable();

		contactListWindow.add(BorderLayout.NORTH, buttonPanel);
		contactListWindow.add(BorderLayout.CENTER, tablePanel);

		contactListWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contactListWindow.setVisible(true);
	}

	/**
	 * Redraws the table with (presumably) new information.
	 */
	public void refreshTable() {
		tablePanel.removeAll();
		tablePanel.revalidate();
		TableModel contactModel = new DefaultTableModel(tableData, columnNames);
		contactTable = new ContactTable(contactModel);
		JScrollPane tableContainer = new JScrollPane(contactTable);
		tablePanel.add(tableContainer);
	}
	
	/**
	 * Sets this GUI's controller to the one provided.
	 * @param controller
	 */
	public void setController(ClientController controller) {
		this.controller = controller;
	}

	/**
	 * Sets up the buttons used in the GUI.
	 */
	public void buildButtons() {
		deleteContactButton = new JButton("Delete Contact");
		deleteContactButton.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (contactTable.getSelectedRow() != -1) {
					controller.deleteContact(contactTable.getSelectedRow());
					refreshTable();
				}
			}
		});
		newContactButton = new JButton("New Contact");
		newContactButton.addActionListener(new NewContactSubclass());

		searchForContactButton = new JButton("Search");
		searchForContactButton.addActionListener(new SearchForContactSubclass());

		updateContactButton = new JButton("Update Contact");
		updateContactButton.addActionListener(new UpdateContactSubclass());

		viewAllContactsButton = new JButton("View all Contacts/Refresh Table");
		viewAllContactsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.viewAllContacts();
				refreshTable();
			}
		});
	}
	
	/**
	 * Calls up a dialog window that warns the user that the contact he wants to update is out of sync.
	 */
	public void outOfSyncWarning() {
		JOptionPane.showMessageDialog(contactListWindow, "List out of sync and will be updated, please try again");
	}

	private class NewContactSubclass implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			@SuppressWarnings("unused")
			NewContactWindow newContactWindow = new NewContactWindow(contactListWindow, controller);
		}
	}

	private class SearchForContactSubclass implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			@SuppressWarnings("unused")
			SearchForContactWindow searchWindow = new SearchForContactWindow(contactListWindow, controller);
		}
	}

	private class UpdateContactSubclass implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (contactTable.getSelectedRow() != -1) {
				@SuppressWarnings("unused")
				UpdateContactWindow updateWindow = new UpdateContactWindow(contactListWindow, controller, contactTable.getSelectedRow());
			}
		}
	}

	/**
	 * Sets this GUI's tabledata to the one provided.
	 * @param tableData Vector<Vector<String>> format tabledata
	 */
	public void setTableData(Vector<Vector<String>> tableData) {
		this.tableData = tableData;
	}
}
