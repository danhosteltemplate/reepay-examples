package com.reepay.examples.api;

import com.google.common.collect.Maps;
import com.reepay.examples.api.model.AbstractExamples;
import com.reepay.examples.api.model.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SubscriptionExamples extends AbstractExamples implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(SubscriptionExamples.class);

    @Override
    public void run() {
        // Create a recurring token directly with the Reepay card API
        // WARNING: This should always be done using Reepay Token or Reepay.js in non-test cases.
        String token = createToken("4111111111111111", "999", 12, 20, true);

        // Make sure there is a plan called gold
        Map<String, Object> params = Maps.newHashMap();
        params.put("handle", "gold");
        params.put("amount", 40000);
        params.put("name", "Gold subscription");
        params.put("schedule_type", "manual");
        client.post("/v1/plan", params);

        // Create customer and subscription
        Map<String, Object> customer = Maps.newHashMap();
        customer.put("generate_handle", true);
        customer.put("email", "reepay-api-examples@mailinator.com");
        Map<String, Object> subscription = Maps.newHashMap();
        subscription.put("generate_handle", true);
        subscription.put("create_customer", customer);
        subscription.put("signup_method", "source");
        subscription.put("source", token);
        subscription.put("plan", "gold");

        Subscription sub = readResponse(client.post("/v1/subscription", subscription), Subscription.class);
        if (!sub.getState().equals("active")) {
            LOG.error("Subscription not active");
            System.exit(-1);
        }

    }
}
