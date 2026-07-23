import "../styles/Hotels.css";
import { useNavigate } from "react-router-dom";
import { FaHotel, FaStar, FaMapMarkerAlt } from "react-icons/fa";
import ProgressBar from "../components/ProgressBar";

export default function Hotels() {

    const navigate = useNavigate();

    const hotels = [

        {
            name: "Grand Bali Resort",
            location: "Kuta, Bali",
            rating: 4.8,
            price: "₹8,500 / Night"
        },

        {
            name: "Ocean Paradise",
            location: "Seminyak, Bali",
            rating: 4.7,
            price: "₹6,900 / Night"
        },

        {
            name: "Luxury Palm Hotel",
            location: "Ubud, Bali",
            rating: 4.9,
            price: "₹11,200 / Night"
        }

    ];

    return (

        <div className="hotels-page">

            <ProgressBar currentStep={4} />

            <h1>Choose Your Hotel</h1>

            <div className="hotels-container">

                {

                    hotels.map((hotel, index) => (

                        <div
                            className="hotel-card"
                            key={index}
                        >

                            <div className="hotel-header">

                                <FaHotel className="hotel-icon" />

                                <h2>{hotel.name}</h2>

                            </div>

                            <div className="hotel-location">

                                <FaMapMarkerAlt />

                                <span>{hotel.location}</span>

                            </div>

                            <div className="hotel-rating">

                                <FaStar className="star-icon" />

                                <span>{hotel.rating}</span>

                            </div>

                            <div className="hotel-footer">

                                <h2>{hotel.price}</h2>
                                <button className="select-btn">

                                    Select Hotel

                                </button>

                            </div>

                        </div>

                    ))

                }

            </div>

            <button
                className="next-btn"
                onClick={() => navigate("/review-booking")}
            >

                Next: Review →

            </button>

        </div>

    );

}