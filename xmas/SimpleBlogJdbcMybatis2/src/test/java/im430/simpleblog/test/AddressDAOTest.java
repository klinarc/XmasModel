package im430.simpleblog.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import im430.simpleblog.business.Address;
import im430.simpleblog.dao.AddressDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("DAOTest-context.xml")
@Transactional
public class AddressDAOTest {

	@Autowired
	private AddressDAO addressDAO;
	
	@Test
	public void testAddressDAO() {
		
		Address a1 = new Address();
		a1.setText("TestText "+new Date());
		
		int len1 = addressDAO.getAllAddresses().size();
		addressDAO.addAddress(a1);
		assertEquals(len1+1, addressDAO.getAllAddresses().size());
		
		Address a2 = addressDAO.getAddress(a1.getId());
		assertEquals(a1.getText(), a2.getText());
		assertEquals(a1, a2);
		
		addressDAO.removeAddress(a1);
		assertEquals(len1, addressDAO.getAllAddresses().size());
	}
	
}
