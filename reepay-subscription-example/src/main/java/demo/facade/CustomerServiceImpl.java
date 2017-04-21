package demo.facade;

import demo.model.Customer;
import demo.repository.CustomerRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by mikkel on 27/03/2017.
 */
@Transactional
@Component("customerService")
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer getByemail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public int updateHandle(Long id, String handle) {
        return customerRepository.updateHandle(id, handle);
    }
}
