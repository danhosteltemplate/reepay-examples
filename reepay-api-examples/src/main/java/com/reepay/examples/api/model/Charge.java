package com.reepay.examples.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Charge {

    private String handle;

    private String state;

    private String customer;

    private Integer amount;

    private String currency;

    @JsonProperty("refunded_amount")
    private Integer refundedAmount;

    private Date authorized;

    private Date settled;

    private Date cancelled;

    private Date created;

    private String transaction;

    private String error;

    @JsonProperty("error_state")
    private String errorState;

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getRefundedAmount() {
        return refundedAmount;
    }

    public void setRefundedAmount(Integer refundedAmount) {
        this.refundedAmount = refundedAmount;
    }

    public Date getAuthorized() {
        return authorized;
    }

    public void setAuthorized(Date authorized) {
        this.authorized = authorized;
    }

    public Date getSettled() {
        return settled;
    }

    public void setSettled(Date settled) {
        this.settled = settled;
    }

    public Date getCancelled() {
        return cancelled;
    }

    public void setCancelled(Date cancelled) {
        this.cancelled = cancelled;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorState() {
        return errorState;
    }

    public void setErrorState(String errorState) {
        this.errorState = errorState;
    }
}
