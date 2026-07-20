package com.trvelplannerplatform.user.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.trvelplannerplatform.user.trip.tripEnums.TripStatus;
import com.trvelplannerplatform.user.trip.tripEnums.TripVisibility;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "trips")
@Data
public class Trip {
	
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(name = "trip_name", nullable = false, length = 100)
	    private String tripName;

	    @Column(name = "description", length = 500)
	    private String description;

	    @Column(name = "source_city", nullable = false, length = 100)
	    private String source;

	    @Column(name = "destination_city", nullable = false, length = 100)
	    private String destination;

	    @Column(name = "start_date")
	    private LocalDate startDate;

	    @Column(name = "end_date")
	    private LocalDate endDate;

	    @Column(name = "budget")
	    private BigDecimal budget;

	    @Enumerated(EnumType.STRING)
	    @Column(name = "status")
	    private TripStatus status;

	    @Enumerated(EnumType.STRING)
	    @Column(name = "visibility")
	    private TripVisibility visibility;

	    @Column(name = "created_at")
	    private LocalDateTime createdAt;

	    @Column(name = "updated_at")
	    private LocalDateTime updatedAt;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "owner_id", nullable = false)
	    private User owner;

}
