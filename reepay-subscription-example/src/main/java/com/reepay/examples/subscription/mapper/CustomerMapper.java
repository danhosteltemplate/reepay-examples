package com.reepay.examples.subscription.mapper;

import com.reepay.examples.subscription.model.Customer;
import com.reepay.examples.subscription.model.FormData;

/**
 * Created by mikkel on 11/04/2017.
 */
public class CustomerMapper {
    public static Customer mapCustomer(FormData formData){
        Customer customer = new Customer();

        if(formData.getPassword() != null) customer.setPassword(formData.getPassword());
        customer.setEmail(formData.getEmail());
        customer.setFirstName(formData.getFirstName());
        customer.setLastName(formData.getLastName());

        return customer;
    }
}
