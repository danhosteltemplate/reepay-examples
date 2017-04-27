package com.reepay.examples.subscription.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mikkel on 19/04/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CouponRedemption {
    private Coupon coupon;

    @JsonProperty("subscription_discount")
    private SubscriptionDiscount discount;

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public SubscriptionDiscount getDiscount() {
        return discount;
    }

    public void setDiscount(SubscriptionDiscount discount) {
        this.discount = discount;
    }
}
