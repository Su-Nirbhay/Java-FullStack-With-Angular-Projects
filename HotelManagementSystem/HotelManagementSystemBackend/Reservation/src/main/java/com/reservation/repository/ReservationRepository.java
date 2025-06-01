package com.reservation.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.reservation.entity.ReservationUser;

@Repository
public interface ReservationRepository extends MongoRepository<ReservationUser, Long>  {
	public ReservationUser findByContact(Long contact);
}
