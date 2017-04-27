package com.reepay.examples.subscription.repository;

import com.reepay.examples.subscription.model.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by mikkel on 27/03/2017.
 */
@Repository
@Transactional
public interface CustomerRepository extends CrudRepository<Customer, Long>{
    List<Customer> findByFirstName (String firstName);
    Customer findByEmail (String email);
    List<Customer> findAll();

    @Query("UPDATE Customer c SET c.handle = :handle WHERE c.id=:id")
    @Modifying
    int updateHandle(@Param("id") Long id, @Param("handle") String handle);
}
