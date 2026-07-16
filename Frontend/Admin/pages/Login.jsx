import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { FaEnvelope, FaLock, FaPlaneDeparture } from "react-icons/fa";

function Login() {
  const navigate = useNavigate();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = (e) => {
    e.preventDefault();

    if (email === "admin@gmail.com" && password === "admin123") {
      navigate("/dashboard");
    } else {
      alert("Invalid Email or Password");
    }
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-purple-700 via-purple-600 to-indigo-700 flex items-center justify-center p-6">

      <div className="bg-white w-full max-w-5xl rounded-3xl shadow-2xl overflow-hidden grid md:grid-cols-2">

        {/* Left Section */}

        <div className="hidden md:flex flex-col justify-center items-center bg-gradient-to-br from-purple-800 to-purple-600 text-white p-10">

          <FaPlaneDeparture className="text-7xl mb-6" />

          <h1 className="text-4xl font-bold">
            Travel Planner
          </h1>

          <p className="text-center mt-6 text-purple-100 leading-7">
            Welcome to the Admin Portal.
            <br />
            Manage users, bookings, destinations,
            packages and reports from one dashboard.
          </p>

          <img
            src="https://images.unsplash.com/photo-1507525428034-b723cf961d3e?w=700"
            alt="Travel"
            className="rounded-2xl mt-10 shadow-lg"
          />

        </div>

        {/* Right Section */}

        <div className="p-10 flex flex-col justify-center">

          <h2 className="text-4xl font-bold text-purple-700">
            Admin Login
          </h2>

          <p className="text-gray-500 mt-2">
            Sign in to continue
          </p>

          <form onSubmit={handleLogin} className="mt-10 space-y-6">

            {/* Email */}

            <div>

              <label className="font-medium">
                Email
              </label>

              <div className="flex items-center mt-2 border rounded-xl px-4 py-3 focus-within:border-purple-600">

                <FaEnvelope className="text-gray-400 mr-3" />

                <input
                  type="email"
                  placeholder="admin@gmail.com"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  className="w-full outline-none"
                  required
                />

              </div>

            </div>

            {/* Password */}

            <div>

              <label className="font-medium">
                Password
              </label>

              <div className="flex items-center mt-2 border rounded-xl px-4 py-3 focus-within:border-purple-600">

                <FaLock className="text-gray-400 mr-3" />

                <input
                  type="password"
                  placeholder="Enter Password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  className="w-full outline-none"
                  required
                />

              </div>

            </div>

            {/* Remember Me */}

            <div className="flex justify-between items-center">

              <label className="flex items-center gap-2">
                <input type="checkbox" />
                Remember Me
              </label>

              <button
                type="button"
                className="text-purple-600 hover:underline"
              >
                Forgot Password?
              </button>

            </div>

            {/* Login Button */}

            <button
              type="submit"
              className="w-full bg-purple-600 hover:bg-purple-700 text-white py-3 rounded-xl text-lg font-semibold transition duration-300"
            >
              Login
            </button>

          </form>

        </div>

      </div>

    </div>
  );
}

export default Login;