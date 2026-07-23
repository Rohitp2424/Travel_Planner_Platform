import "../styles/Wishlist.css";
import { useNavigate } from "react-router-dom";
import { FaHeart, FaStar, FaMapMarkerAlt } from "react-icons/fa";

export default function Wishlist() {

    const navigate = useNavigate();

    const wishlist = [

        {
            id:1,
            name:"Bali",
            country:"Indonesia",
            rating:"4.8",
            price:"₹18,500"
        },

        {
            id:2,
            name:"Paris",
            country:"France",
            rating:"4.7",
            price:"₹30,000"
        },

        {
            id:3,
            name:"Maldives",
            country:"Maldives",
            rating:"4.9",
            price:"₹35,000"
        }

    ];

    return (

        <div className="wishlist-page">

            <h1>My Wishlist</h1>

            <div className="wishlist-container">

                {

                    wishlist.map((item)=>(

                        <div
                            className="wishlist-card"
                            key={item.id}
                        >

                            <div className="wishlist-header">

                                <h2>{item.name}</h2>

                                <FaHeart className="heart-icon"/>

                            </div>

                            <div className="wishlist-info">

                                <FaMapMarkerAlt/>

                                <span>{item.country}</span>

                            </div>

                            <div className="wishlist-info">

                                <FaStar className="star"/>

                                <span>{item.rating}</span>

                            </div>

                            <h3>{item.price}</h3>
                            <div className="wishlist-buttons">

                                <button
                                    className="plan-btn"
                                    onClick={() => navigate("/plan-trip")}
                                >
                                    Plan Trip
                                </button>

                                <button
                                    className="remove-btn"
                                >
                                    Remove
                                </button>

                            </div>

                        </div>

                    ))

                }

            </div>

        </div>

    );

}