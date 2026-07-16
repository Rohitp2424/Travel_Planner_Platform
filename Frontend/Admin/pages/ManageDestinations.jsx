import { FaPlus, FaEdit, FaTrash, FaSearch, FaMapMarkerAlt } from "react-icons/fa";

const destinations = [
  {
    id: 1,
    name: "Bali",
    country: "Indonesia",
    price: "₹25,000",
    image: "https://images.unsplash.com/photo-1537996194471-e657df975ab4?w=500",
  },
  {
    id: 2,
    name: "Paris",
    country: "France",
    price: "₹60,000",
    image: "https://images.unsplash.com/photo-1502602898657-3e91760cbb34?w=500",
  },
  {
    id: 3,
    name: "Dubai",
    country: "UAE",
    price: "₹40,000",
    image: "https://images.unsplash.com/photo-1512453979798-5ea266f8880c?w=500",
  },
  {
    id: 4,
    name: "Goa",
    country: "India",
    price: "₹15,000",
    image: "https://images.unsplash.com/photo-1512343879784-a960bf40e7f2?w=500",
  },
];

function ManageDestinations() {
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

          <FaSearch className="absolute left-4 top-4 text-gray-400"/>

          <input
            type="text"
            placeholder="Search Destination..."
            className="w-full pl-12 py-3 border rounded-xl outline-none focus:border-purple-600"
          />

        </div>

      </div>

      {/* Cards */}

      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">

        {destinations.map((item) => (

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

                <FaMapMarkerAlt className="mr-2 text-purple-600"/>

                {item.country}

              </div>

              <p className="text-purple-700 font-bold text-xl mt-3">
                {item.price}
              </p>

              <div className="flex justify-between mt-5">

                <button className="bg-blue-500 hover:bg-blue-600 text-white px-4 py-2 rounded-lg flex items-center gap-2">
                  <FaEdit />
                  Edit
                </button>

                <button className="bg-red-500 hover:bg-red-600 text-white px-4 py-2 rounded-lg flex items-center gap-2">
                  <FaTrash />
                  Delete
                </button>

              </div>

            </div>

          </div>

        ))}

      </div>

    </div>
  );
}

export default ManageDestinations;