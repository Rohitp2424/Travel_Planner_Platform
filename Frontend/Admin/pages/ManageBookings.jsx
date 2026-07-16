import { FaSearch, FaEye, FaEdit, FaTrash } from "react-icons/fa";

const bookings = [
  {
    id: 101,
    customer: "Sanika Akhade",
    destination: "Bali",
    date: "12 Jul 2026",
    amount: "₹25,000",
    status: "Confirmed",
  },
  {
    id: 102,
    customer: "Rahul Sharma",
    destination: "Dubai",
    date: "18 Jul 2026",
    amount: "₹42,000",
    status: "Pending",
  },
  {
    id: 103,
    customer: "Priya Verma",
    destination: "Goa",
    date: "22 Jul 2026",
    amount: "₹15,500",
    status: "Cancelled",
  },
];

function ManageBookings() {
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

            {bookings.map((booking) => (

              <tr
                key={booking.id}
                className="border-b text-center hover:bg-gray-50"
              >

                <td className="py-4">{booking.id}</td>

                <td>{booking.customer}</td>

                <td>{booking.destination}</td>

                <td>{booking.date}</td>

                <td>{booking.amount}</td>

                <td>

                  <span
                    className={`px-3 py-1 rounded-full text-sm font-semibold
                    ${
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

                    <button className="bg-green-500 hover:bg-green-600 text-white p-2 rounded-lg">
                      <FaEye />
                    </button>

                    <button className="bg-blue-500 hover:bg-blue-600 text-white p-2 rounded-lg">
                      <FaEdit />
                    </button>

                    <button className="bg-red-500 hover:bg-red-600 text-white p-2 rounded-lg">
                      <FaTrash />
                    </button>

                  </div>

                </td>

              </tr>

            ))}

          </tbody>

        </table>

      </div>

    </div>
  );
}

export default ManageBookings;