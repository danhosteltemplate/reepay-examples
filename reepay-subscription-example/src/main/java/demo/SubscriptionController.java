package demo;

import demo.controllers.ReepayController;

import demo.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.inject.Inject;
import java.util.Optional;

/**
 * Created by mikkel on 06/04/2017.
 */
@Controller
public class SubscriptionController {
    @Inject
    private ReepayController reepayController;

    @RequestMapping(value = "/subscription/{subscriptionHandle}", method = RequestMethod.GET)
    public String getSubscription(@PathVariable("subscriptionHandle") String subscriptionHandle, Model model, HttpSession session){
        Customer customer = (Customer)session.getAttribute("customer");
        if(customer == null) return "redirect:/signin";
        Subscription subscription = reepayController.getSubscription(subscriptionHandle);

        model.addAttribute("customer", customer);
        model.addAttribute(reepayController.getSubscriptionsForCustomer(customer.getHandle()));

        if(!subscription.getCustomer().equals(customer.getHandle())){
            model.addAttribute("errorMsg", "You don't have permission to view that subscription!");
            return "mypage";
        }

        Plan plan = reepayController.getPlan(subscription.getPlan());
        subscription.setAmount(plan.getAmount() / 100);

        model.addAttribute("plan", plan);
        model.addAttribute("subscription", subscription);
        model.addAttribute("coupon", new Coupon());
        return "subscription";
    }

    @RequestMapping(value = "/unsubscribe/{subscriptionHandle}", method = RequestMethod.GET)
    public String unsubscribe(@PathVariable("subscriptionHandle") String subscriptionHandle, Model model){
        Subscription subscription = reepayController.unsubscribe(subscriptionHandle);
        return "redirect:/mypage";
    }

    @RequestMapping(value = "/resubscribe/{subscriptionHandle}", method = RequestMethod.GET)
    public String resubscribe(@PathVariable("subscriptionHandle") String subscriptionHandle, Model model){
        Subscription subscription = reepayController.resubscribe(subscriptionHandle);
        return "redirect:/mypage";
    }

    @RequestMapping(value = "/invoices/{subscriptionHandle}", method = RequestMethod.GET)
    public String getInvoices(@PathVariable("subscriptionHandle") String subscriptionHandle, Model model, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) return "redirect:/signin";
        InvoiceList invoices = reepayController.getInvoicesForSubscription(subscriptionHandle, customer.getHandle());
        CardList cards = reepayController.getCardsForCustomer(customer.getHandle());
        model.addAttribute("invoices", invoices.getInvoices());
        model.addAttribute("cards", cards.getCards());
        return "invoices";
    }
    @PostMapping("/coupon/activate/{subscriptionHandle}")
    public String validateCoupon(@PathVariable("subscriptionHandle") String subscriptionHandle,
                                 @ModelAttribute Coupon coupon,
                                 Model model){
        
        Optional<Coupon> coup = reepayController.validateCoupon(coupon);

        if(coup.isPresent()) reepayController.redeemCoupon(coup.get(), subscriptionHandle);

        return "redirect:/mypage";
    }
}
