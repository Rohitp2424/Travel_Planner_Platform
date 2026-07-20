import { useEffect, useState } from "react";
import {
  FaEdit,
  FaTrash,
  FaSearch,
  FaUsers,
  FaUserCheck,
  FaUserTimes,
} from "react-icons/fa";

function ManageUsers() {
  const [users, setUsers] = useState([]);
  const [search, setSearch] = useState("");
  const [filter, setFilter] = useState("All Users");

  useEffect(() => {
    fetch("http://localhost:8080/api/users") // Change if your endpoint is different
      .then((res) => {
        if (!res.ok) {
          throw new Error("Failed to fetch users");
        }
        return res.json();
      })
      .then((data) => setUsers(data))
      .catch((err) => console.error(err));
  }, []);

  const filteredUsers = users.filter((user) => {
    const matchesSearch =
      (user.name || "").toLowerCase().includes(search.toLowerCase()) ||
      (user.email || "").toLowerCase().includes(search.toLowerCase());

    const matchesFilter =
      filter === "All Users" ||
      user.status === filter ||
      user.role === filter;

    return matchesSearch && matchesFilter;
  });

  const totalUsers = users.length;
  const activeUsers = users.filter(
    (u) => u.status === "Active"
  ).length;
  const inactiveUsers = users.filter(
    (u) => u.status === "Inactive"
  ).length;

  return (
    <div className="flex-1 min-h-screen bg-[#F8F7FF] p-8">
      {/* Header */}
      <div className="mb-8">
        <h1 className="text-4xl font-bold text-purple-700">
          Manage Users
        </h1>
        <p className="text-gray-500 mt-1">
          Manage all registered users
        </p>
      </div>

      {/* Statistics */}
      <div className="grid md:grid-cols-3 gap-6 mb-8">
        <div className="bg-white p-6 rounded-3xl shadow-lg">
          <div className="flex justify-between items-center">
            <div>
              <p className="text-gray-500">Total Users</p>
              <h2 className="text-3xl font-bold mt-2">{totalUsers}</h2>
            </div>
            <FaUsers className="text-5xl text-purple-600" />
          </div>
        </div>

        <div className="bg-white p-6 rounded-3xl shadow-lg">
          <div className="flex justify-between items-center">
            <div>
              <p className="text-gray-500">Active Users</p>
              <h2 className="text-3xl font-bold mt-2">{activeUsers}</h2>
            </div>
            <FaUserCheck className="text-5xl text-green-500" />
          </div>
        </div>

        <div className="bg-white p-6 rounded-3xl shadow-lg">
          <div className="flex justify-between items-center">
            <div>
              <p className="text-gray-500">Inactive Users</p>
              <h2 className="text-3xl font-bold mt-2">{inactiveUsers}</h2>
            </div>
            <FaUserTimes className="text-5xl text-red-500" />
          </div>
        </div>
      </div>

      {/* Search & Filter */}
      <div className="bg-white p-5 rounded-3xl shadow-lg mb-8 flex flex-col md:flex-row gap-4">
        <div className="relative flex-1">
          <FaSearch className="absolute left-4 top-4 text-gray-400" />

          <input
            type="text"
            placeholder="Search users..."
            value={search}
            onChange={(e) => setSearch(e.target.value)}
            className="w-full pl-12 py-3 border rounded-xl outline-none focus:border-purple-500"
          />
        </div>

        <select
          value={filter}
          onChange={(e) => setFilter(e.target.value)}
          className="border rounded-xl px-4 py-3 outline-none"
        >
          <option>All Users</option>
          <option>Active</option>
          <option>Inactive</option>
          <option>Admin</option>
        </select>
      </div>

      {/* Users Table */}
      <div className="bg-white rounded-3xl shadow-lg overflow-x-auto">
        <table className="w-full">
          <thead className="bg-purple-100">
            <tr>
              <th className="py-4">User</th>
              <th>Email</th>
              <th>Role</th>
              <th>Status</th>
              <th>Actions</th>
            </tr>
          </thead>

          <tbody>
            {filteredUsers.length > 0 ? (
              filteredUsers.map((user) => (
                <tr
                  key={user.id}
                  className="border-b hover:bg-purple-50"
                >
                  <td className="py-4 px-4">
                    <div className="flex items-center gap-4">
                      <img
                        src={
                          user.image ||
                          "https://i.pravatar.cc/150?img=1"
                        }
                        alt={user.name}
                        className="w-12 h-12 rounded-full object-cover"
                      />

                      <div>
                        <h3 className="font-semibold">{user.name}</h3>
                        <p className="text-gray-500 text-sm">
                          ID #{user.id}
                        </p>
                      </div>
                    </div>
                  </td>

                  <td>{user.email}</td>

                  <td>
                    <span className="bg-blue-100 text-blue-700 px-3 py-1 rounded-full">
                      {user.role}
                    </span>
                  </td>

                  <td>
                    <span
                      className={`px-4 py-1 rounded-full text-sm font-medium ${
                        user.status === "Active"
                          ? "bg-green-100 text-green-700"
                          : "bg-red-100 text-red-700"
                      }`}
                    >
                      {user.status}
                    </span>
                  </td>

                  <td>
                    <div className="flex gap-3 justify-center">
                      <button className="bg-blue-500 hover:bg-blue-600 text-white p-3 rounded-lg">
                        <FaEdit />
                      </button>

                      <button className="bg-red-500 hover:bg-red-600 text-white p-3 rounded-lg">
                        <FaTrash />
                      </button>
                    </div>
                  </td>
                </tr>
              ))
            ) : (
              <tr>
                <td
                  colSpan="5"
                  className="py-8 text-center text-gray-500"
                >
                  No users found.
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default ManageUsers;
