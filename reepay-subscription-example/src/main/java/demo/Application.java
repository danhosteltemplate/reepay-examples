package demo;

import demo.model.Customer;
import demo.repository.CustomerRepository;
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
@EnableJpaRepositories("demo.repository")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository customerRepository, PasswordEncoder passwordEncoder){
        return (args) -> {
            Customer customer = new Customer();
            customer.setFirstName("Mikkel");
            customer.setLastName("Jensen");
            customer.setHandle("cust-0163");
            customer.setEmail("mikkel@reepay.com");
            customer.setPassword(passwordEncoder.encode("123456"));
            customerRepository.save(customer);

            Customer customer2 = new Customer();
            customer2.setHandle("6607f200-c86f-45c0-9dd8-7267f643e61c");
            customer2.setFirstName("Mikkel");
            customer2.setLastName("Jensen");
            customer2.setEmail("reepay-api-examples@mailinator.com");
            customer2.setPassword(passwordEncoder.encode("123456"));
            customerRepository.save(customer2);
        };
    }
}
