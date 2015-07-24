package de.xailabs.client;

import java.awt.Color;

import javax.swing.JTextField;

public class Instruction extends JTextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2960420210005630251L;

	public Instruction (String text, int columns) {
		super(text, columns);
		this.setEditable(false);
		this.setBorder(null);
		this.setBackground(Color.WHITE);
		
	}
	public Instruction (String text) {
		super(text);
		this.setEditable(false);
		this.setBorder(null);
		this.setBackground(Color.WHITE);
	}
}
