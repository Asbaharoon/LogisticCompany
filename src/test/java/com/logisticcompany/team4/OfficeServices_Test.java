package com.logisticcompany.team4;

import com.logisticcompany.team4.model.Customer;
import com.logisticcompany.team4.model.Office;
import com.logisticcompany.team4.repository.CompanyRepository;
import com.logisticcompany.team4.repository.CustomerRepository;
import com.logisticcompany.team4.repository.OfficeRepository;
import com.logisticcompany.team4.repository.UserRepository;
import com.logisticcompany.team4.services.CustomerServices;
import com.logisticcompany.team4.services.OfficeServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class OfficeServices_Test {

    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private OfficeServices officeServices;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;

    private Office FirstOffice;
    private Office SecondOffice;

    @BeforeEach
    public void init(){
        FirstOffice= officeRepository.findById(1).get();
        SecondOffice = officeRepository.findById(2).get();
    }

    @Test
    public void getOfficesTest(){
        List<Office> expectedListOfOffices=new ArrayList<>();
        expectedListOfOffices.add(FirstOffice);
        expectedListOfOffices.add(SecondOffice);
        Assertions.assertEquals(expectedListOfOffices.size(),officeServices.findAll().size());

    }

    @Test
    public void addOfficeTest(){
        Office toAddOffice=new Office(0, "Street Q", 800, companyRepository.getById(2), null, null);
        officeServices.addOffice(toAddOffice);
        Assertions.assertEquals(3,officeServices.findAll().size());
    }

    @Test
    public void getOfficeTest(){
        Office office=FirstOffice;
        Office office1=officeServices.findOfficeById(FirstOffice.getId());
//            Assertions.assertEquals(FirstCompany,companyServices.getCompanyById(FirstCompany.getId()));
        Assertions.assertEquals(office.getAddress(),office1.getAddress());
    }
    @Test
    public void UpdateOfficeTest() throws Exception{
        Office toUpdateOffice=new Office(2, "Street Q", 800, companyRepository.getById(2), null, null);
        officeServices.updateOffice(toUpdateOffice);
        Assertions.assertEquals("Street Q",officeServices.findOfficeById(SecondOffice.getId()).getAddress());
    }
    @Test
    public void deleteOfficeTest() throws Exception{
            Office office=officeRepository.save(new Office(0, "Street F", 1000, companyRepository.getById(2), null, null));
            officeServices.deleteOffice(office.getId());
            Assertions.assertFalse(officeServices.findAll().contains(office));
    }

}
