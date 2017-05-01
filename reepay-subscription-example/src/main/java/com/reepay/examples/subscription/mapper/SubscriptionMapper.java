package com.reepay.examples.subscription.mapper;

import com.reepay.examples.subscription.model.CustomerDto;
import com.reepay.examples.subscription.model.FormData;
import com.reepay.examples.subscription.model.SubscriptionDto;

/**
 * Created by mikkel on 03/04/2017.
 */
public final class SubscriptionMapper {
    public static SubscriptionDto getSubscription(FormData formData){
        SubscriptionDto subscriptionDTO = new SubscriptionDto();
        CustomerDto customerDto = new CustomerDto();

        customerDto.setEmail(formData.getEmail());

        if(formData.getHandle() != null) {
            customerDto.setHandle(formData.getHandle());
        } else {
            customerDto.setGenerateHandle(true);
        }
        
        customerDto.setFirstName(formData.getFirstName());
        customerDto.setLastName(formData.getLastName());
        
        subscriptionDTO.setCustomer(customerDto);

        if(formData.getMethod().equals("card_token")) {
            subscriptionDTO.setCardToken(formData.getToken());
        } else {
            subscriptionDTO.setSignupMethod(formData.getMethod());
        }
        subscriptionDTO.setPlan(formData.getPlan());
        return subscriptionDTO;
    }
}
