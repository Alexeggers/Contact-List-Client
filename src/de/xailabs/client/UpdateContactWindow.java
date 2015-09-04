package de.xailabs.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdesktop.xswingx.PromptSupport;

import de.xailabs.interfaces.IAddress;
import de.xailabs.interfaces.IContact;


public class UpdateContactWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6397653614766456939L;
	private int id;
	private String defaultName;
	private String defaultNumber;
	private String defaultNotes;
	private String defaultStreet;
	private String defaultHousenumber;
	private JFrame frame;
	private JTextField nameField;
	private JTextField numberField;
	private JTextField notesField;
	private JTextField streetField;
	private JTextField housenumberField;
	private IContact contact;
	
	public UpdateContactWindow(JFrame contactListWindow, ClientController controller, int selectedRow) {
		contact = controller.getContact(selectedRow);
		if (controller.checkVersion(contact) == false) {
			contact = controller.getDBContact(contact);
			controller.outOfSyncWarning();
		}
		id = contact.getId();
		defaultName = contact.getName();
		defaultNumber = contact.getPhonenumber();
		defaultNotes = contact.getNotes();
		defaultStreet = contact.getAddress().getStreet();
		defaultHousenumber = contact.getAddress().getHousenumber();
		frame = new JFrame("Update Contact");
		frame.setLocationRelativeTo(contactListWindow);
		frame.setBounds(200, 200, 400, 400);
		JPanel textPanel = new JPanel(new FlowLayout());
		JPanel buttonPanel = new JPanel();
		frame.add(BorderLayout.CENTER, textPanel);
		frame.add(BorderLayout.SOUTH, buttonPanel);
		JButton enter = new JButton("Enter");
		nameField = new JTextField(defaultName, 20);
		numberField = new JTextField(defaultNumber, 20);
		notesField = new JTextField(defaultNotes, 20);
		streetField = new JTextField(defaultStreet, 20);
		housenumberField = new JTextField(defaultHousenumber, 20);
		
		buttonPanel.add(enter);
		textPanel.add(nameField);
		textPanel.add(numberField);
		textPanel.add(notesField);
		textPanel.add(streetField);
		textPanel.add(housenumberField);
		
		PromptSupport.setPrompt("Name", nameField);
		PromptSupport.setPrompt("Phonenumber", numberField);
		PromptSupport.setPrompt("Notes", notesField);
		PromptSupport.setPrompt("Street", streetField);
		PromptSupport.setPrompt("Housenumber", housenumberField);
		
		enter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String number = numberField.getText();
				String notes = notesField.getText();
				String street = streetField.getText();
				String housenumber = housenumberField.getText();
				IAddress address = new Address(street, housenumber);
				Contact updatedContact = new Contact(id, name, number, notes, contact.getVersion());
				updatedContact.setAddress(address);
				controller.updateContact(updatedContact, selectedRow);
				controller.refreshGUI();
				frame.setVisible(false);
				frame.dispose();
			}
		});
		
		nameField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char key = e.getKeyChar();
				if (key == '\n'){
					enter.doClick();
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		
		numberField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char key = e.getKeyChar();
				if (key == '\n'){
					enter.doClick();
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		
		notesField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char key = e.getKeyChar();
				if (key == '\n'){
					enter.doClick();
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		
		streetField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char key = e.getKeyChar();
				if (key == '\n'){
					enter.doClick();
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		
		housenumberField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char key = e.getKeyChar();
				if (key == '\n'){
					enter.doClick();
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		
		buttonPanel.setBackground(Color.white);
		textPanel.setBackground(Color.white);
		frame.setVisible(true);
	}
}
