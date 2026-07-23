import "../styles/Flights.css";
import { useNavigate } from "react-router-dom";
import { FaPlaneDeparture } from "react-icons/fa";
import ProgressBar from "../components/ProgressBar";

export default function Flights() {

    const navigate = useNavigate();

    const flights = [

        {
            airline: "IndiGo",
            from: "Pune",
            to: "Bali",
            departure: "08:00 AM",
            arrival: "04:30 PM",
            duration: "8h 30m",
            price: "₹18,500"
        },

        {
            airline: "Air India",
            from: "Pune",
            to: "Bali",
            departure: "10:30 AM",
            arrival: "07:00 PM",
            duration: "8h 30m",
            price: "₹20,200"
        },

        {
            airline: "Singapore Airlines",
            from: "Pune",
            to: "Bali",
            departure: "01:15 PM",
            arrival: "10:00 PM",
            duration: "8h 45m",
            price: "₹24,800"
        }

    ];

    return (

        <div className="flights-page">

            <ProgressBar currentStep={3} />

            <h1>Available Flights</h1>

            <div className="flights-container">

                {

                    flights.map((flight, index) => (

                        <div
                            className="flight-card"
                            key={index}
                        >

                            <div className="flight-header">

                                <FaPlaneDeparture className="plane-icon" />

                                <h2>{flight.airline}</h2>

                            </div>

                            <div className="flight-route">

                                <div>

                                    <h3>{flight.from}</h3>

                                    <p>{flight.departure}</p>

                                </div>

                                <div className="route-line">

                                    ✈️

                                    <span>{flight.duration}</span>

                                </div>

                                <div>

                                    <h3>{flight.to}</h3>

                                    <p>{flight.arrival}</p>

                                </div>

                            </div>

                            <div className="flight-footer">

                                <h2>{flight.price}</h2>
                                <button className="select-btn">

                                    Select Flight

                                </button>

                            </div>

                        </div>

                    ))

                }

            </div>

            <button
                className="next-btn"
                onClick={() => navigate("/hotels")}
            >

                Next: Hotels →

            </button>

        </div>

    );

}