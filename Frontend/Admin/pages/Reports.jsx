import React from "react";
import {
  PieChart,
  Pie,
  Cell,
  ResponsiveContainer,
  Tooltip,
  Legend,
  BarChart,
  Bar,
  XAxis,
  YAxis,
  CartesianGrid,
  LineChart,
  Line,
} from "recharts";

import {
  FaUsers,
  FaSuitcase,
  FaRupeeSign,
  FaMapMarkedAlt,
  FaFilePdf,
  FaFileCsv,
} from "react-icons/fa";
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
  { name: "Confirmed", value: 642 },
  { name: "Pending", value: 312 },
  { name: "Cancelled", value: 168 },
  { name: "Completed", value: 123 },
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
function Reports() {
  return (
    <div className="flex-1 bg-[#F8F7FF] min-h-screen p-8">

      {/* Header */}

      <div className="flex justify-between items-center mb-8">

        <div>

          <h1 className="text-4xl font-bold text-purple-700">
            Reports & Analytics
          </h1>

          <p className="text-gray-500">
            Track your business performance
          </p>

        </div>

        <div className="flex gap-4">

          <button className="bg-red-500 text-white px-5 py-3 rounded-xl flex items-center gap-2">
            <FaFilePdf />
            Export PDF
          </button>

          <button className="bg-green-500 text-white px-5 py-3 rounded-xl flex items-center gap-2">
            <FaFileCsv />
            Export CSV
          </button>

        </div>

      </div>

      {/* Summary Cards */}

      <div className="grid md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">

        {/* Revenue */}

        <div className="bg-white rounded-3xl shadow-lg p-6">

          <div className="flex justify-between">

            <div>

              <p className="text-gray-500">
                Revenue
              </p>

              <h2 className="text-3xl font-bold mt-2">
                ₹18.5L
              </h2>

            </div>

            <FaRupeeSign className="text-5xl text-purple-600" />

          </div>

        </div>

        {/* Bookings */}

        <div className="bg-white rounded-3xl shadow-lg p-6">

          <div className="flex justify-between">

            <div>

              <p className="text-gray-500">
                Bookings
              </p>

              <h2 className="text-3xl font-bold mt-2">
                1245
              </h2>

            </div>

            <FaSuitcase className="text-5xl text-blue-500" />

          </div>

        </div>

        {/* Users */}

        <div className="bg-white rounded-3xl shadow-lg p-6">

          <div className="flex justify-between">

            <div>

              <p className="text-gray-500">
                Users
              </p>

              <h2 className="text-3xl font-bold mt-2">
                2530
              </h2>

            </div>

            <FaUsers className="text-5xl text-green-500" />

          </div>

        </div>

        {/* Destinations */}

        <div className="bg-white rounded-3xl shadow-lg p-6">

          <div className="flex justify-between">

            <div>

              <p className="text-gray-500">
                Destinations
              </p>

              <h2 className="text-3xl font-bold mt-2">
                145
              </h2>

            </div>

            <FaMapMarkedAlt className="text-5xl text-orange-500" />

          </div>

        </div>

      </div>

      {/* Charts */}

      <div className="grid lg:grid-cols-3 gap-8">

        {/* Monthly Booking */}

        <div className="lg:col-span-2 bg-white rounded-3xl shadow-lg p-6">

          <h2 className="text-2xl font-bold mb-5">
            Monthly Bookings
          </h2>

          <div className="h-80">

            <ResponsiveContainer width="100%" height="100%">

              <BarChart data={monthlyData}>

                <CartesianGrid strokeDasharray="3 3" />

                <XAxis dataKey="month" />

                <YAxis />

                <Tooltip />

                <Bar
                  dataKey="bookings"
                  fill="#8B5CF6"
                  radius={[8,8,0,0]}
                />

              </BarChart>

            </ResponsiveContainer>

          </div>

        </div>

        {/* Pie Chart */}

        <div className="bg-white rounded-3xl shadow-lg p-6">

          <h2 className="text-2xl font-bold mb-5">
            Booking Status
          </h2>

          <div className="h-80">

            <ResponsiveContainer>

              <PieChart>

                <Pie
                  data={pieData}
                  dataKey="value"
                  innerRadius={70}
                  outerRadius={100}
                >

                  {pieData.map((entry,index)=>(
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

      {/* Revenue */}

      <div className="bg-white rounded-3xl shadow-lg p-6 mt-8">

        <h2 className="text-2xl font-bold mb-5">
          Revenue Overview
        </h2>

        <div className="h-80">

          <ResponsiveContainer>

            <LineChart data={revenueData}>

              <CartesianGrid strokeDasharray="3 3"/>

              <XAxis dataKey="month"/>

              <YAxis/>

              <Tooltip/>

              <Line
                type="monotone"
                dataKey="revenue"
                stroke="#9333EA"
                strokeWidth={4}
              />

            </LineChart>

          </ResponsiveContainer>

        </div>

      </div>

    </div>
  );
}

export default Reports;