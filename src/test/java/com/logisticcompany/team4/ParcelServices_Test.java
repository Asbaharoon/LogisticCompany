package com.logisticcompany.team4;

import com.logisticcompany.team4.model.Office;
import com.logisticcompany.team4.model.Parcel;
import com.logisticcompany.team4.repository.*;
import com.logisticcompany.team4.services.OfficeServices;
import com.logisticcompany.team4.services.ParcelServices;
import constant.ParcelStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ParcelServices_Test {

        @Autowired
        private ParcelRepository parcelRepository;

        @Autowired
        private ParcelServices parcelServices;

        @Autowired
        private OfficeRepository officeRepository;

        @Autowired
        private CustomerRepository customerRepository;

        private Parcel FirstParcel;
        private Parcel SecondParcel;

        @BeforeEach
        public void init(){
            FirstParcel= parcelRepository.findById(1).get();
            SecondParcel = parcelRepository.findById(2).get();
        }

        @Test
        public void getParcelsTest(){
            List<Parcel> expectedListOfParcels=new ArrayList<>();
            expectedListOfParcels.add(FirstParcel);
            expectedListOfParcels.add(SecondParcel);
            Assertions.assertEquals(expectedListOfParcels.size()+2,parcelServices.findAll().size());

        }

        @Test
        public void addParcelTest(){
            Parcel toAddParcel=new Parcel(0,customerRepository.findById(1).get(),customerRepository.findById(2).get(),7.50,"Place C", 32.95, 3.25, officeRepository.findById(1).get(), ParcelStatus.IN_TRANSIT);
            parcelServices.addParcel(toAddParcel);
            Assertions.assertEquals(5,parcelServices.findAll().size());
        }

        @Test
        public void getParcelTest(){
            Parcel parcel=FirstParcel;
            Parcel parcel1=parcelServices.findParcelById(FirstParcel.getId());
//            Assertions.assertEquals(FirstCompany,companyServices.getCompanyById(FirstCompany.getId()));
            Assertions.assertEquals(parcel.getDeliveryAddress(),parcel1.getDeliveryAddress());
        }
        @Test
        public void UpdateParcelTest() throws Exception{
            Parcel toUpdateParcel=new Parcel(2,customerRepository.findById(3).get(),customerRepository.findById(2).get(),10.50,"Place Q", 52.95, 3.25, officeRepository.findById(1).get(), ParcelStatus.IN_TRANSIT);
            parcelServices.updateParcel(toUpdateParcel);
            Assertions.assertEquals("Place Q",parcelServices.findParcelById(SecondParcel.getId()).getDeliveryAddress());
        }
        @Test
        public void deleteCompanyTest() throws Exception{
            Parcel parcel=parcelRepository.save(new Parcel(0,customerRepository.findById(2).get(),customerRepository.findById(1).get(),10.50,"Place Q", 52.95, 3.25, officeRepository.findById(1).get(), ParcelStatus.IN_TRANSIT));
            parcelServices.deleteParcel(parcel.getId());
            Assertions.assertFalse(parcelServices.findAll().contains(parcel));
        }

    }
