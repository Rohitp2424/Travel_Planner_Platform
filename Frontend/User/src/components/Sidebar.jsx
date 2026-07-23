import "./Sidebar.css";
import { useNavigate } from "react-router-dom";

import {
  FaHome,
  FaMapMarkedAlt,
  FaPlaneDeparture,
  FaSuitcase,
  FaWallet,
  FaStar,
  FaHeart,
  FaBell,
  FaUser,
  FaCog,
  FaSignOutAlt
} from "react-icons/fa";

export default function Sidebar() {

  const navigate = useNavigate();

  return (

    <div className="sidebar">

      <div className="sidebar-logo">

        <FaPlaneDeparture className="sidebar-logo-icon" />

        <h2>TripVibe</h2>

      </div>

      <ul>

        <li
          className="active"
          onClick={() => navigate("/dashboard")}
        >
          <FaHome />
          <span>Dashboard</span>
        </li>

        <li
          onClick={() => navigate("/destinations")}
        >
          <FaMapMarkedAlt />
          <span>Destinations</span>
        </li>

        <li
          onClick={() => navigate("/plan-trip")}
        >
          <FaPlaneDeparture />
          <span>Plan Trip</span>
        </li>

        <li
          onClick={() => navigate("/my-bookings")}
        >
          <FaSuitcase />
          <span>My Bookings</span>
        </li>

        <li
          onClick={() => navigate("/budget-tracker")}
        >
          <FaWallet />
          <span>Budget Tracker</span>
        </li>
        <li
    onClick={() => navigate("/recommendations")}
>
    <FaStar />
    <span>Recommendations</span>
</li>

        <li
          onClick={() => navigate("/wishlist")}
        >
          <FaHeart />
          <span>Wishlist</span>
        </li>

        <li
          onClick={() => navigate("/notifications")}
        >
          <FaBell />
          <span>Notifications</span>
        </li>

        <li
          onClick={() => navigate("/profile")}
        >
          <FaUser />
          <span>Profile</span>
        </li>

        <li
          onClick={() => navigate("/settings")}
        >
          <FaCog />
          <span>Settings</span>
        </li>

      </ul>

      <button
        className="logout-btn"
        onClick={() => navigate("/")}
      >
        <FaSignOutAlt />
        Logout
      </button>

    </div>

  );

}