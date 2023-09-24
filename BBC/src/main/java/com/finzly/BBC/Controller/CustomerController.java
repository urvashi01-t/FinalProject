package com.finzly.BBC.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.finzly.BBC.Entity.Customer;
import com.finzly.BBC.Service.CustomerService;

@RestController
public class CustomerController {
@Autowired 
CustomerService customerService;
@PostMapping("insertCustomer")
public String insertCustomer(@RequestBody Customer customer) {
	return customerService.insertCustomer(customer);
}

@PutMapping("updateUser")
public String updateCustomer(@RequestBody Customer customer ) {
	return customerService.updateCustomer(customer);

}





}
