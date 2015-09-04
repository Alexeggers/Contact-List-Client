package de.xailabs.client;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import de.xailabs.interfaces.IAddress;
import de.xailabs.interfaces.IContact;

@Entity
@Table(name="Contacts")
@NamedQueries({
	@NamedQuery(name="getAllContacts", query="SELECT c FROM Contact c"),
	@NamedQuery(name="getMaxID", query="Select max(c.id) from Contact c"),
	@NamedQuery(name="searchForContact", query="SELECT c FROM Contact c WHERE c.name LIKE ?1 OR c.notes LIKE ?1")
})
public class Contact implements IContact, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -336806449655714987L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CONTACT_ID")
	private int id;
	private String name;
	private String phonenumber;
	private String notes;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
		      name="CONTACT_ADDRESS",
		      joinColumns={ @JoinColumn(name="C_ID", referencedColumnName="CONTACT_ID") },
		      inverseJoinColumns={ @JoinColumn(name="A_ID", referencedColumnName="ADDRESS_ID") }
		  )
	private Address address;
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

	@Override
	public Address getAddress() {
		return address;
	}

	@Override
	public void setAddress(IAddress address) {
		this.address = (Address) address;
	}
	
}
