package com.finzly.BBC.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finzly.BBC.Dao.CustomerBillDao;
import com.finzly.BBC.Entity.Customer;
import com.finzly.BBC.Entity.CustomerBill;


@Service
public class CustomerBillService {
	@Autowired
	CustomerBillDao customerBillDao;

	public String generateNewBill(CustomerBill customerBill) {
		return customerBillDao.generateNewBill(customerBill);

	}

	public List<CustomerBill> getAllBill() {

		return customerBillDao.getAllBill();
	}

	public List<CustomerBill> getAllInvoice(int id) {
		List<CustomerBill> c = customerBillDao.getAllInvoice();
		List<CustomerBill> al = new ArrayList<>();

//	    try {
//	        for (CustomerBill cus : c) {
//	            if (cus.getBillId() == id) {
//	                al.add(cus);
//	            }
//	        }
//	        
//	        if (al.isEmpty()) {
//	           // throw new CustomBillIdNotFoundException("Bill ID not found: " + id);
//	        }
//	        
//	    } catch (CustomBillIdNotFoundException e) {
//	        // Handle the custom exception here
//	        // You can log the error, perform additional actions, or rethrow the exception as needed
//	        e.printStackTrace(); // Example: Print the stack trace
//	    }

		return al;
	}

	public List<CustomerBill> getPaymenyById(int id) {
		List<CustomerBill> list = customerBillDao.getAllInvoice();

		List<CustomerBill> newList = new ArrayList<>();
		for (CustomerBill tranction : list) {
			Customer customer = tranction.getCustomerId();
			if (customer.getCustomerId() == id && tranction.getStatus().equals("success")) {
				newList.add(tranction);
			}
		}
		return newList;

	}

	public List<CustomerBill> getAllPendingBills() {
		List<CustomerBill> list = customerBillDao.getAllPendingBills();
		List<CustomerBill> newList = new ArrayList<>();
		for (CustomerBill tranction : list) {
			if (tranction.getStatus().equals("pending")) {
				newList.add(tranction);
			}
		}
		return newList;
	}

	public String getPtos(int id) {
	List<CustomerBill>list=	customerBillDao.getAllPendingBills();
	for(CustomerBill customerBill : list) {
		Customer c = customerBill.getCustomerId();
		System.out.println(c.getCustomerId());
		System.out.println(customerBill.getStatus());
		if(c.getCustomerId()==id && customerBill.getStatus().equals("pending")) {
			customerBill.setStatus("success");
			return customerBillDao.getPtos(customerBill);
		}
		
	}
	return "No one";
	}

	public List<CustomerBill> getPaymentById() {
		List<CustomerBill> list=	customerBillDao.getAllBill();
		List<CustomerBill> list1=	new ArrayList<>();
		for(CustomerBill customerBill : list) {
			if(customerBill.getStatus().equals("success")) {
				list1.add(customerBill);
			}
			
		}
		return list1;
	}
}
