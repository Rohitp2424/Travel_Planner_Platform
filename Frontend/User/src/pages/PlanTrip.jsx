import "../styles/PlanTrip.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import ProgressBar from "../components/ProgressBar";

export default function PlanTrip() {

    const navigate = useNavigate();

    const [tripData, setTripData] = useState({
        tripName: "",
        destination: "",
        checkIn: "",
        checkOut: "",
        travellers: "2 Adults",
        tripType: "Adventure"
    });

    const handleChange = (e) => {

        const { name, value } = e.target;

        setTripData({
            ...tripData,
            [name]: value
        });

    };

    return (

        <div className="plan-trip-page">

            <ProgressBar currentStep={1} />

            <div className="trip-form">

                <h2>Plan Your Trip</h2>

                <div className="form-group">

                    <label>Trip Name</label>

                    <input
                        type="text"
                        name="tripName"
                        placeholder="Summer Vacation"
                        value={tripData.tripName}
                        onChange={handleChange}
                    />

                </div>

                <div className="form-group">

                    <label>Destination</label>

                    <input
                        type="text"
                        name="destination"
                        placeholder="Enter destination"
                        value={tripData.destination}
                        onChange={handleChange}
                    />

                </div>

                <div className="date-row">

                    <div className="form-group">

                        <label>Check-In</label>

                        <input
                            type="date"
                            name="checkIn"
                            value={tripData.checkIn}
                            onChange={handleChange}
                        />

                    </div>

                    <div className="form-group">

                        <label>Check-Out</label>

                        <input
                            type="date"
                            name="checkOut"
                            value={tripData.checkOut}
                            onChange={handleChange}
                        />

                    </div>

                </div>

                <div className="form-group">

                    <label>Travellers</label>

                    <select
                        name="travellers"
                        value={tripData.travellers}
                        onChange={handleChange}
                    >
                        <option>1 Adult</option>
                        <option>2 Adults</option>
                        <option>3 Adults</option>
                        <option>4 Adults</option>
                        <option>Family (5+)</option>
                    </select>

                </div>
                <div className="form-group">

                    <label>Trip Type</label>

                    <div className="trip-types">

                        <label>
                            <input
                                type="radio"
                                name="tripType"
                                value="Adventure"
                                checked={tripData.tripType === "Adventure"}
                                onChange={handleChange}
                            />
                            Adventure
                        </label>

                        <label>
                            <input
                                type="radio"
                                name="tripType"
                                value="Relaxation"
                                checked={tripData.tripType === "Relaxation"}
                                onChange={handleChange}
                            />
                            Relaxation
                        </label>

                        <label>
                            <input
                                type="radio"
                                name="tripType"
                                value="Family"
                                checked={tripData.tripType === "Family"}
                                onChange={handleChange}
                            />
                            Family
                        </label>

                        <label>
                            <input
                                type="radio"
                                name="tripType"
                                value="Camping"
                                checked={tripData.tripType === "Camping"}
                                onChange={handleChange}
                            />
                            Camping
                        </label>

                    </div>

                </div>

                <button
                    className="next-btn"
                    onClick={() => navigate("/itinerary")}
                >
                    Next: Itinerary →
                </button>

            </div>

        </div>

    );

}