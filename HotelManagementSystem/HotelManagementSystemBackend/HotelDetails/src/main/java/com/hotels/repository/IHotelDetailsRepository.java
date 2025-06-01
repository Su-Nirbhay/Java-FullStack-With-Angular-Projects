package com.hotels.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hotels.entity.IHotelDetails;

public interface IHotelDetailsRepository extends MongoRepository<IHotelDetails, String> {

}
