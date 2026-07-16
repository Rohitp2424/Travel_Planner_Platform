import { FaBell, FaSearch } from "react-icons/fa";

function Navbar() {
  return (
    <div className="flex justify-between items-center bg-white px-8 py-4 rounded-2xl shadow-md">

      {/* Search */}

      <div className="relative w-96">

        <FaSearch className="absolute left-4 top-4 text-gray-400" />

        <input
          type="text"
          placeholder="Search..."
          className="w-full pl-12 pr-4 py-3 rounded-xl border border-gray-200 outline-none focus:border-purple-600"
        />

      </div>

      {/* Right Side */}

      <div className="flex items-center gap-6">

        <button className="relative">

          <FaBell className="text-2xl text-purple-700" />

          <span className="absolute -top-1 -right-1 h-3 w-3 rounded-full bg-red-500"></span>

        </button>

        <div className="flex items-center gap-3">

          <img
            src="https://i.pravatar.cc/50"
            alt="Admin"
            className="h-12 w-12 rounded-full"
          />

          <div>

            <h2 className="font-semibold">Admin</h2>

            <p className="text-sm text-gray-500">
              Administrator
            </p>

          </div>

        </div>

      </div>

    </div>
  );
}

export default Navbar;