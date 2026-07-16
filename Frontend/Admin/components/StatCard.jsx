import { FaArrowUp } from "react-icons/fa";

function StatCard({ title, value, icon, change, color }) {
  const colors = {
    purple: "bg-purple-100 text-purple-700",
    blue: "bg-blue-100 text-blue-700",
    green: "bg-green-100 text-green-700",
    orange: "bg-orange-100 text-orange-700",
  };

  return (
    <div className="bg-white rounded-3xl shadow-lg p-6 hover:-translate-y-2 hover:shadow-2xl transition-all duration-300">

      <div className="flex justify-between items-center">

        <div>

          <p className="text-gray-500">{title}</p>

          <h2 className="text-3xl font-bold mt-2">{value}</h2>

          <div className="flex items-center gap-2 mt-3 text-green-600 text-sm font-semibold">

            <FaArrowUp />

            {change}

          </div>

        </div>

        <div
          className={`w-16 h-16 rounded-2xl flex items-center justify-center text-3xl ${colors[color]}`}
        >
          {icon}
        </div>

      </div>

    </div>
  );
}

export default StatCard;