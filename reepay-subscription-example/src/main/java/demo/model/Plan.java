package demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mikkel on 06/04/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Plan {
    private float amount;
    private String name;
    private String description;

    private float vat;

    private String currency;

    @JsonProperty("amount_incl_vat")
    private boolean inclVat;

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getVat() {
        return vat;
    }

    public void setVat(float vat) {
        this.vat = vat;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean isInclVat() {
        return inclVat;
    }

    public void setInclVat(boolean inclVat) {
        this.inclVat = inclVat;
    }
}
