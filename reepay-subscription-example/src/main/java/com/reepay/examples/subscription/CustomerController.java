package com.reepay.examples.subscription;

import com.reepay.examples.subscription.controllers.ReepayController;
import com.reepay.examples.subscription.facade.CustomerService;
import com.reepay.examples.subscription.mapper.CustomerMapper;
import com.reepay.examples.subscription.model.Customer;
import com.reepay.examples.subscription.model.FormData;
import com.reepay.examples.subscription.model.SubscriptionList;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

import javax.inject.Inject;
import java.util.UUID;


/**
 * Created by mikkel on 27/03/2017.
 */
@Controller
public class CustomerController {

    @Inject
    private CustomerService customerService;

    @Inject
    private PasswordEncoder passwordEncoder;

    @Inject
    private ReepayController reepayController;
    
    @Inject
    private PlanConfig planConfig;

    @RequestMapping("/")
    public String home(){
        return "home";
    }
    
    @RequestMapping("/register")
    public String signup(Model model) {
        model.addAttribute("formdata", new FormData());
        //You can pass a different handle here to decide which plan you want to expose. You can also expose all plans
        //In this example we simply choose to expose the gold plan.
        model.addAttribute("plan", reepayController.getPlan(planConfig.getPlanHandle()));
        //model.addAttribute("plans", reepayController.getPlans());
        return "register";
    }

    @PostMapping("/register/new")
    public String newCustomer(@ModelAttribute FormData formData, Model model) {

        Customer customer = CustomerMapper.mapCustomer(formData);

        if (customer.getPassword() != null) customer.setPassword(passwordEncoder.encode(customer.getPassword()));

        if (customerService.getByemail(customer.getEmail()) != null) return "redirect:/register";

        String uuid = UUID.randomUUID().toString();
        customer = customerService.create(customer);
        customerService.updateHandle(customer.getId(), uuid);
        formData.setHandle(uuid);

        customer = reepayController.registerCustomerAndSubscription(formData);

        model.addAttribute("customer", customer);
        return "thankyou";
    }

    @RequestMapping("/signin")
    public String signin(Model model) {
        model.addAttribute("customer", new Customer());
        return "signin";
    }

    @PostMapping("/signin/validate")
    public String signinValidate(@ModelAttribute Customer customer, Model model, HttpSession session) {
        Customer customerFromDB = customerService.getByemail(customer.getEmail());
        if (!passwordEncoder.matches(customer.getPassword(), customerFromDB.getPassword())){
            model.addAttribute("errorMsg", "There was an error logging you in, try again");
            return "signin";
        }
        session.setAttribute("customer", customerFromDB);
        return "redirect:/mypage";

    }

    @RequestMapping("/customers")
    public String allCustomers(Model model) {
        model.addAttribute("customers", customerService.getAll());
        return "customers";
    }

    @RequestMapping("/mypage")
    public String myPage(Model model, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");

        if(customer == null){
            model.addAttribute("errorMsg", "Please login to view your page.");
            model.addAttribute("customer", new Customer());
            return "signin";
        }
        model.addAttribute("customer", customer);
        SubscriptionList subscriptions = reepayController.getSubscriptionsForCustomer(customer.getHandle());
        model.addAttribute("subscriptions", subscriptions.getSubscriptions());
        return "mypage";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/signin";
    }
}
