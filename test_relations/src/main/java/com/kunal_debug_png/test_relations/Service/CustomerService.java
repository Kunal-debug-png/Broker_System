package com.kunal_debug_png.test_relations.Service;

import com.kunal_debug_png.test_relations.Entity.Customer;
import com.kunal_debug_png.test_relations.Entity.LoginDAO;
import com.kunal_debug_png.test_relations.Repository.CustomerRepository;
import com.kunal_debug_png.test_relations.Entity.Session;
import com.kunal_debug_png.test_relations.Repository.SessionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    SessionRepository sessionRepository;

    public String loginUser(LoginDAO loginDAO) {
        String number=loginDAO.getNumber();
        String password=loginDAO.getPassword();
        Customer customer=customerRepository.findByNumber(number);

        if(customer.getPassword().equals(password)){
            Session newSession=new Session();
            newSession.setUserId(customer.getId());
            sessionRepository.save(newSession);
            return "Session has Started";
        }
        else{
            return "User doesnot exist";
        }
    }


    public String signupUser(Customer customer) {
        customer.setBalance(5000L);
        customerRepository.save(customer);
        return "Success";
    }
    @Transactional
    public String deleteByUserId(Long id) {

        sessionRepository.deleteByUserId(id);
        return  "success";
    }
}
