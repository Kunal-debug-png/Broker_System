package com.kunal_debug_png.test_relations.Repository;

import com.kunal_debug_png.test_relations.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findByNumber(String number);

}
