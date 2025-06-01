package com.hotels.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.hotels.entity.HotelDetails;

@Repository
public interface HotelRepository extends MongoRepository<HotelDetails, String>{

	

}
