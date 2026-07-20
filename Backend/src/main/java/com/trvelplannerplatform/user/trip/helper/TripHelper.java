package com.trvelplannerplatform.user.trip.helper;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.trvelplannerplatform.user.entity.Trip;
import com.trvelplannerplatform.user.entity.User;
import com.trvelplannerplatform.user.enums.ErrorCodeEnum;
import com.trvelplannerplatform.user.exception.UserValidationException;
import com.trvelplannerplatform.user.interfaces.UserRepository;
import com.trvelplannerplatform.user.trip.interfaces.TripRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class TripHelper {
	
	private final UserRepository userRepository;
	
	private final TripRepository tripRepository;
	
	public User getLoggedInUser() {
		
		Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UserValidationException(
                                ErrorCodeEnum.USER_NOT_FOUND.getCode(),
                                ErrorCodeEnum.USER_NOT_FOUND.getMessage(),
                                HttpStatus.NOT_FOUND));
	}
	
	public Trip getTrip(Long tripId){
	

        return tripRepository.findById(tripId)
        		
                .orElseThrow(() ->
                        new UserValidationException(
                                ErrorCodeEnum.TRIP_NOT_FOUND.getCode(),
                                ErrorCodeEnum.TRIP_NOT_FOUND.getMessage(),
                                HttpStatus.NOT_FOUND));
        
    }
	
	
	public void validateTripOwner(User user, Trip trip){

	    if(!trip.getOwner().getId().equals(user.getId())){

	        throw new UserValidationException(
	                ErrorCodeEnum.UNAUTHORIZED_TRIP_ACCESS.getCode(),
	                ErrorCodeEnum.UNAUTHORIZED_TRIP_ACCESS.getMessage(),
	                HttpStatus.FORBIDDEN);
	    }

	}


}
