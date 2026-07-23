import "../styles/BookingSuccess.css";
import { useNavigate } from "react-router-dom";
import { FaCheckCircle } from "react-icons/fa";
import ProgressBar from "../components/ProgressBar";

export default function BookingSuccess() {

    const navigate = useNavigate();

    const bookingId = "TV2026BK1025";

    return (

        <div className="success-page">

            <ProgressBar currentStep={6} />

            <div className="success-card">

                <FaCheckCircle className="success-icon" />

                <h1>Booking Confirmed!</h1>

                <p>

                    Your trip has been booked successfully.

                    We hope you have an amazing journey!

                </p>

                <div className="booking-id">

                    <span>Booking ID</span>

                    <strong>{bookingId}</strong>

                </div>
                <button
                    className="dashboard-btn"
                    onClick={() => navigate("/dashboard")}
                >
                    Back to Dashboard
                </button>

            </div>

        </div>

    );

}