import "../styles/MyBookings.css";
import { useNavigate } from "react-router-dom";
import { FaSuitcase, FaMapMarkerAlt, FaCalendarAlt } from "react-icons/fa";

export default function MyBookings() {

    const navigate = useNavigate();

    const bookings = [

        {
            id: "TV2026BK1025",
            destination: "Bali, Indonesia",
            date: "15 Aug 2026 - 20 Aug 2026",
            status: "Upcoming"
        },

        {
            id: "TV2026BK1026",
            destination: "Paris, France",
            date: "05 Sep 2026 - 10 Sep 2026",
            status: "Completed"
        },

        {
            id: "TV2026BK1027",
            destination: "Dubai, UAE",
            date: "25 Oct 2026 - 30 Oct 2026",
            status: "Cancelled"
        }

    ];

    return (

        <div className="bookings-page">

            <h1>My Bookings</h1>

            <div className="bookings-container">

                {

                    bookings.map((booking, index) => (

                        <div
                            className="booking-card"
                            key={index}
                        >

                            <div className="booking-header">

                                <FaSuitcase className="booking-icon"/>

                                <h2>{booking.id}</h2>

                            </div>

                            <div className="booking-info">

                                <FaMapMarkerAlt/>

                                <span>{booking.destination}</span>

                            </div>

                            <div className="booking-info">

                                <FaCalendarAlt/>

                                <span>{booking.date}</span>

                            </div>

                            <div className={`status ${booking.status.toLowerCase()}`}>

                                {booking.status}

                            </div>
                            <div className="booking-buttons">

                                <button
                                    className="details-btn"
                                    onClick={() => navigate("/destination-details")}
                                >
                                    View Details
                                </button>

                                <button
                                    className="download-btn"
                                >
                                    Download Ticket
                                </button>

                            </div>

                        </div>

                    ))

                }

            </div>

        </div>

    );

}