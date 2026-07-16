import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import {
  FaPlus,
  FaEdit,
  FaTrash,
  FaSearch,
  FaClock,
  FaStar,
} from "react-icons/fa";

function ManagePackages() {
  const navigate = useNavigate();

  const [packages, setPackages] = useState([]);
  const [search, setSearch] = useState("");
  const [statusFilter, setStatusFilter] = useState("All");

  useEffect(() => {
    const savedPackages =
      JSON.parse(localStorage.getItem("packages")) || [];

    setPackages(savedPackages);
  }, []);

  const filteredPackages = packages.filter((pkg) => {
    const matchesSearch =
      pkg.title.toLowerCase().includes(search.toLowerCase()) ||
      pkg.destination.toLowerCase().includes(search.toLowerCase());

    const matchesStatus =
      statusFilter === "All" || pkg.status === statusFilter;

    return matchesSearch && matchesStatus;
  });

  const handleDelete = (id) => {
    if (window.confirm("Delete this package?")) {
      const updatedPackages = packages.filter(
        (pkg) => pkg.id !== id
      );

      setPackages(updatedPackages);

      localStorage.setItem(
        "packages",
        JSON.stringify(updatedPackages)
      );
    }
  };

  return (
    <div className="flex-1 min-h-screen bg-[#F8F7FF] p-8">

      {/* Header */}

      <div className="flex justify-between items-center mb-8">

        <div>

          <h1 className="text-4xl font-bold text-purple-700">
            Manage Packages
          </h1>

          <p className="text-gray-500 mt-2">
            Total Packages : {packages.length}
          </p>

        </div>

        <button
          onClick={() => navigate("/add-package")}
          className="flex items-center gap-2 bg-purple-600 hover:bg-purple-700 text-white px-6 py-3 rounded-xl shadow-lg"
        >
          <FaPlus />
          Add Package
        </button>

      </div>

      {/* Search & Filter */}

      <div className="bg-white rounded-2xl shadow-md p-5 mb-8 flex flex-col md:flex-row gap-4">

        <div className="relative flex-1">

          <FaSearch className="absolute left-4 top-4 text-gray-400" />

          <input
            type="text"
            placeholder="Search package..."
            value={search}
            onChange={(e) => setSearch(e.target.value)}
            className="w-full pl-12 py-3 border rounded-xl focus:outline-none focus:border-purple-600"
          />

        </div>

        <select
          value={statusFilter}
          onChange={(e) => setStatusFilter(e.target.value)}
          className="border rounded-xl px-5 py-3 focus:outline-none focus:border-purple-600"
        >
          <option>All</option>
          <option>Active</option>
          <option>Inactive</option>
        </select>

      </div>
            {/* Package Cards */}

      {filteredPackages.length === 0 ? (

        <div className="bg-white rounded-2xl shadow-md p-10 text-center">

          <h2 className="text-2xl font-semibold text-gray-700">
            No Packages Found
          </h2>

          <p className="text-gray-500 mt-2">
            Click "Add Package" to create your first package.
          </p>

        </div>

      ) : (

        <div className="grid grid-cols-1 md:grid-cols-2 xl:grid-cols-3 gap-8">

          {filteredPackages.map((pkg) => (

            <div
              key={pkg.id}
              className="bg-white rounded-3xl overflow-hidden shadow-lg hover:shadow-2xl hover:-translate-y-2 transition-all duration-300"
            >

              {/* Image */}

              <img
                src={pkg.image}
                alt={pkg.title}
                className="w-full h-56 object-cover"
              />

              {/* Content */}

              <div className="p-6">

                <div className="flex justify-between items-center">

                  <h2 className="text-2xl font-bold">
                    {pkg.title}
                  </h2>

                  <span
                    className={`px-3 py-1 rounded-full text-sm font-semibold ${
                      pkg.status === "Active"
                        ? "bg-green-100 text-green-700"
                        : "bg-red-100 text-red-700"
                    }`}
                  >
                    {pkg.status}
                  </span>

                </div>

                <p className="text-gray-500 mt-2">
                  {pkg.destination}
                </p>

                <div className="flex items-center gap-2 mt-4 text-gray-600">

                  <FaClock className="text-purple-600" />

                  <span>{pkg.duration}</span>

                </div>

                <div className="flex items-center gap-2 mt-3">

                  <FaStar className="text-yellow-500" />

                  <span className="font-semibold">
                    {pkg.rating}
                  </span>

                </div>

                <div className="mt-5">

                  <p className="text-3xl font-bold text-purple-700">
                    {pkg.price}
                  </p>

                </div>

                <div className="mt-4">

                  <p className="text-gray-600 line-clamp-3">
                    {pkg.description}
                  </p>

                </div>

                {/* Buttons */}

                <div className="flex gap-3 mt-6">

                  <button
                    onClick={() =>
                      navigate(`/edit-package/${pkg.id}`)
                    }
                    className="flex-1 flex justify-center items-center gap-2 bg-blue-500 hover:bg-blue-600 text-white py-3 rounded-xl transition"
                  >
                    <FaEdit />
                    Edit
                  </button>

                  <button
                    onClick={() => handleDelete(pkg.id)}
                    className="flex-1 flex justify-center items-center gap-2 bg-red-500 hover:bg-red-600 text-white py-3 rounded-xl transition"
                  >
                    <FaTrash />
                    Delete
                  </button>

                </div>

              </div>

            </div>

          ))}

        </div>

      )}
          </div>
  );
}

export default ManagePackages;