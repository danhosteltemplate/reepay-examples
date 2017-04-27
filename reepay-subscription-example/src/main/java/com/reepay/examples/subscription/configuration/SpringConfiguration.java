package com.reepay.examples.subscription.configuration;

import com.reepay.examples.subscription.controllers.ReepayController;
import com.reepay.examples.subscription.controllers.ReepayControllerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by mikkel on 30/03/2017.
 */

@Configuration
public class SpringConfiguration {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ReepayController reepayController(){
        return new ReepayControllerImpl();
    }
}
