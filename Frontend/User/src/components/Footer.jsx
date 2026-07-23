import "../styles/Footer.css";
import {
  FaFacebookF,
  FaInstagram,
  FaTwitter,
  FaLinkedinIn,
  FaPlaneDeparture
} from "react-icons/fa";

function Footer() {
  return (

    <footer className="footer">

      <div className="footer-top">

        <div className="footer-logo">

          <FaPlaneDeparture />

          <h2>TripVibe</h2>

        </div>

        <p>
          Discover amazing destinations and plan your dream journey with TripVibe.
        </p>

      </div>

      <div className="footer-links">

        <div>

          <h3>Company</h3>

          <a href="/">About</a>

          <a href="/">Careers</a>

          <a href="/">Contact</a>

        </div>

        <div>

          <h3>Support</h3>

          <a href="/">Help Center</a>

          <a href="/">Privacy Policy</a>

          <a href="/">Terms</a>

        </div>

        <div>

          <h3>Follow Us</h3>

          <div className="social-icons">

            <FaFacebookF />

            <FaInstagram />

            <FaTwitter />

            <FaLinkedinIn />

          </div>

        </div>

      </div>

      <div className="copyright">

        © 2026 TripVibe. All Rights Reserved.

      </div>

    </footer>

  );
}

export default Footer;