import Navbar from "../components/Navbar";
import StatCard from "../components/StatCard";

import {
  FaUsers,
  FaSuitcase,
  FaMapMarkedAlt,
  FaRupeeSign,
} from "react-icons/fa";

import {
  ResponsiveContainer,
  BarChart,
  Bar,
  PieChart,
  Pie,
  Cell,
  Tooltip,
  Legend,
  CartesianGrid,
  XAxis,
  YAxis,
  LineChart,
  Line,
} from "recharts";
const monthlyData = [
  { month: "Jan", bookings: 40 },
  { month: "Feb", bookings: 55 },
  { month: "Mar", bookings: 70 },
  { month: "Apr", bookings: 90 },
  { month: "May", bookings: 120 },
  { month: "Jun", bookings: 95 },
  { month: "Jul", bookings: 110 },
  { month: "Aug", bookings: 105 },
  { month: "Sep", bookings: 85 },
  { month: "Oct", bookings: 75 },
  { month: "Nov", bookings: 65 },
  { month: "Dec", bookings: 80 },
];
const pieData = [
  {
    name: "Confirmed",
    value: 642,
  },
  {
    name: "Pending",
    value: 312,
  },
  {
    name: "Cancelled",
    value: 168,
  },
  {
    name: "Completed",
    value: 123,
  },
];

const COLORS = [
  "#7C3AED",
  "#F59E0B",
  "#EF4444",
  "#22C55E",
];
const revenueData = [
  { month: "Jan", revenue: 5 },
  { month: "Feb", revenue: 8 },
  { month: "Mar", revenue: 10 },
  { month: "Apr", revenue: 9 },
  { month: "May", revenue: 12 },
  { month: "Jun", revenue: 15 },
  { month: "Jul", revenue: 13 },
  { month: "Aug", revenue: 18 },
  { month: "Sep", revenue: 16 },
  { month: "Oct", revenue: 20 },
  { month: "Nov", revenue: 19 },
  { month: "Dec", revenue: 22 },
];
const destinations = [
  {
    id: 1,
    name: "Bali",
    country: "Indonesia",
    bookings: 210,
    image:
      "https://images.unsplash.com/photo-1537996194471-e657df975ab4?w=500",
  },
  {
    id: 2,
    name: "Dubai",
    country: "UAE",
    bookings: 185,
    image:
      "https://images.unsplash.com/photo-1512453979798-5ea266f8880c?w=500",
  },
  {
    id: 3,
    name: "Paris",
    country: "France",
    bookings: 165,
    image:
      "https://images.unsplash.com/photo-1502602898657-3e91760cbb34?w=500",
  },
  {
    id: 4,
    name: "Maldives",
    country: "Maldives",
    bookings: 142,
    image:
      "https://images.unsplash.com/photo-1573843981267-be1999ff37cd?w=500",
  },
];
const bookings = [
  {
    id: 1,
    user: "Sanika",
    destination: "Bali",
    amount: "₹25,000",
    status: "Confirmed",
    image: "https://i.pravatar.cc/100?img=1",
  },
  {
    id: 2,
    user: "Rahul",
    destination: "Dubai",
    amount: "₹38,000",
    status: "Pending",
    image: "https://i.pravatar.cc/100?img=2",
  },
  {
    id: 3,
    user: "Priya",
    destination: "Paris",
    amount: "₹60,000",
    status: "Cancelled",
    image: "https://i.pravatar.cc/100?img=3",
  },
];
function AdminDashboard() {
  return (
    <div className="flex-1 min-h-screen bg-gradient-to-br from-[#F8F7FF] via-white to-purple-50 p-8">

      <Navbar />

      <div className="mt-8 mb-8">

        <h1 className="text-4xl font-bold text-gray-800">
          Welcome Back,
          <span className="text-purple-700"> Admin 👋</span>
        </h1>

        <p className="text-gray-500 mt-2">
          Here's what's happening in your travel business today.
        </p>

      </div>
            {/* Statistics Cards */}

      <div className="grid grid-cols-1 md:grid-cols-2 xl:grid-cols-4 gap-6">

        <StatCard
          title="Total Revenue"
          value="₹18.5L"
          change="+23.5%"
          color="purple"
          icon={<FaRupeeSign />}
        />

        <StatCard
          title="Total Bookings"
          value="1,245"
          change="+18.7%"
          color="blue"
          icon={<FaSuitcase />}
        />

        <StatCard
          title="Total Users"
          value="2,530"
          change="+29.3%"
          color="green"
          icon={<FaUsers />}
        />

        <StatCard
          title="Destinations"
          value="145"
          change="+12.4%"
          color="orange"
          icon={<FaMapMarkedAlt />}
        />

      </div>
            <div className="grid lg:grid-cols-3 gap-8 mt-8">
                    <div className="lg:col-span-2 bg-white rounded-3xl shadow-xl p-6">

          <div className="flex justify-between items-center mb-6">

            <h2 className="text-2xl font-bold">
              Monthly Bookings
            </h2>

            <select className="border rounded-lg px-3 py-2">

              <option>This Year</option>
              <option>Last Year</option>

            </select>

          </div>

          <div className="h-80">

            <ResponsiveContainer>

              <BarChart data={monthlyData}>

                <CartesianGrid strokeDasharray="3 3" />

                <XAxis dataKey="month" />

                <YAxis />

                <Tooltip />

                <Bar
                  dataKey="bookings"
                  fill="#7C3AED"
                  radius={[10, 10, 0, 0]}
                />

              </BarChart>

            </ResponsiveContainer>

          </div>

        </div>
                <div className="bg-white rounded-3xl shadow-xl p-6">

          <div className="flex justify-between items-center mb-6">

            <h2 className="text-2xl font-bold">
              Booking Status
            </h2>

            <select className="border rounded-lg px-3 py-2">

              <option>This Year</option>

            </select>

          </div>

          <div className="h-80">

            <ResponsiveContainer>

              <PieChart>

                <Pie
                  data={pieData}
                  dataKey="value"
                  innerRadius={65}
                  outerRadius={100}
                  paddingAngle={4}
                >

                  {pieData.map((entry, index) => (

                    <Cell
                      key={index}
                      fill={COLORS[index]}
                    />

                  ))}

                </Pie>

                <Tooltip />

                <Legend />

              </PieChart>

            </ResponsiveContainer>

          </div>

        </div>

      </div>
            {/* Popular Destinations + Revenue */}

      <div className="grid lg:grid-cols-3 gap-8 mt-8">

        {/* Popular Destinations */}

        <div className="lg:col-span-2 bg-white rounded-3xl shadow-xl p-6">

          <div className="flex justify-between items-center mb-6">

            <h2 className="text-2xl font-bold">
              Popular Destinations
            </h2>

            <button className="text-purple-600 font-semibold hover:underline">
              View All
            </button>

          </div>

          <div className="grid md:grid-cols-2 xl:grid-cols-4 gap-5">

            {destinations.map((place) => (

              <div
                key={place.id}
                className="rounded-2xl overflow-hidden shadow-md hover:shadow-2xl hover:-translate-y-2 transition-all duration-300 bg-white"
              >

                <img
                  src={place.image}
                  alt={place.name}
                  className="h-40 w-full object-cover"
                />

                <div className="p-4">

                  <h3 className="font-bold text-lg">
                    {place.name}
                  </h3>

                  <p className="text-gray-500">
                    {place.country}
                  </p>

                  <div className="flex justify-between items-center mt-4">

                    <span className="text-sm text-gray-600">
                      {place.bookings} Bookings
                    </span>

                    <span className="bg-purple-100 text-purple-700 text-xs px-3 py-1 rounded-full">
                      Top
                    </span>

                  </div>

                </div>

              </div>

            ))}

          </div>

        </div>

        {/* Revenue Overview */}

        <div className="bg-white rounded-3xl shadow-xl p-6">

          <div className="flex justify-between items-center mb-6">

            <h2 className="text-2xl font-bold">
              Revenue Overview
            </h2>

            <select className="border rounded-lg px-3 py-2">

              <option>This Year</option>

            </select>

          </div>

          <div>

            <h1 className="text-4xl font-bold text-purple-700">
              ₹18.5L
            </h1>

            <p className="text-green-600 mt-2">
              ▲ 23.5% vs last month
            </p>

          </div>

          <div className="h-64 mt-8">

            <ResponsiveContainer>

              <LineChart data={revenueData}>

                <CartesianGrid strokeDasharray="3 3" />

                <XAxis dataKey="month" />

                <YAxis />

                <Tooltip />

                <Line
                  type="monotone"
                  dataKey="revenue"
                  stroke="#7C3AED"
                  strokeWidth={4}
                  dot={{ r: 5 }}
                />

              </LineChart>

            </ResponsiveContainer>

          </div>

          <button className="mt-6 w-full bg-purple-600 hover:bg-purple-700 text-white py-3 rounded-xl font-semibold">
            View Full Report
          </button>

        </div>

      </div>
            {/* Quick Statistics */}

      <div className="grid grid-cols-2 md:grid-cols-4 gap-6 mt-8">

        <div className="bg-white rounded-2xl shadow-lg p-6 text-center hover:shadow-xl transition">

          <div className="text-5xl mb-3">
            ✈️
          </div>

          <h2 className="text-2xl font-bold">
            145+
          </h2>

          <p className="text-gray-500">
            Destinations
          </p>

        </div>

        <div className="bg-white rounded-2xl shadow-lg p-6 text-center hover:shadow-xl transition">

          <div className="text-5xl mb-3">
            🎒
          </div>

          <h2 className="text-2xl font-bold">
            256+
          </h2>

          <p className="text-gray-500">
            Packages
          </p>

        </div>

        <div className="bg-white rounded-2xl shadow-lg p-6 text-center hover:shadow-xl transition">

          <div className="text-5xl mb-3">
            👥
          </div>

          <h2 className="text-2xl font-bold">
            2,530+
          </h2>

          <p className="text-gray-500">
            Happy Customers
          </p>

        </div>

        <div className="bg-white rounded-2xl shadow-lg p-6 text-center hover:shadow-xl transition">

          <div className="text-5xl mb-3">
            ⭐
          </div>

          <h2 className="text-2xl font-bold">
            4.9
          </h2>

          <p className="text-gray-500">
            Average Rating
          </p>

        </div>

      </div>
            {/* Recent Bookings & Recent Activity */}

      <div className="grid lg:grid-cols-3 gap-8 mt-8">

        {/* Recent Bookings */}

        <div className="lg:col-span-2 bg-white rounded-3xl shadow-xl p-6">

          <div className="flex justify-between items-center mb-6">

            <h2 className="text-2xl font-bold">
              Recent Bookings
            </h2>

            <button className="text-purple-600 font-semibold hover:underline">
              View All
            </button>

          </div>

          <div className="overflow-x-auto">

            <table className="w-full">

              <thead>

                <tr className="bg-purple-100 text-gray-700">

                  <th className="py-4 px-4 text-left">Customer</th>
                  <th className="text-left">Destination</th>
                  <th className="text-left">Amount</th>
                  <th className="text-left">Status</th>
                  <th className="text-center">Action</th>

                </tr>

              </thead>

              <tbody>

                {bookings.map((booking) => (

                  <tr
                    key={booking.id}
                    className="border-b hover:bg-purple-50 transition"
                  >

                    <td className="py-4">

                      <div className="flex items-center gap-3">

                        <img
                          src={booking.image}
                          alt=""
                          className="w-12 h-12 rounded-full object-cover"
                        />

                        <div>

                          <h3 className="font-semibold">
                            {booking.user}
                          </h3>

                          <p className="text-gray-500 text-sm">
                            Booking #{booking.id}
                          </p>

                        </div>

                      </div>

                    </td>

                    <td>{booking.destination}</td>

                    <td className="font-semibold">
                      {booking.amount}
                    </td>

                    <td>

                      <span
                        className={`px-4 py-1 rounded-full text-sm font-medium ${
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

                    <td className="text-center">

                      <button className="bg-purple-600 hover:bg-purple-700 text-white px-4 py-2 rounded-lg">
                        View
                      </button>

                    </td>

                  </tr>

                ))}

              </tbody>

            </table>

          </div>

        </div>
                {/* Recent Activity */}

        <div className="bg-white rounded-3xl shadow-xl p-6">

          <h2 className="text-2xl font-bold mb-6">
            Recent Activity
          </h2>

          <div className="space-y-6">

            <div className="flex gap-4">
              <div className="w-3 h-3 rounded-full bg-green-500 mt-2"></div>

              <div>
                <h3 className="font-semibold">New Booking</h3>

                <p className="text-gray-500 text-sm">
                  Rahul booked Dubai Package
                </p>

                <span className="text-xs text-gray-400">
                  5 mins ago
                </span>
              </div>
            </div>

            <div className="flex gap-4">
              <div className="w-3 h-3 rounded-full bg-blue-500 mt-2"></div>

              <div>
                <h3 className="font-semibold">
                  New User Registered
                </h3>

                <p className="text-gray-500 text-sm">
                  Priya Verma joined Travel Planner
                </p>

                <span className="text-xs text-gray-400">
                  20 mins ago
                </span>
              </div>
            </div>

            <div className="flex gap-4">
              <div className="w-3 h-3 rounded-full bg-red-500 mt-2"></div>

              <div>
                <h3 className="font-semibold">
                  Booking Cancelled
                </h3>

                <p className="text-gray-500 text-sm">
                  Goa Trip was cancelled
                </p>

                <span className="text-xs text-gray-400">
                  1 hour ago
                </span>
              </div>
            </div>

            <div className="flex gap-4">
              <div className="w-3 h-3 rounded-full bg-yellow-500 mt-2"></div>

              <div>
                <h3 className="font-semibold">
                  New Review Added
                </h3>

                <p className="text-gray-500 text-sm">
                  ★★★★★ Bali Package received a 5-star review.
                </p>

                <span className="text-xs text-gray-400">
                  2 hours ago
                </span>
              </div>
            </div>

          </div>

        </div>

      </div> {/* End of Recent Bookings & Activity Grid */}
      </div>
  );
}

export default AdminDashboard;
            