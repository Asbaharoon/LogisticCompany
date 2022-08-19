package com.logisticcompany.team4.services;

import com.logisticcompany.team4.model.Office;
import com.logisticcompany.team4.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class OfficeServices {
	 
	@Autowired
    private OfficeRepository officeRepository;

    public List<Office> findAll() {
        List<Office> offices = officeRepository.findAll();
        return offices;
    }

    public void addOffice(@ModelAttribute Office office) {
        officeRepository.save(office);
    }

    public Office findOfficeById(@PathVariable("id") int id) {
        Office office = officeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid office Id:" + id));
        return office;
    }

    public void updateOffice(@ModelAttribute Office office) throws Exception {
        Office officeInDB = officeRepository.findById(office.getId()).orElse(null);
        if (officeInDB != null) {
            officeInDB.setAddress(office.getAddress());
            officeInDB.setCompany(office.getCompany());
            officeRepository.save(officeInDB);
        } else {
            throw new Exception("Office not found");
        }
    }


    public void deleteOffice(@PathVariable("id") int id) {
        Office office = officeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid office Id:" + id));
        officeRepository.delete(office);
    }

}
