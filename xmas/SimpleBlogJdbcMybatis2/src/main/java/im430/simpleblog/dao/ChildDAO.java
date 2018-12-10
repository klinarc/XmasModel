package im430.simpleblog.dao;

import java.util.List;

import im430.simpleblog.business.Child;

public interface ChildDAO {

	public void addChild(Child child);

	public Child getChild(int id);

	public List<Child> getAllChildren();

	public void updateChild(Child child);

	public void removeChild(Child child);
}
