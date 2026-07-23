import "../styles/ReviewBooking.css";
import { useNavigate } from "react-router-dom";
import ProgressBar from "../components/ProgressBar";

export default function ReviewBooking() {

    const navigate = useNavigate();

    const booking = {
        destination: "Bali, Indonesia",
        travellers: "2 Adults",
        travelDate: "15 Aug 2026 - 20 Aug 2026",
        flight: "Singapore Airlines",
        hotel: "Luxury Palm Hotel",
        total: "₹1,12,500"
    };

    return (

        <div className="review-page">

            <ProgressBar currentStep={5} />

            <div className="review-card">

                <h1>Review Your Booking</h1>

                <div className="review-item">
                    <span>🌍 Destination</span>
                    <strong>{booking.destination}</strong>
                </div>

                <div className="review-item">
                    <span>👥 Travellers</span>
                    <strong>{booking.travellers}</strong>
                </div>

                <div className="review-item">
                    <span>📅 Travel Dates</span>
                    <strong>{booking.travelDate}</strong>
                </div>

                <div className="review-item">
                    <span>✈️ Flight</span>
                    <strong>{booking.flight}</strong>
                </div>
                <div className="review-item">
                    <span>🏨 Hotel</span>
                    <strong>{booking.hotel}</strong>
                </div>

                <div className="review-item total">

                    <span>Total Amount</span>

                    <strong>{booking.total}</strong>

                </div>

                <button
                    className="confirm-btn"
                    onClick={() => navigate("/booking-success")}
                >
                    Confirm Booking
                </button>

            </div>

        </div>

    );

}