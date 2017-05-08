package com.reepay.examples.subscription.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mikkel on 03/04/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscriptionDto {

    @JsonProperty("handle")
    private String handle;
    @JsonProperty("signup_method")
    private String signupMethod = "source";
    @JsonProperty("plan")
    private String plan;
    @JsonProperty("source")
    private String source;
    @JsonProperty("generate_handle")
    private boolean generateHandle = true;

    @JsonProperty("create_customer")
    private CustomerDto customer;

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getSignupMethod() {
        return signupMethod;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public boolean isGenerateHandle() {
        return generateHandle;
    }

    public void setGenerateHandle(boolean generateHandle) {
        this.generateHandle = generateHandle;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public void setSignupMethod(String signupMethod) {
        this.signupMethod = signupMethod;
    }
}
