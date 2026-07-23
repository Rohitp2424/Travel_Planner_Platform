import "../styles/Itinerary.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import ProgressBar from "../components/ProgressBar";

export default function Itinerary() {

    const navigate = useNavigate();

    const [activities] = useState([
        {
            time: "09:00 AM",
            activity: "Breakfast at Hotel"
        },
        {
            time: "11:00 AM",
            activity: "Temple Visit"
        },
        {
            time: "02:00 PM",
            activity: "Lunch at Local Restaurant"
        },
        {
            time: "05:00 PM",
            activity: "Beach Walk"
        },
        {
            time: "08:00 PM",
            activity: "Dinner"
        }
    ]);

    return (

        <div className="itinerary-page">

            <ProgressBar currentStep={2} />

            <div className="itinerary-card">

                <h2>Itinerary Builder</h2>

                <div className="day-tabs">

                    <button className="active-day">Day 1</button>
                    <button>Day 2</button>
                    <button>Day 3</button>
                    <button>Day 4</button>
                    <button>Day 5</button>

                </div>

                <h3>Day 1 - Arrival</h3>

                <div className="timeline">

                    {activities.map((item, index) => (

                        <div
                            className="timeline-item"
                            key={index}
                        >

                            <div className="time">

                                {item.time}

                            </div>

                            <div className="activity">

                                {item.activity}

                            </div>

                        </div>

                    ))}

                </div>
                <div className="button-group">

                    <button className="add-btn">

                        + Add Activity

                    </button>

                    <button className="add-btn">

                        + Add Day

                    </button>

                </div>

                <button
                    className="next-btn"
                    onClick={() => navigate("/flights")}
                >
                    Next: Flights →
                </button>

            </div>

        </div>

    );

}