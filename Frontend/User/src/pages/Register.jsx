import "../styles/Register.css";
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { registerUser } from "../services/UserService";

import {
  FaPlane,
  FaUser,
  FaEnvelope,
  FaPhone,
  FaLock,
  FaEye,
  FaFacebook
} from "react-icons/fa";

import { FcGoogle } from "react-icons/fc";

export default function Register() {

  const navigate = useNavigate();

  const [showPassword, setShowPassword] = useState(false);
  const [showConfirmPassword, setShowConfirmPassword] = useState(false);

  const [formData, setFormData] = useState({
    name: "",
    email: "",
    phone: "",
    password: "",
    confirmPassword: ""
  });

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleRegister = async (e) => {
  e.preventDefault();

  if (formData.password !== formData.confirmPassword) {
    alert("Passwords do not match");
    return;
  }

  try {

    await registerUser({
      fullName: formData.name,
      email: formData.email,
      phoneNumber: formData.phone,
      password: formData.password,
      role: "USER"
    });

    alert("Registration Successful!");
    navigate("/login");

  } catch (error) {
    console.log(error.response?.data);
    console.log(error.response);
    alert("Registration Failed");
  }
};

  return (
    <div className="register-page">

      <div className="register-card">

        <div className="register-logo">
          <FaPlane />
        </div>

        <h1>Create Your Account</h1>

        <p className="register-subtitle">
          Join TripVibe and start exploring the world
        </p>

        <form onSubmit={handleRegister}>

          <label>Full Name</label>

          <div className="input-box">
            <FaUser className="input-icon" />

            <input
              type="text"
              name="name"
              placeholder="Enter your full name"
              value={formData.name}
              onChange={handleChange}
              required
            />
          </div>

          <label>Email Address</label>

          <div className="input-box">
            <FaEnvelope className="input-icon" />

            <input
              type="email"
              name="email"
              placeholder="Enter your email"
              value={formData.email}
              onChange={handleChange}
              required
            />
          </div>

          <label>Phone Number</label>

          <div className="input-box">
            <FaPhone className="input-icon" />

            <input
              type="text"
              name="phone"
              placeholder="Enter your phone number"
              value={formData.phone}
              onChange={handleChange}
              required
            />
          </div>

          <label>Password</label>

          <div className="input-box">
            <FaLock className="input-icon" />

            <input
              type={showPassword ? "text" : "password"}
              name="password"
              placeholder="Create a password"
              value={formData.password}
              onChange={handleChange}
              required
            />

            <FaEye
              className="eye-icon"
              onClick={() => setShowPassword(!showPassword)}
            />
          </div>

          <label>Confirm Password</label>

          <div className="input-box">
            <FaLock className="input-icon" />

            <input
              type={showConfirmPassword ? "text" : "password"}
              name="confirmPassword"
              placeholder="Confirm your password"
              value={formData.confirmPassword}
              onChange={handleChange}
              required
            />

            <FaEye
              className="eye-icon"
              onClick={() =>
                setShowConfirmPassword(!showConfirmPassword)
              }
            />
          </div>

          <div className="terms">

            <input type="checkbox" required />

            <span>
              I agree to the
              <a href="/"> Terms & Conditions </a>
              and
              <a href="/"> Privacy Policy</a>
            </span>

          </div>

          <button type="submit" className="register-btn">
            Register →
          </button>

        </form>

        <p className="social-title">
          or sign up with
        </p>

        <div className="social-buttons">

          <button className="social-btn">
            <FcGoogle />
            Google
          </button>

          <button className="social-btn">
            <FaFacebook />
            Facebook
          </button>

        </div>

        <p className="login-text">
          Already have an account?{" "}
          <Link to="/login">
            Login here
          </Link>
        </p>

      </div>

    </div>
  );
}