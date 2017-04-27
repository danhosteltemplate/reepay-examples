package com.reepay.examples.subscription.controllers;

import com.reepay.examples.subscription.mapper.SubscriptionMapper;
import com.reepay.examples.subscription.model.*;

import javax.ws.rs.core.Response;
import java.util.*;


/**
 * Created by mikkel on 03/04/2017.
 */
public class ReepayControllerImpl extends AbstractExamples implements ReepayController{

    @Override
    public Customer registerCustomerAndSubscription(FormData customer) {
        Response response = client.get("/v1/customer/" + customer.getHandle());
        Subscription subscription = new Subscription();

        if(response.getStatus() == 404) {
            SubscriptionDto sub = SubscriptionMapper.getSubscription(customer);
            subscription = readResponse(client.post("/v1/subscription", sub), Subscription.class);
        }
        return readResponse(client.get("/v1/customer/" + subscription.getCustomer()), Customer.class);
    }

    @Override
    public Customer getCustomer(String customerHandle) {
        return readResponse(client.get("/v1/customer/" + customerHandle), Customer.class);
    }

    @Override
    public <T> SubscriptionList getSubscriptionsForCustomer(String customerHandle) {
        Map<String, List<String>> params = new HashMap<>();
        List<String> searchParams = new ArrayList<>();
        searchParams.add("customer.handle:" + customerHandle);
        params.put("search", searchParams);
        return readResponse(client.get("/v1/subscription", params), SubscriptionList.class);
    }

    @Override
    public Subscription getSubscription(String handle){
        return readResponse(client.get("/v1/subscription/" + handle), Subscription.class);
    }

    @Override
    public Plan getPlan(String handle) {
        return readResponse(client.get("/v1/plan/" + handle + "/current"), Plan.class);
    }

    @Override
    public Subscription unsubscribe(String handle) {
        return readResponse(client.post("/v1/subscription/" + handle + "/cancel", null), Subscription.class);
    }

    @Override
    public Subscription resubscribe(String handle) {
        return readResponse(client.post("/v1/subscription/" + handle + "/uncancel", null), Subscription.class);
    }

    @Override
    public <T> InvoiceList getInvoicesForSubscription(String subscriptionHandle, String customerHandle) {
        Map<String, List<String>> params = new HashMap<>();
        List<String> searchParams = new ArrayList<>();
        searchParams.add("subscription.handle:" + subscriptionHandle);
        searchParams.add("customer.handle:" + customerHandle);
        params.put("search", searchParams);
        return readResponse(client.get("/v1/invoice", params), InvoiceList.class);
    }

    @Override
    public <T> CardList getCardsForCustomer(String customerHandle) {
        return readResponse(client.get("/v1/customer/" + customerHandle + "/payment_method"), CardList.class);
    }
    public Optional<Coupon> validateCoupon(Coupon coupon) {
        Map<String, List<String>> params = new HashMap<>();
        List<String> codeParams = new ArrayList<>();
        codeParams.add(coupon.getCode());

        List<String> planParams = new ArrayList<>();
        planParams.add(coupon.getPlan());

        List<String> customerParams = new ArrayList<>();
        customerParams.add(coupon.getCustomer());

        params.put("code", codeParams);
        params.put("plan", planParams);
        params.put("customer", customerParams);
        Response response = client.get("/v1/coupon/code/validate", params);

        if (response.getStatus() != 200){
            return Optional.ofNullable(coupon);
        }

        return Optional.of(readResponse(response, Coupon.class));
    }

    @Override
    public CouponRedemption redeemCoupon(Coupon coupon, String subscriptionHandle) {
        return readResponse(client.post("/v1/subscription/" + subscriptionHandle + "/coupon", coupon), CouponRedemption.class);
    }


}
