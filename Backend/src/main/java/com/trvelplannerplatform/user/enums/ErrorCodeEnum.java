package com.trvelplannerplatform.user.enums;

public enum ErrorCodeEnum {

    FIRST_NAME_REQUIRED("TP-001", "First name is required"),

    FIRST_NAME_INVALID_LENGTH("TP-002", "First name must be between 2 and 50 characters"),

    LAST_NAME_REQUIRED("TP-003", "Last name is required"),

    EMAIL_REQUIRED("TP-004", "Email is required"),

    EMAIL_INVALID("TP-005", "Please provide a valid email address"),

    PHONE_REQUIRED("TP-006", "Phone number is required"),

    PHONE_INVALID("TP-007", "Please provide a valid 10-digit mobile number"),

    PASSWORD_REQUIRED("TP-008", "Password is required"),

    PASSWORD_WEAK("TP-009", "Password must contain uppercase, lowercase, digit and special character"),

    CONFIRM_PASSWORD_REQUIRED("TP-010", "Confirm password is required"), 
    
    VALIDATION_ERROR("TP-011", "Validation error occurred"), 
    
    UNKNOWN_ERROR("TP-999", "An unknown error occurred"), 
    
    PASSWORDS_DO_NOT_MATCH("TP-012", "Passwords do not match"), 
    
    EMAIL_ALREADY_EXISTS("TP-013", "Email already exists"), 
    
    PHONE_NUMBER_ALREADY_EXISTED("TP-014", "Phone number already exists"), 
    
    TERMS_NOT_ACCEPTED("TP-015", "Terms and conditions must be accepted"), 
    
    REGISTRATION_DISABLED("TP-016", "User registration is currently disabled"), 
    
    USER_NOT_FOUND("TP-017", "User not found"), 
    
    INVALID_CREDENTIALS("TP-018", "Invalid email or password"), 
    
    AUTH_HEADER_IS_NULL("TP-019","Header authentication key is null or header doesn't contains bearer token"),
	
	TRIP_NAME_REQUIRED("TP-101", "Trip name is required"),
	
	TRIP_NAME_INVALID_LENGTH("TP-102", "Trip name must be between 3 and 100 characters"),
	
	TRIP_DESCRIPTION_INVALID_LENGTH("TP-103", "Description cannot exceed 500 characters"),
	
	SOURCE_REQUIRED("TP-104", "Source is required"),
	
	SOURCE_INVALID_LENGTH("TP-105", "Source cannot exceed 100 characters"),
	
	DESTINATION_REQUIRED("TP-106", "Destination is required"),
	
	DESTINATION_INVALID_LENGTH("TP-107", "Destination cannot exceed 100 characters"),
	
	START_DATE_REQUIRED("TP-108", "Start date is required"),
	
	START_DATE_INVALID("TP-109", "Start date cannot be in the past"),
	
	END_DATE_REQUIRED("TP-110", "End date is required"),
	
	BUDGET_REQUIRED("TP-111", "Budget is required"),
	
	BUDGET_INVALID("TP-112", "Budget must be greater than zero"),
	
	VISIBILITY_REQUIRED("TP-113", "Trip visibility is required"), 
	
	TRIP_CREATION_FAILED("TP-114", "Trip creation failed"), 
	
	TRIP_FETCH_FAILED("TP-115", "Failed to fetch the trips"), 
	
	TRIP_NOT_FOUND("TP-116", "Trip not found"), 
	
	UNAUTHORIZED_TRIP_ACCESS("TP-117", "Trip is unauthorized"), 
	
	TRIP_SEARCH_FAILED("TP-118", "Trip couldn't found"), 
	
	ITINERARY_NOT_FOUND("TP-119", "Trip details not found"), 
	
	INVALID_ITINERARY("TP-120", "Trip details are not valid"), 
	
	ITINERARY_CREATION_FAILED("TP-121", "Trip iternary creation failed"), 
	
	ITINERARY_UPDATE_FAILED("TP-122", "Trip iternary update failed"), 
	
	ITINERARY_DELETE_FAILED("TP-123", "Trip Iternary deletion failed"), 
	
	TRIP_MEMBER_NOT_FOUND("TP-124", "Trip member not found"), 
	
	UNAUTHORIZED_ACCESS("TP-125", "Trip owner is unauthorized"), 
	
	TRIP_MEMBER_ALREADY_EXISTS("TP-126", "Trip member already exist"), 
	
	INVALID_INVITATION_STATUS("TP-127", "Invitation is invalid"), 
	
	CANNOT_REMOVE_OWNER("TP-128", "Trip owner cannot be removed"), 
	
	CANNOT_INVITE_YOURSELF("TP-129", "owner cannot invite himself"), 
	
	TRIP_MEMBER_CREATION_FAILED("TP-130", "Trip member creation failed"), 
	
	TRIP_MEMBER_UPDATE_FAILED("TP-131", "Trip member update failed"), 
	
	TRIP_MEMBER_DELETE_FAILED("TP-132", "Trip member deletion failed"), 
	
	BUDGET_NOT_FOUND("TP-133", "Trip budget not found"), 
	
	BUDGET_ALREADY_EXISTS("TP-134", "Budget already exists"), 
	
	EXPENSE_NOT_FOUND("TP-135", "Trip expense does not found"), 
	
	INSUFFICIENT_BUDGET("TP-136", "Insufficient budget"), 
	
	BOOKING_NOT_FOUND("TP-137", "Booking not found"), 
	
	BOOKING_REFERENCE_ALREADY_EXISTS("TP-138", "Booking reference already exists"), 
	
	INVALID_BOOKING_DATES("TP-140", "Booking dates are invalid");
	
	
	
    private final String errorCode;
    private final String errorMessage;

    ErrorCodeEnum(String code, String message) {
        this.errorCode = code;
        this.errorMessage = message;
    }

    public String getCode() {
        return errorCode;
    }

    public String getMessage() {
        return errorMessage;
    }

    public static ErrorCodeEnum fromName(String name) {
        return ErrorCodeEnum.valueOf(name);
    }
}
