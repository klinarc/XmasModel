package im430.simpleblog.dao.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import im430.simpleblog.business.Child;
import im430.simpleblog.business.Gift;
import im430.simpleblog.dao.ChildWithGiftsDAO;

@Resource
@Transactional
public class MappedSqlSessionChildDAOImpl implements ChildWithGiftsDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void addGift(Child child, Gift gift) {
		
		Map<String, Object> sqlParameters = new HashMap<String, Object>();
		sqlParameters.put("gift", gift);
		sqlParameters.put("childId", child.getId());
		
		sqlSession.insert(
				"im430.simpleblog.dao.mybatis.MappedSqlSessionChildDAOImpl.addGift",
				sqlParameters);	
		
		child.getGifts().add(gift);
		
	}
	

	@Override
	public void removeGift(Child child, Gift gift) {
		sqlSession.delete(
				"im430.simpleblog.dao.mybatis.MappedSqlSessionChildDAOImpl.removeGift",
				gift);		
		child.getGifts().remove(gift);
		gift.setId(-1);
	}



	@Override
	public void addChild(Child child) {
		sqlSession.insert(
		"im430.simpleblog.dao.mybatis.MappedSqlSessionChildDAOImpl.addChild",
		child);	
	}

	@Override
	@Transactional(readOnly=true)
	public Child getChild(int id) {
		return sqlSession.selectOne(
				//"im430.simpleblog.dao.mybatis.MappedSqlSessionChildDAOImpl.getChildById",
				"im430.simpleblog.dao.mybatis.MappedSqlSessionChildDAOImpl.joinedGetChildById",
				id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Child> getAllChildren() {
		return sqlSession.selectList(
				//"im430.simpleblog.dao.mybatis.MappedSqlSessionChildDAOImpl.getAllChildren");
				"im430.simpleblog.dao.mybatis.MappedSqlSessionChildDAOImpl.joinedGetAllChildren");
	}

	@Override
	public void updateChild(Child child) {
		sqlSession.update(
				"im430.simpleblog.dao.mybatis.MappedSqlSessionChildDAOImpl.updateChild",
				child);	

	}

	@Override
	public void removeChild(Child child) {
		sqlSession.delete(
				"im430.simpleblog.dao.mybatis.MappedSqlSessionChildDAOImpl.removeChild",
				child);	
		child.setId(-1);
	}

}
