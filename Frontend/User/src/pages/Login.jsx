import "../styles/Login.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { loginUser } from "../services/UserService";

import {
  FaPlane,
  FaEnvelope,
  FaLock,
  FaEye,
  FaEyeSlash,
  FaFacebook
} from "react-icons/fa";

import { FcGoogle } from "react-icons/fc";

export default function Login() {

  const navigate = useNavigate();

  const [showPassword, setShowPassword] = useState(false);
  const [isAdmin, setIsAdmin] = useState(false);

  const [formData, setFormData] = useState({
    email: "",
    password: ""
  });

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleLogin = async (e) => {
    e.preventDefault();

    try {

      const response = await loginUser(
        formData.email,
        formData.password
      );

      alert("Login Successful!");

      console.log(response.data);

      // Store logged in user
      localStorage.setItem(
    "user",
    JSON.stringify(response.data)
);

localStorage.setItem(
    "email",
    response.data.email
);

// Redirect
navigate("/dashboard");

    } catch (error) {

      console.log(error);

      alert("Invalid Email or Password");

    }
  };

  return (

    <div className="login-page">

      <div className="login-card">

        <div className="plane-icon">
          <FaPlane />
        </div>

        <h1>Welcome Back!</h1>

        <p>Login to continue your journey</p>

        <div className="login-toggle">

          <button
            type="button"
            className={!isAdmin ? "active" : ""}
            onClick={() => setIsAdmin(false)}
          >
            User Login
          </button>

          <button
            type="button"
            className={isAdmin ? "active" : ""}
            onClick={() => setIsAdmin(true)}
          >
            Admin Login
          </button>

        </div>

        <form className="login-form" onSubmit={handleLogin}>

          <div className="input-group">

            <label>Email Address</label>

            <div className="input-box">

              <FaEnvelope />

              <input
                type="email"
                name="email"
                placeholder="Enter your email"
                value={formData.email}
                onChange={handleChange}
                required
              />

            </div>

          </div>

          <div className="input-group">

            <label>Password</label>

            <div className="input-box">

              <FaLock />

              <input
                type={showPassword ? "text" : "password"}
                name="password"
                placeholder="Enter your password"
                value={formData.password}
                onChange={handleChange}
                required
              />

              <span
                className="password-toggle"
                onClick={() =>
                  setShowPassword(!showPassword)
                }
              >
                {showPassword ? <FaEyeSlash /> : <FaEye />}
              </span>

            </div>

          </div>

          <div className="login-options">

            <label className="remember">

              <input type="checkbox" />

              Remember Me

            </label>

            <a href="/" className="forgot">

              Forgot Password?

            </a>

          </div>

          <button
            type="submit"
            className="login-btn"
          >
            Login →
          </button>

        </form>

        <div className="divider">
          or continue with
        </div>

        <div className="social-buttons">

          <button className="social-btn">

            <FcGoogle size={26} />

            Google

          </button>

          <button className="social-btn">

            <FaFacebook
              size={24}
              color="#1877F2"
            />

            Facebook

          </button>

        </div>

        <div className="register-text">

          Don't have an account?

          <a href="/register">

            Register here

          </a>

        </div>

      </div>

    </div>

  );

}