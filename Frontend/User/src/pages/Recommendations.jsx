import "../styles/Recommendations.css";
import { useState } from "react";

export default function Recommendations() {

    const [activeTab, setActiveTab] = useState("Activities");

    const recommendations = [

        {
            id: 1,
            image: "https://images.unsplash.com/photo-1537996194471-e657df975ab4?w=600",
            title: "Tanah Lot Temple",
            subtitle: "A must visit temple"
        },

        {
            id: 2,
            image: "https://images.unsplash.com/photo-1507525428034-b723cf961d3e?w=600",
            title: "Water Sports",
            subtitle: "Experience adventure"
        },

        {
            id: 3,
            image: "https://images.unsplash.com/photo-1518509562904-e7ef99cdcc86?w=600",
            title: "Ubud Monkey Forest",
            subtitle: "Explore nature & wildlife"
        },

        {
            id: 4,
            image: "https://images.unsplash.com/photo-1527631746610-bca00a040d60?w=600",
            title: "Bali Swing",
            subtitle: "Beautiful viewpoints"
        }

    ];

    return (

        <div className="recommendations-page">

            <h1>Recommendations</h1>

            <div className="tabs">

                <button
                    className={activeTab === "Activities" ? "recommend-active" : ""}
                    onClick={() => setActiveTab("Activities")}
                >
                    Activities
                </button>

                <button
                    className={activeTab === "Restaurants" ? "active" : ""}
                    onClick={() => setActiveTab("Restaurants")}
                >
                    Restaurants
                </button>

                <button
                    className={activeTab === "Places" ? "active" : ""}
                    onClick={() => setActiveTab("Places")}
                >
                    Places
                </button>

                <button
                    className={activeTab === "Stay" ? "active" : ""}
                    onClick={() => setActiveTab("Stay")}
                >
                    Stay
                </button>

            </div>

            <div className="recommend-grid">
                {

                    recommendations.map((item) => (

                        <div
                            className="recommend-card"
                            key={item.id}
                        >

                            <img
                                src={item.image}
                                alt={item.title}
                                className="recommend-image"
                            />

                            <div className="recommend-content">

                                <span className="recommend-tag">

                                    {activeTab}

                                </span>

                                <h3>

                                    {item.title}

                                </h3>

                                <p>

                                    {item.subtitle}

                                </p>

                                <button className="view-btn">

                                    View Details

                                </button>

                            </div>

                        </div>

                    ))

                }

            </div>
            <div className="featured-recommendation">

                <h2>⭐ Featured Recommendation</h2>

                <div className="featured-card">

                    <img
                        src="https://images.unsplash.com/photo-1507525428034-b723cf961d3e?w=1200"
                        alt="Featured"
                    />

                    <div className="featured-content">

                        <h3>Explore the Beauty of Bali</h3>

                        <p>
                            Discover stunning beaches, ancient temples,
                            breathtaking waterfalls, exciting adventure
                            activities and unforgettable sunsets.
                        </p>

                        <button className="explore-btn">
                            Explore Now
                        </button>

                    </div>

                </div>

            </div>

            <div className="recommend-footer">

                <button className="view-more-btn">

                    View More Recommendations →

                </button>

            </div>

        </div>

    );

}