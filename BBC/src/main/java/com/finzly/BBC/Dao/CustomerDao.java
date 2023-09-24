package com.finzly.BBC.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.finzly.BBC.Entity.Customer;

@Repository
public class CustomerDao {
@Autowired 
SessionFactory customerFactory;
public String insertCustomer (Customer customer) {
	Session session = customerFactory.openSession();
	session.save(customer);
	session.beginTransaction().commit();
	return "customer created Successfully!!";
}
public String updateCustomer(Customer customer) {
	Session session = customerFactory.openSession();
	session.update(customer);
	session.beginTransaction().commit();
	return "customer created Successfully!!";
}


}
