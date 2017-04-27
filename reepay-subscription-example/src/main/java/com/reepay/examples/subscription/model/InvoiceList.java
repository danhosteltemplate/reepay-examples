package com.reepay.examples.subscription.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by mikkel on 11/04/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class InvoiceList {

    @JsonProperty("content")
    private List<Invoice> invoices;

    @JsonProperty("page")
    private int page;

    public InvoiceList(){}

    public InvoiceList(List<Invoice> invoices){
        this.invoices = invoices;
    }

    public void addInvoice(Invoice invoice){
        this.invoices.add(invoice);
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
