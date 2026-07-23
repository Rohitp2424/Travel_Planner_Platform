import "../styles/Destinations.css";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { getAllDestinations } from "../services/DestinationService";
import {
  FaSearch,
  FaFilter,
  FaStar,
  FaPlaneDeparture
} from "react-icons/fa";

export default function Destinations() {

  const navigate = useNavigate();

  const [destinations, setDestinations] = useState([]);

  const [search, setSearch] = useState("");

  useEffect(() => {
    loadDestinations();
  }, []);

  const loadDestinations = async () => {

    try {

      const response = await getAllDestinations();

      setDestinations(response.data);

    } catch (error) {

      console.log(error);

    }

  };

  const filteredDestinations = destinations.filter((item) =>
    item.name.toLowerCase().includes(search.toLowerCase()) ||
    item.country.toLowerCase().includes(search.toLowerCase())
  );
  return (

    <div className="destinations-page">

      {/* Header */}

      <div className="destination-header">

        <div className="logo">

          <FaPlaneDeparture className="logo-icon"/>

          <h2>TripVibe</h2>

        </div>

        <button className="menu-btn">
          ☰
        </button>

      </div>

      <h1 className="title">
        Destinations
      </h1>

      {/* Search */}

      <div className="search-filter">

        <div className="destination-search-box">

          <FaSearch className="destination-search-icon"/>

          <input
            type="text"
            placeholder="Search destinations..."
            value={search}
            onChange={(e)=>setSearch(e.target.value)}
          />

        </div>

        <button className="filter-btn">

          <FaFilter/>

          Filter

        </button>

      </div>

      {/* Categories */}

      <div className="category-buttons">

        <button className="active">All</button>

        <button>Beach</button>

        <button>Mountains</button>

        <button>Cities</button>

        <button>Adventure</button>

      </div>

      {/* Cards */}

      <div className="destination-grid">

        {

          filteredDestinations.map((destination)=>(

            <div
              className="destination-card"
              key={destination.id}
              onClick={()=>navigate(`/destination/${destination.id}`)}
            >

              <img
                src={destination.imageUrl}
                alt={destination.name}
              />

              <div className="card-content">

                <h3>{destination.name}</h3>

                <p>{destination.country}</p>

                <div className="rating">

                  <FaStar className="star"/>

                  {destination.rating}

                </div>

                <div className="price">

                  ₹ {destination.price}

                </div>

                <button className="details-btn">

                  View Details

                </button>

              </div>

            </div>

          ))

        }

      </div>

    </div>

  );

}