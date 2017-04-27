import com.reepay.examples.subscription.Application;
import com.reepay.examples.subscription.controllers.ReepayController;
import com.reepay.examples.subscription.facade.CustomerService;
import com.reepay.examples.subscription.model.Customer;
import com.reepay.examples.subscription.model.FormData;
import com.reepay.examples.subscription.model.SubscriptionList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.UUID;

/**
 * Created by mikkel on 05/04/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("itest")
public class ReepayControllerTest {
    @Inject
    ReepayController reepayController;

    @Inject
    CustomerService customerService;

    private Customer customer = new Customer();
    private FormData subCustomer = new FormData();
    private String uuid;
    @Before
    public void setUp(){
        this.customer.setHandle("cust-0165");

        this.subCustomer.setEmail("reepay-api-examples@mailinator.com");
        this.subCustomer.setFirstName("Reepay");
        this.subCustomer.setLastName("Test");
        this.subCustomer.setPlan("gold");
        this.uuid = UUID.randomUUID().toString();
        this.subCustomer.setHandle(this.uuid);
        this.subCustomer.setMethod("email");
    }

    @Test
    public void getSubscriptionsForCustomerTest(){
        SubscriptionList subscriptions = reepayController.getSubscriptionsForCustomer(customer.getHandle());
        System.out.println(subscriptions.getSubscriptions().get(0).getHandle());
        Assert.assertEquals("sub-0400",  subscriptions.getSubscriptions().get(0).getHandle());
    }

    @Test
    public void registerCustomerAndSubscriptionTest(){
        Customer result = reepayController.registerCustomerAndSubscription(subCustomer);
        Assert.assertTrue(result.getHandle().equals(this.uuid));

    }
}
