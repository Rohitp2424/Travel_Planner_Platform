import "../styles/Dashboard.css";
import Sidebar from "../components/Sidebar";
import { useEffect, useState } from "react";
import { getProfile } from "../services/UserService";

export default function Dashboard() {

  const [user, setUser] = useState({});

  useEffect(() => {

    const email = localStorage.getItem("email");

    if (email) {

      getProfile(email)
        .then((response) => {
          setUser(response.data);
        })
        .catch((error) => {
          console.log(error);
        });

    }

  }, []);

  return (

    <div className="dashboard-page">

      <Sidebar />

      <div className="dashboard-content">

        <div className="top-bar">

          <div>

            <h1>
              Welcome back, {user.fullName || user.name || "User"} 👋
            </h1>

            <p>Where do you want to explore today?</p>

          </div>

          <div className="profile">

            🔔

            <img
              src="https://i.pravatar.cc/50"
              alt="Profile"
            />

          </div>

        </div>

        <div className="dashboard-search-box">

          <input
            type="text"
            placeholder="Search destinations, countries..."
          />

          <button>🔍</button>

        </div>

        <h2>Upcoming Trip</h2>

        <div className="trip-card">

          <img
            src="https://images.unsplash.com/photo-1499856871958-5b9627545d1"
            alt="Paris"
          />

          <div>

            <h3>Paris, France</h3>

            <p>20 Jun - 24 Jun 2024</p>

            <span className="status">
              Confirmed
            </span>

          </div>

          <button className="view-btn">
            View Trip
          </button>

        </div>

        <h2>Popular Destinations</h2>

        <div className="destination-grid">

          <div className="card">

            <img
              src="https://picsum.photos/250/150?1"
              alt="Bali"
            />

            <h4>Bali</h4>

            ⭐4.8

          </div>

          <div className="card">

            <img
              src="https://picsum.photos/250/150?2"
              alt="Santorini"
            />

            <h4>Santorini</h4>

            ⭐4.9

          </div>

          <div className="card">

            <img
              src="https://picsum.photos/250/150?3"
              alt="Dubai"
            />

            <h4>Dubai</h4>

            ⭐4.7

          </div>

          <div className="card">

            <img
              src="https://picsum.photos/250/150?4"
              alt="Maldives"
            />

            <h4>Maldives</h4>

            ⭐4.9

          </div>

        </div>

      </div>

    </div>

  );

}