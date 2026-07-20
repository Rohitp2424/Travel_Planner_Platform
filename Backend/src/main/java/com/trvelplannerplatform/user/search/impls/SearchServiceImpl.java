package com.trvelplannerplatform.user.search.impls;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.trvelplannerplatform.user.entity.Trip;
import com.trvelplannerplatform.user.entity.User;
import com.trvelplannerplatform.user.enums.ErrorCodeEnum;
import com.trvelplannerplatform.user.exception.UserValidationException;
import com.trvelplannerplatform.user.search.dtos.SearchTripItemResponse;
import com.trvelplannerplatform.user.search.dtos.SearchTripResponse;
import com.trvelplannerplatform.user.search.interfaces.SearchTripService;
import com.trvelplannerplatform.user.trip.helper.TripHelper;
import com.trvelplannerplatform.user.trip.interfaces.TripRepository;
import com.trvelplannerplatform.user.trip.mapper.TripMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchServiceImpl implements SearchTripService  {

	private final TripRepository tripRepository;
	
	private final TripHelper tripHelper;
	
	private final TripMapper tripMapper;
	
	@Override
	public SearchTripResponse searchTrips(String keyword) {
		

		log.info("Search trip request received. Keyword: '{}'", keyword);

        User user = tripHelper.getLoggedInUser();

        log.info("Searching trips for user '{}'", user.getEmail());

        try {

            List<Trip> trips =
                    tripRepository.searchTrips(user, keyword);

            log.info("Found {} trip(s) for keyword '{}'",
                    trips.size(), keyword);

            List<SearchTripItemResponse> responses =
                    trips.stream()
                            .map(tripMapper::toSearchResponse)
                            .toList();

            SearchTripResponse response = new SearchTripResponse();
            response.setTotalTrips(responses.size());
            response.setTrips(responses);

            log.info("Search completed successfully.");

            return response;

        } catch (Exception ex) {

            log.error("Error while searching trips for user '{}'",
                    user.getEmail(), ex);

            throw new UserValidationException(
                    ErrorCodeEnum.TRIP_SEARCH_FAILED.getCode(),
                    ErrorCodeEnum.TRIP_SEARCH_FAILED.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
