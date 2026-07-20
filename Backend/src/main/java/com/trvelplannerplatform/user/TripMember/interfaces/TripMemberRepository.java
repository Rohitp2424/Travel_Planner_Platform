package com.trvelplannerplatform.user.TripMember.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trvelplannerplatform.user.TripMember.entities.TripMember;
import com.trvelplannerplatform.user.entity.Trip;
import com.trvelplannerplatform.user.entity.User;

public interface TripMemberRepository extends JpaRepository<TripMember, Long> {
	
	List<TripMember> findByTrip(Trip trip);

    List<TripMember> findByMember(User member);

    Optional<TripMember> findByTripAndMember(Trip trip, User member);

    boolean existsByTripAndMember(Trip trip, User member);

}
