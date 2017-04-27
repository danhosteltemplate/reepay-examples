import com.reepay.examples.subscription.Application;
import com.reepay.examples.subscription.ReepayClient;
import com.reepay.examples.subscription.model.AbstractExamples;
import com.reepay.examples.subscription.model.SubscriptionList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mikkel on 10/04/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("itest")
public class ReepayClientTest extends AbstractExamples{

    @Inject
    private ReepayClient client;

    @Test
    public void getWithParamsTest(){
        Map<String, List<String>> params = new HashMap();
        List<String> searchParams = new ArrayList<>();
        searchParams.add("customer.handle:cust-0165");
        params.put("search", searchParams);
        client.get("/v1/subscription", params);
        SubscriptionList subscriptions = readResponse(client.get("/v1/subscription", params), SubscriptionList.class);

        List<String> customers = subscriptions.getCustomers();
        customers.add("cust-0165");
        customers.removeIf("cust-0165"::equals);

        Assert.assertTrue(customers.isEmpty());
    }
}
