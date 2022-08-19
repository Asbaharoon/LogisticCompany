package com.logisticcompany.team4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logisticcompany.team4.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
