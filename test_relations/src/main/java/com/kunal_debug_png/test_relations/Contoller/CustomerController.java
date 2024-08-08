package com.kunal_debug_png.test_relations.Contoller;


import com.kunal_debug_png.test_relations.Service.CustomerService;
import com.kunal_debug_png.test_relations.Entity.Customer;
import com.kunal_debug_png.test_relations.Entity.LoginDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/login")
    private String login(@RequestBody LoginDAO loginDAO){

        customerService.loginUser(loginDAO);
        return "Login success";
    }

    @PostMapping("/signup")
    private String signup(@RequestBody Customer customer){
        return customerService.signupUser(customer);
    }
    @DeleteMapping("/logout/{id}")
            private String deleteUser(@PathVariable Long id)
    {
        return customerService.deleteByUserId(id);
    }
}
