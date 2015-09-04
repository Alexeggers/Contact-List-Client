package de.xailabs.client;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import de.xailabs.interfaces.IAddress;

@Entity
@Table(name="Addresses")
@NamedQueries({
	@NamedQuery(name="address.findStreet", query="SELECT a FROM Address a WHERE a.street LIKE ?1 AND a.housenumber LIKE ?2"),
})
public class Address implements Serializable, IAddress{

	/**
	 * 
	 */
	private static final long serialVersionUID = -770903418577291957L;
	@Id
	@Column(name="ADDRESS_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String street;
	private String housenumber;
	
	public Address() {
		
	}	
	
	public Address(String street, String housenumber) {
		this.street = street;
		this.housenumber = housenumber;
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
}