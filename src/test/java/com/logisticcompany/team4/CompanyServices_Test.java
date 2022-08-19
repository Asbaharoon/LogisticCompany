package com.logisticcompany.team4;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.logisticcompany.team4.model.*;
import com.logisticcompany.team4.repository.*;
import com.logisticcompany.team4.services.CompanyServices;
import com.logisticcompany.team4.services.EmployeeServices;
import com.logisticcompany.team4.services.OfficeServices;
import com.logisticcompany.team4.services.ParcelServices;
import constant.EmployeeType;
import constant.ParcelStatus;
import constant.RoleType;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CompanyServices_Test {

        @Autowired
        private CompanyRepository companyRepository;

        @Autowired
        private CompanyServices companyServices;

        private Company FirstCompany;
        private Company SecondCompany;

        @BeforeEach
        public void init(){
            if(companyServices.findAll().size()<2){
                companyRepository.save(new Company(0, "Company B", "Street B", null, null));
            }
            Company co1 = companyRepository.findById(1).get();
            Company co2 = companyRepository.findById(2).get();
            FirstCompany=co1;
            SecondCompany=co2;
        }

        @Test
        public void getCompaniesTest(){;
            List<Company> expectedListOfCompanies=new ArrayList<>();
            expectedListOfCompanies.add(FirstCompany);
            expectedListOfCompanies.add(SecondCompany);
            Assertions.assertEquals(companyServices.findAll().size(),expectedListOfCompanies.size());

        }

    @Test
    public void addCompanyTest(){;
        Company toAddCompany=new Company(0, "Company C", "Street C", null, null);
        companyServices.addCompany(toAddCompany);
        Assertions.assertEquals(companyServices.findAll().size(),3);
    }

    @Test
    public void getCompanyTest(){;
            Company company=FirstCompany;
            Company company1=companyServices.getCompanyById(FirstCompany.getId());
//            Assertions.assertEquals(FirstCompany,companyServices.getCompanyById(FirstCompany.getId()));
            Assertions.assertEquals(company.getAddress(),company1.getAddress());
    }
    @Test
    public void UpdateCompanyTest() throws Exception{;
        Company toUpdateCompany=new Company(1, "Company C", "Street C", null, null);
        companyServices.updateCompany(toUpdateCompany);
        Assertions.assertEquals("Company C",companyServices.getCompanyById(FirstCompany.getId()).getName());
    }

    @Test
    public void deleteCompanyTest() throws Exception{;
        Company company=companyRepository.save(new Company(0, "Company B", "Street B", null, null));
        companyServices.deleteCompany(company.getId());
        Assertions.assertFalse(companyServices.findAll().contains(company));
    }

        @Test
    public void getRevenuesTest(){
            double expected=31.4;
            double actual=companyServices.getRevenues(FirstCompany.getId());
            Assertions.assertEquals(expected,actual);
        }
    @Test
    public void getExpensesTest(){
        double expected=3408.7;
        double actual=companyServices.getExpenses(FirstCompany.getId());
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void getProfitTest(){
        double expected=-4083.4599999999996;
        double actual=companyServices.getProfit(SecondCompany.getId());
        Assertions.assertEquals(expected,actual);
    }

}

