package com.finzly.BBC.Dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.finzly.BBC.Entity.Customer;
import com.finzly.BBC.Entity.CustomerBill;

@Repository
public class BulkUserRepository {
	@Autowired
	SessionFactory factory;

	public List<Customer> UploadData(List<CustomerBill> customerBillList) {
		Session empSession = factory.openSession()	;
		Criteria criteria = empSession.createCriteria(Customer.class);
			return criteria.list() ;
		
	}

}
