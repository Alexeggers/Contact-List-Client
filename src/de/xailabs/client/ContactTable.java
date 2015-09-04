package de.xailabs.client;

import java.awt.Color;

import javax.swing.JTable;
import javax.swing.table.TableModel;

public class ContactTable extends JTable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4602221038896702806L;

	public ContactTable(TableModel model) {
		super(model);
		this.setSize(1500, 586);
		this.setBackground(Color.WHITE);
		this.setRowHeight(20);
		this.setOpaque(true);
		this.getColumnModel().getColumn(0).setMaxWidth(30);
	}
}