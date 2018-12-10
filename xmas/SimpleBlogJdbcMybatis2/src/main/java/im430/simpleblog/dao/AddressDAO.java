package im430.simpleblog.dao;

import java.util.List;

import im430.simpleblog.business.Address;

public interface AddressDAO {

	public void addAddress(Address address);
	
	public Address getAddress(int id);
	
	public List<Address> getAllAddresses();
	
	public void removeAddress(Address address);
	
}
