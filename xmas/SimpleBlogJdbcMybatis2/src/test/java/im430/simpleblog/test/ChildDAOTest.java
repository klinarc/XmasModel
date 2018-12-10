package im430.simpleblog.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import im430.simpleblog.business.Child;
import im430.simpleblog.business.Address;
import im430.simpleblog.business.Gift;
import im430.simpleblog.dao.ChildWithGiftsDAO;
import im430.simpleblog.dao.AddressDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("DAOTest-context.xml")
//@Transactional
public class ChildDAOTest {

	@Autowired
	private ChildWithGiftsDAO childDAO;
	
	@Autowired
	private AddressDAO addressDAO;
	
	@Test
	public void shouldAddAddressToChild() {
		Address a1 = new Address();
		a1.setText("TestText "+new Date());
		addressDAO.addAddress(a1);
		
		Child c1 = new Child();
		c1.setName("TestName "+new Date());
		c1.setAddress(a1);
		
		childDAO.addChild(c1);
		Child c2 = childDAO.getChild(c1.getId());
		
		assertEquals(c1.getAddress(), c2.getAddress());
	}
	
	@Test
	public void shouldAddRemoveGiftToFromChild() {
		Address a1 = new Address();
		a1.setText("TestText "+new Date());
		addressDAO.addAddress(a1);
		
		Child c1 = new Child();
		c1.setName("TestName "+new Date());
		c1.setAddress(a1);
		childDAO.addChild(c1);
		
		Gift g1= new Gift();
		g1.setDescription("TestDescription "+new Date());
		
		int len1= c1.getGifts().size();
		childDAO.addGift(c1,g1);
		
		assertEquals(len1+1, c1.getGifts().size());
		assertEquals(len1+1, childDAO.getChild(c1.getId()).getGifts().size());
		
		childDAO.removeGift(c1, g1);
		assertEquals(len1, c1.getGifts().size());
		assertEquals(len1, childDAO.getChild(c1.getId()).getGifts().size());
	}

}
