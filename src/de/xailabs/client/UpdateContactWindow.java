package de.xailabs.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdesktop.xswingx.PromptSupport;


public class UpdateContactWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6397653614766456939L;
	private int id;
	private String defaultName;
	private String defaultNumber;
	private String defaultNotes;
	private JFrame frame;
	private JTextField nameField;
	private JTextField numberField;
	private JTextField notesField;
	
	public UpdateContactWindow(JFrame contactListWindow, Controller controller, int selectedRow, Vector<Vector<String>> tableData) {
		Vector<String> dataVector = tableData.get(selectedRow);
		id = Integer.parseInt(dataVector.get(0));
		defaultName = dataVector.get(1);
		defaultNumber = dataVector.get(2);
		defaultNotes = dataVector.get(3);
		frame = new JFrame("Update Contact");
		frame.setLocationRelativeTo(contactListWindow);
		frame.setBounds(200, 200, 400, 200);
		JPanel textPanel = new JPanel(new FlowLayout());
		JPanel buttonPanel = new JPanel();
		frame.add(BorderLayout.CENTER, textPanel);
		frame.add(BorderLayout.SOUTH, buttonPanel);
		JButton enter = new JButton("Enter");
		nameField = new JTextField(defaultName, 20);
		numberField = new JTextField(defaultNumber, 20);
		notesField = new JTextField(defaultNotes, 20);
		
		buttonPanel.add(enter);
		textPanel.add(nameField);
		textPanel.add(numberField);
		textPanel.add(notesField);
		
		PromptSupport.setPrompt("Name", nameField);
		PromptSupport.setPrompt("Phonenumber", numberField);
		PromptSupport.setPrompt("Notes", notesField);
		
		enter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String number = numberField.getText();
				String notes = notesField.getText();
				Contact contact = new Contact(id, name, number, notes);
				controller.updateContact(contact);
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
		
		buttonPanel.setBackground(Color.white);
		textPanel.setBackground(Color.white);
		frame.setVisible(true);
	}

}
