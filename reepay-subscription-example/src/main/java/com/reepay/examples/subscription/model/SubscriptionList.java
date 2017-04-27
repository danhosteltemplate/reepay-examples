package com.reepay.examples.subscription.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mikkel on 05/04/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscriptionList {

    @JsonProperty("content")
    private List<Subscription> subscriptions;

    @JsonProperty("page")
    private int page;

    public SubscriptionList(){}

    public SubscriptionList(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public void addSubscription(Subscription subscription){
        this.subscriptions.add(subscription);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<String> getCustomers(){
        List<String> customers = new ArrayList();
        for(Subscription subscription : subscriptions){
            customers.add(subscription.getCustomer());
        }
        return customers;
    }
}
