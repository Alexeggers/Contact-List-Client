package de.xailabs.client;

import java.io.Serializable;

import de.xailabs.interfaces.IContact;

public class Contact implements IContact, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -336806449655714987L;
	private int id;
	private String name;
	private String phonenumber;
	private String notes;
	private int version = 1;
	
	public Contact() {
		
	}
	
	public Contact(String name, String phonenumber, String notes) {
		if (name.equals("")) {
			this.name = "defaultname";
		} else {
			this.name = name;
		}
		if (phonenumber.equals("")) {
			this.phonenumber = "000-0000 00 00";
		} else {
			this.phonenumber = phonenumber;
		}
		if (notes.equals("")) {
			this.notes = "defaultnotes";
		} else {
			this.notes = notes;
		}
	}
	
	public Contact(int id, String name, String phonenumber, String notes, int version) {
		this(name, phonenumber, notes);
		this.id = id;
		this.version = version;
	}
	
	
	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	@Override
	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getPhonenumber() {
		return phonenumber;
	}

	@Override
	public String getNotes() {
		return notes;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public int getVersion() {
		return version;
	}
	
	@Override
	public void incrementVersion() {
		this.version += 1;
	}
	
}
