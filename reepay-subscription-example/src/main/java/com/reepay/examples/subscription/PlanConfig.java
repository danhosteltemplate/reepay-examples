package com.reepay.examples.subscription;

import com.reepay.examples.subscription.controllers.ReepayController;
import com.reepay.examples.subscription.model.Plan;
import com.reepay.examples.subscription.model.exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * Created by mikkel on 11/05/2017.
 */
@Component
public class PlanConfig {
    
    @Inject
    private ReepayController reepayController;

    private static final Logger LOG = LoggerFactory.getLogger(PlanConfig.class);
    private String handle;
    
    public PlanConfig(@Value("${plan_handle}") String planHandle){
        this.handle = planHandle;
    }
    
    @PostConstruct
    public void init(){
        
        Plan plan = null;
        try {
            plan = reepayController.getPlan(this.handle);
        } catch (ServiceException e){
            if(e.getCode() != 10) {
                LOG.error("Something went wrong getting the plan for handle: " + this.handle);
                throw e;
            } else {
                LOG.error("Couldn't find the plan handle " + this.handle);
                System.exit(1);
            }
        }
        
    }
    
    public String getPlanHandle(){
        return this.handle;
    }
    
}
