import { Link } from "react-router-dom";
import { FaPlaneDeparture } from "react-icons/fa";
import "../styles/Navbar.css";

function Navbar() {
  return (
    <nav className="navbar">

      <div className="logo">
        <FaPlaneDeparture className="logo-icon"/>
        <span>TripVibe</span>
      </div>

      <ul className="nav-links">
        
        
      </ul>

      <div className="nav-buttons">
        <Link to="/login" className="login-btn">
          Login
        </Link>

        <Link to="/register" className="register-btn">
          Register
        </Link>
      </div>

    </nav>
  );
}

export default Navbar;