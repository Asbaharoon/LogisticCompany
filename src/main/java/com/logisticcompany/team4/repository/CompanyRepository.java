package com.logisticcompany.team4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logisticcompany.team4.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

}
