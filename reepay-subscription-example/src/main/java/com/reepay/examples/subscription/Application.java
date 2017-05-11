package com.reepay.examples.subscription;

import com.reepay.examples.subscription.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by mikkel on 27/03/2017.
 */
@SpringBootApplication
@EnableJpaRepositories("com.reepay.examples.subscription.repository")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    public CommandLineRunner demo(CustomerRepository customerRepository, PasswordEncoder passwordEncoder){
        return (args) -> {
            /*
             * It's possible to generate initial database data here.
             * See the commented example below
             */
            
//            Customer customer = new Customer();
//            customer.setHandle("6607f200-c86f-45c0-9dd8-7267f643e61c");
//            customer.setFirstName("Reepay");
//            customer.setLastName("Example");
//            customer.setEmail("reepay-api-examples@mailinator.com");
//            customer.setPassword(passwordEncoder.encode("123456"));
//            customerRepository.save(customer);
        };
    }
}
