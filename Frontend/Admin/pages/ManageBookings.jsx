import { useEffect, useMemo, useState } from "react";
import { FaSearch, FaEye, FaEdit, FaTrash } from "react-icons/fa";

function ManageBookings() {
  const [bookings, setBookings] = useState([]);
  const [search, setSearch] = useState("");
  const [loading, setLoading] = useState(true);

  // Fetch Bookings
  useEffect(() => {
    fetch("http://localhost:8080/api/bookings")
      .then((res) => {
        if (!res.ok) {
          throw new Error("Failed to fetch bookings");
        }
        return res.json();
      })
      .then((data) => {
        setBookings(data);
        setLoading(false);
      })
      .catch((err) => {
        console.log(err);
        setLoading(false);
      });
  }, []);

  // Delete Booking
  const deleteBooking = async (id) => {
    if (!window.confirm("Delete this booking?")) return;

    try {
      const res = await fetch(`http://localhost:8080/api/bookings/${id}`, {
        method: "DELETE",
      });

      if (res.ok) {
        setBookings((prev) => prev.filter((booking) => booking.id !== id));
      }
    } catch (err) {
      console.log(err);
    }
  };

  // Search Filter
  const filteredBookings = useMemo(() => {
    return bookings.filter((booking) => {
      return (
        booking.customer?.toLowerCase().includes(search.toLowerCase()) ||
        booking.destination?.toLowerCase().includes(search.toLowerCase())
      );
    });
  }, [bookings, search]);

  return (
    <div className="flex-1 bg-[#F8F7FF] min-h-screen p-8">

      {/* Header */}
      <div className="flex justify-between items-center mb-8">
        <h1 className="text-3xl font-bold text-purple-700">
          Manage Bookings
        </h1>

        <button className="bg-purple-600 hover:bg-purple-700 text-white px-5 py-3 rounded-xl">
          + Add Booking
        </button>
      </div>

      {/* Search */}
      <div className="bg-white p-5 rounded-2xl shadow mb-6">
        <div className="relative">

          <FaSearch className="absolute left-4 top-4 text-gray-400" />

          <input
            type="text"
            placeholder="Search bookings..."
            value={search}
            onChange={(e) => setSearch(e.target.value)}
            className="w-full pl-12 py-3 border rounded-xl outline-none focus:border-purple-600"
          />

        </div>
      </div>

      {/* Booking Table */}
      <div className="bg-white rounded-2xl shadow overflow-hidden">

        <table className="w-full">

          <thead className="bg-purple-100">
            <tr>
              <th className="py-4">Booking ID</th>
              <th>Customer</th>
              <th>Destination</th>
              <th>Date</th>
              <th>Amount</th>
              <th>Status</th>
              <th>Actions</th>
            </tr>
          </thead>

          <tbody>

            {loading ? (
              <tr>
                <td colSpan="7" className="py-8 text-center">
                  Loading bookings...
                </td>
              </tr>
            ) : filteredBookings.length === 0 ? (
              <tr>
                <td colSpan="7" className="py-8 text-center">
                  No bookings found.
                </td>
              </tr>
            ) : (
              filteredBookings.map((booking) => (

                <tr
                  key={booking.id}
                  className="border-b text-center hover:bg-gray-50"
                >

                  <td className="py-4">{booking.id}</td>

                  <td>{booking.customer}</td>

                  <td>{booking.destination}</td>

                  <td>{booking.date}</td>

                  <td>₹{booking.amount}</td>

                  <td>

                    <span
                      className={`px-3 py-1 rounded-full text-sm font-semibold ${
                        booking.status === "Confirmed"
                          ? "bg-green-100 text-green-700"
                          : booking.status === "Pending"
                          ? "bg-yellow-100 text-yellow-700"
                          : "bg-red-100 text-red-700"
                      }`}
                    >
                      {booking.status}
                    </span>

                  </td>

                  <td>

                    <div className="flex justify-center gap-2">

                      <button
                        onClick={() => alert(`Booking ID: ${booking.id}`)}
                        className="bg-green-500 hover:bg-green-600 text-white p-2 rounded-lg"
                      >
                        <FaEye />
                      </button>

                      <button className="bg-blue-500 hover:bg-blue-600 text-white p-2 rounded-lg">
                        <FaEdit />
                      </button>

                      <button
                        onClick={() => deleteBooking(booking.id)}
                        className="bg-red-500 hover:bg-red-600 text-white p-2 rounded-lg"
                      >
                        <FaTrash />
                      </button>

                    </div>

                  </td>

                </tr>

              ))
            )}

          </tbody>

        </table>

      </div>

    </div>
  );
}

export default ManageBookings;
