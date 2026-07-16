import { NavLink } from "react-router-dom";
import {
  FaPlane,
  FaTachometerAlt,
  FaUsers,
  FaSuitcase,
  FaMapMarkedAlt,
  FaBoxOpen,
  FaStar,
  FaChartBar,
  FaUserCircle,
  FaSignOutAlt,
} from "react-icons/fa";

import traveler from "../assets/traveler.png"; // Make sure this image exists

const menuItems = [
  { name: "Dashboard", path: "/dashboard", icon: <FaTachometerAlt /> },
  { name: "Users", path: "/users", icon: <FaUsers /> },
  { name: "Bookings", path: "/bookings", icon: <FaSuitcase /> },
  { name: "Destinations", path: "/destinations", icon: <FaMapMarkedAlt /> },
  { name: "Packages", path: "/packages", icon: <FaBoxOpen /> },
  { name: "Reviews", path: "/reviews", icon: <FaStar /> },
  { name: "Reports", path: "/reports", icon: <FaChartBar /> },
  { name: "Profile", path: "/profile", icon: <FaUserCircle /> },
];

function Sidebar() {
  return (
    <aside className="relative w-72 min-h-screen overflow-hidden bg-gradient-to-b from-[#24115d] via-[#5423c8] to-[#b84cff] text-white flex flex-col justify-between shadow-2xl">

      {/* Decorative Dots */}
      <div className="absolute top-16 left-10 w-2 h-2 bg-white rounded-full opacity-40"></div>
      <div className="absolute top-40 right-8 w-1.5 h-1.5 bg-white rounded-full opacity-60"></div>
      <div className="absolute bottom-64 left-8 w-2 h-2 bg-white rounded-full opacity-30"></div>
      <div className="absolute bottom-40 right-10 w-1.5 h-1.5 bg-white rounded-full opacity-50"></div>

      {/* Top Section */}
      <div>

        {/* Logo */}
        <div className="px-8 py-8 border-b border-white/10">

          <div className="flex items-center gap-4">

            <div className="h-14 w-14 rounded-full bg-white flex items-center justify-center shadow-lg">

              <FaPlane className="text-purple-700 text-2xl -rotate-45" />

            </div>

            <div>

              <h1 className="text-3xl font-bold">
                Travel Admin
              </h1>

              <p className="text-purple-100 text-sm">
                Explore. Manage. Grow.
              </p>

            </div>

          </div>

        </div>

        {/* Menu */}
        <nav className="mt-8 px-5 space-y-3">

          {menuItems.map((item) => (

            <NavLink
              key={item.path}
              to={item.path}
              className={({ isActive }) =>
                `flex items-center gap-4 px-5 py-4 rounded-2xl transition-all duration-300 ${
                  isActive
                    ? "bg-white/20 backdrop-blur-md shadow-xl border border-white/20"
                    : "hover:bg-white/10"
                }`
              }
            >
              <span className="text-xl">{item.icon}</span>

              <span className="font-medium text-lg">
                {item.name}
              </span>

            </NavLink>

          ))}

        </nav>

      </div>

      {/* Bottom Section */}
      <div className="px-6 pb-8">

        <img
          src={traveler}
          alt="Traveler"
          className="w-52 mx-auto mb-8"
        />

        <button className="w-full bg-white text-purple-700 rounded-2xl py-4 font-semibold flex items-center justify-center gap-3 hover:scale-105 transition duration-300">

          <FaSignOutAlt />

          Logout

        </button>

      </div>

    </aside>
  );
}

export default Sidebar;