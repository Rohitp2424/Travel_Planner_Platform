import "../styles/Hero.css";
import { motion } from "framer-motion";
import {
  FaMapMarkerAlt,
  FaCalendarAlt,
  FaUserFriends,
  FaSearch,
} from "react-icons/fa";

import Travel from "../assets/Travel.jpeg";

export default function Hero() {
  return (
    <section className="hero">

      <div className="hero-bg-circle one"></div>
      <div className="hero-bg-circle two"></div>

      <div className="hero-container">

        <motion.div
          className="hero-left"
          initial={{ x: -80, opacity: 0 }}
          animate={{ x: 0, opacity: 1 }}
          transition={{ duration: 0.8 }}
        >
          <span className="hero-small">
            Explore the World
          </span>

          <h1>
            Your <span>Journey,</span>
            <br />
            Your <span>Vibe!</span>
          </h1>

          <p>
            Plan unforgettable trips, discover amazing places and
            create memories that last forever.
          </p>

          <button className="explore-btn">
            Explore Now →
          </button>
        </motion.div>

        <motion.div
          className="hero-right"
          animate={{ y: [0, -12, 0] }}
          transition={{
            repeat: Infinity,
            duration: 5,
          }}
        >
          <img
            src={Travel}
            alt="Travel Illustration"
          />
        </motion.div>

      </div>

      <div className="search-box">

        <div className="search-item">
          <FaMapMarkerAlt />
          <div>
            <h4>Where to?</h4>
            <p>Destination</p>
          </div>
        </div>

        <div className="search-item">
          <FaCalendarAlt />
          <div>
            <h4>Check-in</h4>
            <p>Select Date</p>
          </div>
        </div>

        <div className="search-item">
          <FaCalendarAlt />
          <div>
            <h4>Check-out</h4>
            <p>Select Date</p>
          </div>
        </div>

        <div className="search-item">
          <FaUserFriends />
          <div>
            <h4>Travellers</h4>
            <p>2 Adults</p>
          </div>
        </div>

        <button className="search-btn">
          <FaSearch />
          Search
        </button>

      </div>

    </section>
  );
}