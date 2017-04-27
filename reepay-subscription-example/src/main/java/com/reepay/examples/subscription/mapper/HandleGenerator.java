package com.reepay.examples.subscription.mapper;

/**
 * Created by mikkel on 03/04/2017.
 */
public final class HandleGenerator {
    private final static String prefix = "excust";
    public static String getHandle(Long id){
        return prefix + id;
    }
}
