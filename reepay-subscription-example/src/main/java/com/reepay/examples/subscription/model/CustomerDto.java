package com.reepay.examples.subscription.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mikkel on 03/04/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerDto {

    @JsonProperty("generate_handle")
    private boolean generateHandle = false;

    private String email;

    private String handle;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    public boolean isGenerateHandle() {
        return generateHandle;
    }

    public void setGenerateHandle(boolean generateHandle) {
        this.generateHandle = generateHandle;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
