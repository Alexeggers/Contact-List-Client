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


public class SearchForContactWindow {

	private JTextField searchField;
	private Instruction instructions;
	
	public SearchForContactWindow(JFrame contactListWindow, Controller controller) {
		JFrame frame = new JFrame();
		frame.setLocationRelativeTo(contactListWindow);
		frame.setBounds(200, 200, 400, 200);
		JPanel textPanel = new JPanel(new FlowLayout());
		JPanel insPanel = new JPanel(new FlowLayout());
		JPanel buttonPanel = new JPanel();
		frame.add(BorderLayout.NORTH, insPanel);
		frame.add(BorderLayout.CENTER, textPanel);
		frame.add(BorderLayout.SOUTH, buttonPanel);
		JButton enter = new JButton("Enter");
		searchField = new JTextField(20);
		buttonPanel.add(enter);
		textPanel.add(searchField);
		instructions = new Instruction("Enter the name or note you wish to find.", 20);
		insPanel.add(instructions);
		insPanel.setBackground(Color.WHITE);
		PromptSupport.setPrompt("Search criterion", searchField);
		searchField.addKeyListener(new KeyListener() {
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
		
		enter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.searchContact(searchField.getText());
			}
		});
		
		buttonPanel.setBackground(Color.white);
		textPanel.setBackground(Color.white);
		frame.setVisible(true);
	}

}
