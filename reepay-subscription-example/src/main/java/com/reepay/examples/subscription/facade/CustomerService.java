package com.reepay.examples.subscription.facade;

import com.reepay.examples.subscription.model.Customer;

import java.util.List;

/**
 * Created by mikkel on 27/03/2017.
 */
public interface CustomerService {
    Customer create(Customer customer);
    Customer getByemail(String email);
    List<Customer> getAll();
    int updateHandle(Long id, String handle);
}
