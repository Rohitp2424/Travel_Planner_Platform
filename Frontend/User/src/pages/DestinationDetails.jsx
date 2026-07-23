import "../styles/DestinationDetails.css";
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { FaArrowLeft, FaStar, FaMapMarkerAlt } from "react-icons/fa";
import { getDestinationById } from "../services/DestinationService";

export default function DestinationDetails() {

    const { id } = useParams();

    const navigate = useNavigate();

    const [destination, setDestination] = useState({});

    useEffect(() => {
        loadDestination();
    }, []);

    const loadDestination = async () => {
        try {
            const response = await getDestinationById(id);
            setDestination(response.data);
        } catch (error) {
            console.log(error);
        }
    };
    return (

        <div className="destination-details-page">

            <button
                className="back-btn"
                onClick={() => navigate(-1)}
            >
                <FaArrowLeft />
                Back
            </button>

            <div className="details-card">

                <div className="image-section">

                    <img
                        src={destination.imageUrl}
                        alt={destination.name}
                    />

                </div>

                <div className="details-section">

                    <h1>{destination.name}</h1>

                    <div className="location">

                        <FaMapMarkerAlt />

                        <span>{destination.country}</span>

                    </div>

                    <div className="rating">

                        <FaStar className="star" />

                        <span>{destination.rating}</span>

                    </div>

                    <h3>Description</h3>

                    <p>{destination.description}</p>

                    <h2 className="price">
                        ₹ {destination.price}
                    </h2>

                    <button
                        className="plan-btn"
                        onClick={() => navigate("/plan-trip")}
                    >
                        Plan Trip
                    </button>

                </div>

            </div>

        </div>

    );

}