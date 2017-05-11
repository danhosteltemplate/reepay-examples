package com.reepay.examples.subscription.model;

import java.util.List;
import java.util.Optional;

/**
 * Created by mikkel on 11/05/2017.
 */
public class PlanList {
    private List<Plan> plans;

    public List<Plan> getPlans() {
        return plans;
    }

    public void setPlans(List<Plan> plans) {
        this.plans = plans;
    }
    
    public Plan getPlan(int index){
        return this.plans.get(index);
    }
    
    public Optional<Plan> getPlan(String handle){
        return this.plans.stream().filter(p -> p.getHandle().equals(handle)).findFirst();
    }
}
