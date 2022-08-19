package com.logisticcompany.team4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logisticcompany.team4.model.Parcel;

public interface ParcelRepository extends JpaRepository<Parcel, Integer> {

}
