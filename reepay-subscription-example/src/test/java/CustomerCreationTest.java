import demo.Application;
import demo.facade.CustomerService;
import demo.model.Customer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

/**
 * Created by mikkel on 05/04/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("itest")
public class CustomerCreationTest {

    @Inject
    CustomerService customerService;

    private Customer customer = new Customer();

    @Before
    public void setup(){
        customer.setEmail("ttt@reepay.com");
        customer.setFirstName("Mikkel");
        customer.setLastName("Jensen");
        customer.setPassword("123456");
    }

    /**
     * Integrationtest: Tests if the handle is generated and saved to the database.
     */
    @Test
    public void updateHandle(){
        customer = customerService.create(customer);
        int something = customerService.updateHandle(customer.getId(), "expcustom" + customer.getId());
        customer = customerService.getByemail(customer.getEmail());
        Assert.assertEquals(customer.getHandle(), "expcustom" + customer.getId());
    }
}
