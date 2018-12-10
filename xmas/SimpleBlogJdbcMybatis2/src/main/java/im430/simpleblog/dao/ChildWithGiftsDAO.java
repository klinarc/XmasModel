package im430.simpleblog.dao;

import im430.simpleblog.business.Child;
import im430.simpleblog.business.Gift;

public interface ChildWithGiftsDAO extends ChildDAO {
	
	public void addGift(Child child, Gift gift);
	public void removeGift(Child child, Gift gift);

}
