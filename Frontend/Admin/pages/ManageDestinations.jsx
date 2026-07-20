import { useEffect, useMemo, useState } from "react";
import {
  FaPlus,
  FaEdit,
  FaTrash,
  FaSearch,
  FaMapMarkerAlt,
} from "react-icons/fa";

function ManageDestinations() {
  const [destinations, setDestinations] = useState([]);
  const [search, setSearch] = useState("");
  const [loading, setLoading] = useState(true);

  // Fetch Destinations
  useEffect(() => {
    fetch("http://localhost:8080/api/destinations")
      .then((res) => {
        if (!res.ok) throw new Error("Failed to fetch destinations");
        return res.json();
      })
      .then((data) => {
        setDestinations(data);
        setLoading(false);
      })
      .catch((err) => {
        console.log(err);
        setLoading(false);
      });
  }, []);

  // Delete Destination
  const deleteDestination = async (id) => {
    if (!window.confirm("Delete this destination?")) return;

    try {
      const res = await fetch(
        `http://localhost:8080/api/destinations/${id}`,
        {
          method: "DELETE",
        }
      );

      if (res.ok) {
        setDestinations((prev) =>
          prev.filter((item) => item.id !== id)
        );
      }
    } catch (err) {
      console.log(err);
    }
  };

  // Search
  const filteredDestinations = useMemo(() => {
    return destinations.filter((item) => {
      return (
        item.name?.toLowerCase().includes(search.toLowerCase()) ||
        item.country?.toLowerCase().includes(search.toLowerCase())
      );
    });
  }, [destinations, search]);

  return (
    <div className="flex-1 bg-[#F8F7FF] min-h-screen p-8">

      {/* Header */}
      <div className="flex justify-between items-center mb-8">

        <h1 className="text-3xl font-bold text-purple-700">
          Manage Destinations
        </h1>

        <button className="bg-purple-600 hover:bg-purple-700 text-white px-5 py-3 rounded-xl flex items-center gap-2">
          <FaPlus />
          Add Destination
        </button>

      </div>

      {/* Search */}
      <div className="bg-white p-5 rounded-2xl shadow mb-8">

        <div className="relative">

          <FaSearch className="absolute left-4 top-4 text-gray-400" />

          <input
            type="text"
            placeholder="Search Destination..."
            value={search}
            onChange={(e) => setSearch(e.target.value)}
            className="w-full pl-12 py-3 border rounded-xl outline-none focus:border-purple-600"
          />

        </div>

      </div>

      {/* Cards */}
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">

        {loading ? (
          <div className="col-span-4 text-center text-gray-500 py-10">
            Loading destinations...
          </div>
        ) : filteredDestinations.length === 0 ? (
          <div className="col-span-4 text-center text-gray-500 py-10">
            No destinations found.
          </div>
        ) : (
          filteredDestinations.map((item) => (

            <div
              key={item.id}
              className="bg-white rounded-2xl shadow-lg overflow-hidden hover:shadow-xl transition"
            >

              <img
                src={item.image}
                alt={item.name}
                className="h-52 w-full object-cover"
              />

              <div className="p-5">

                <h2 className="text-2xl font-bold">
                  {item.name}
                </h2>

                <div className="flex items-center text-gray-500 mt-2">

                  <FaMapMarkerAlt className="mr-2 text-purple-600" />

                  {item.country}

                </div>

                <p className="text-purple-700 font-bold text-xl mt-3">
                  ₹{item.price}
                </p>

                <div className="flex justify-between mt-5">

                  <button className="bg-blue-500 hover:bg-blue-600 text-white px-4 py-2 rounded-lg flex items-center gap-2">
                    <FaEdit />
                    Edit
                  </button>

                  <button
                    onClick={() => deleteDestination(item.id)}
                    className="bg-red-500 hover:bg-red-600 text-white px-4 py-2 rounded-lg flex items-center gap-2"
                  >
                    <FaTrash />
                    Delete
                  </button>

                </div>

              </div>

            </div>

          ))
        )}

      </div>

    </div>
  );
}

export default ManageDestinations;
