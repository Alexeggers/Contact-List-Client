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

public class NewContactWindow {
	private JTextField nameField;
	private JTextField numberField;
	private JTextField notesField;
	private JFrame frame;
	private Instruction instructions;
	private Contact contact;
	
	public NewContactWindow(JFrame superFrame, ClientController controller) {
		frame = new JFrame("New Contact");
		instructions = new Instruction("Enter new contact information", 20);
		frame.setLocationRelativeTo(superFrame);
		frame.setBounds(200, 200, 400, 200);
		JPanel textPanel = new JPanel(new FlowLayout());
		JPanel buttonPanel = new JPanel();
		JPanel insPanel = new JPanel();
		insPanel.add(instructions);
		insPanel.setBackground(Color.WHITE);
		frame.add(BorderLayout.NORTH, insPanel);
		frame.add(BorderLayout.CENTER, textPanel);
		frame.add(BorderLayout.SOUTH, buttonPanel);
		JButton enter = new JButton("Enter");
		
		nameField = new JTextField(20);
		numberField = new JTextField(20);
		notesField = new JTextField(20);
		
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
				contact = new Contact(name, number, notes);
				controller.newContact(contact);
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
		
		buttonPanel.setBackground(Color.white);
		textPanel.setBackground(Color.white);
		frame.setVisible(true);
	}
	
	public Contact getContact() {
		return contact;
	}
}
