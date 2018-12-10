package im430.simpleblog.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import im430.simpleblog.business.Address;
import im430.simpleblog.dao.AddressDAO;

@Repository
@Transactional
public class MappedSqlSessionAddressDAOImpl implements AddressDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void addAddress(Address address) {
		sqlSession.insert(
				"im430.simpleblog.dao.mybatis.MappedSqlSessionAddressDAOImpl.addAddress",
				address);	
	}

	@Override
	@Transactional(readOnly=true)
	public Address getAddress(int id) {
		return sqlSession.selectOne(
				"im430.simpleblog.dao.mybatis.MappedSqlSessionAddressDAOImpl.getAddressById",
				id);	
	}

	@Override
	@Transactional(readOnly=true)
	public List<Address> getAllAddresses() {
		return sqlSession.selectList(
				"im430.simpleblog.dao.mybatis.MappedSqlSessionAddressDAOImpl.getAllAddresses");	
	}

	@Override
	public void removeAddress(Address address) {
		sqlSession.delete(
				"im430.simpleblog.dao.mybatis.MappedSqlSessionAddressDAOImpl.removeAddress",
				address);	
		address.setId(-1);
	}

}
