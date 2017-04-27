package com.reepay.examples.subscription.controllers;

import com.reepay.examples.subscription.model.*;

import java.util.Optional;

/**
 * Created by mikkel on 03/04/2017.
 */
public interface ReepayController {
    Customer registerCustomerAndSubscription(FormData customer);
    Customer getCustomer(String customerHandle);
    <T> SubscriptionList getSubscriptionsForCustomer(String customerHandle);
    Subscription getSubscription(String handle);
    Plan getPlan(String handle);
    Subscription unsubscribe(String handle);
    Subscription resubscribe(String handle);

    <T> InvoiceList getInvoicesForSubscription(String subscriptionHandle, String customerHandle);
    <T> CardList getCardsForCustomer(String customerHandle);

    Optional<Coupon> validateCoupon(Coupon coupon);
    CouponRedemption redeemCoupon(Coupon coupon, String subscriptionHandle);

}
