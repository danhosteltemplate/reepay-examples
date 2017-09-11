package com.reepay.examples.api;

import com.google.common.collect.Maps;
import com.reepay.examples.api.model.AbstractExamples;
import com.reepay.examples.api.model.Charge;
import com.reepay.examples.api.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.UUID;

@Component
public class ChargeExamples extends AbstractExamples implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(ChargeExamples.class);

    @Override
    public void run() {
        // Get or create customer with handle 007
        Response response = client.get("/v1/customer/007");
        Customer customer;
        if (response.getStatus() == 404) {
            // Create customer
            customer = new Customer();
            customer.setHandle("007");
            customer.setEmail("reepay-api-examples@mailinator.com");
            customer.setFirstName("James");
            response = client.post("/v1/customer", customer);
        }
        customer = readResponse(response, Customer.class);
        
        // Create a non-recurring token directly with the Reepay card API
        // WARNING: This should always be done using Reepay Token or Reepay.js in non-test cases.
        String token = createToken("4111111111111111", "999", 12, 20, false);
        
        // Create a settled charge
        Map<String, Object> params = Maps.newHashMap();
        params.put("handle", UUID.randomUUID().toString());
        params.put("customer_handle", customer.getHandle());
        params.put("amount", 20000);
        params.put("source", token);
        Charge charge = readResponse(client.post("/v1/charge", params), Charge.class);
        if (!charge.getState().equals("settled")) {
            LOG.error("Charge not settled");
            System.exit(-1);
        }
        
        // Create an authorized charge
        token = createToken("4111111111111111", "999", 12, 20, false);
        params = Maps.newHashMap();
        params.put("handle", UUID.randomUUID().toString());
        params.put("customer_handle", customer.getHandle());
        params.put("amount", 20000);
        params.put("settle", false);
        params.put("source", token);
        charge = readResponse(client.post("/v1/charge", params), Charge.class);
        if (!charge.getState().equals("authorized")) {
            LOG.error("Charge not authorized");
            System.exit(-1);
        }
        
        // Settle authorization
        charge = readResponse(client.post("/v1/charge/" + params.get("handle") + "/settle", null), Charge.class);
        if (!charge.getState().equals("settled")) {
            LOG.error("Charge not settled");
            System.exit(-1);
        }
        
    }
}
