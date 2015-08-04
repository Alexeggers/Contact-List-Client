package de.xailabs.client;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Addresses")
@NamedQueries({
	@NamedQuery(name="address.getAll", query="SELECT a from Address a")
})
public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -770903418577291957L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String street;
	private String housenumber;
	@OneToMany(mappedBy="contact")
	private Contact contact;
	private int version;
	
	public Address() {

	}	
	
	
	public Address(String street, String housenumber) {
		this.street = street;
		this.housenumber = housenumber;
		this.setVersion(1);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getHousenumber() {
		return housenumber;
	}
	
	public void setHousenumber(String housenumber) {
		this.housenumber = housenumber;
	}
	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public void incrementVersion() {
		this.setVersion(this.getVersion() + 1);
	}


	public Contact getContact() {
		return contact;
	}


	public void setContact(Contact contact) {
		this.contact = contact;
	}
}
