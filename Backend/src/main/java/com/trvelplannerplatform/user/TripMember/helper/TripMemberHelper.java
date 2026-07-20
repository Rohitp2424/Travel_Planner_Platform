package com.trvelplannerplatform.user.TripMember.helper;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.trvelplannerplatform.user.TripMember.entities.TripMember;
import com.trvelplannerplatform.user.TripMember.enums.InvitationStatus;
import com.trvelplannerplatform.user.TripMember.interfaces.TripMemberRepository;
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
public class TripMemberHelper {

    private final UserRepository userRepository;
    private final TripRepository tripRepository;
    private final TripMemberRepository tripMemberRepository;
    
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

    public Trip getTrip(Long tripId) {

        log.info("Fetching Trip : {}", tripId);

        return tripRepository.findById(tripId)
                .orElseThrow(() -> {

                    log.error("Trip not found : {}", tripId);

                    return new UserValidationException(
                            ErrorCodeEnum.TRIP_NOT_FOUND.getCode(),
                            ErrorCodeEnum.TRIP_NOT_FOUND.getMessage(),
                            HttpStatus.NOT_FOUND);

                });

    }
    
    public User getUserByEmail(String email) {

        log.info("Fetching user by email : {}", email);

        return userRepository.findByEmail(email)
                .orElseThrow(() -> {

                    log.error("User not found : {}", email);

                    return new UserValidationException(
                            ErrorCodeEnum.USER_NOT_FOUND.getCode(),
                            ErrorCodeEnum.USER_NOT_FOUND.getMessage(),
                            HttpStatus.NOT_FOUND);

                });

    }
    
    public TripMember getTripMember(Long memberId) {

        log.info("Fetching Trip Member : {}", memberId);

        return tripMemberRepository.findById(memberId)
                .orElseThrow(() -> {

                    log.error("Trip Member not found : {}", memberId);

                    return new UserValidationException(
                            ErrorCodeEnum.TRIP_MEMBER_NOT_FOUND.getCode(),
                            ErrorCodeEnum.TRIP_MEMBER_NOT_FOUND.getMessage(),
                            HttpStatus.NOT_FOUND);

                });

    }
    
    public void validateTripOwner(User user, Trip trip) {

        if (!trip.getOwner().getId().equals(user.getId())) {

            log.error("Unauthorized access by {}", user.getEmail());

            throw new UserValidationException(
                    ErrorCodeEnum.UNAUTHORIZED_ACCESS.getCode(),
                    ErrorCodeEnum.UNAUTHORIZED_ACCESS.getMessage(),
                    HttpStatus.FORBIDDEN);

        }

    }
    
    public void validateAlreadyMember(Trip trip, User member) {

        boolean exists =
                tripMemberRepository.existsByTripAndMember(trip, member);

        if (exists) {

            log.error("User {} already belongs to Trip {}",
                    member.getEmail(),
                    trip.getId());

            throw new UserValidationException(
                    ErrorCodeEnum.TRIP_MEMBER_ALREADY_EXISTS.getCode(),
                    ErrorCodeEnum.TRIP_MEMBER_ALREADY_EXISTS.getMessage(),
                    HttpStatus.BAD_REQUEST);

        }

    }
    
    public void validatePendingInvitation(TripMember tripMember) {

        if (tripMember.getInvitationStatus() != InvitationStatus.PENDING) {

            log.error("Invitation already processed.");

            throw new UserValidationException(
                    ErrorCodeEnum.INVALID_INVITATION_STATUS.getCode(),
                    ErrorCodeEnum.INVALID_INVITATION_STATUS.getMessage(),
                    HttpStatus.BAD_REQUEST);

        }

    }
    
    public void validateInvitationOwner(User loggedUser,
            TripMember tripMember) {

		if (!tripMember.getMember().getId().equals(loggedUser.getId())) {
		
		log.error("Invitation does not belong to {}",
		loggedUser.getEmail());
		
		throw new UserValidationException(
				ErrorCodeEnum.UNAUTHORIZED_ACCESS.getCode(),
				ErrorCodeEnum.UNAUTHORIZED_ACCESS.getMessage(),
				HttpStatus.FORBIDDEN);
		
		}

    }
    
    public void validateNotOwner(TripMember tripMember) {

        if (tripMember.getRole().name().equals("OWNER")) {

            log.error("Owner cannot be removed.");

            throw new UserValidationException(
                    ErrorCodeEnum.CANNOT_REMOVE_OWNER.getCode(),
                    ErrorCodeEnum.CANNOT_REMOVE_OWNER.getMessage(),
                    HttpStatus.BAD_REQUEST);

        }

    }



}
