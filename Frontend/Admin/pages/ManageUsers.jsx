import { useEffect, useMemo, useState } from "react";
import {
  FaPlus,
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
  const [statusFilter, setStatusFilter] = useState("All Users");
  const [loading, setLoading] = useState(true);

  // Fetch Users
  useEffect(() => {
    fetch("http://localhost:8080/api/user/v1/users")
      .then((res) => {
        if (!res.ok) throw new Error("Failed to fetch users");
        return res.json();
      })
      .then((data) => {
        setUsers(data);
        setLoading(false);
      })
      .catch((err) => {
        console.log(err);
        setLoading(false);
      });
  }, []);

  // Delete User
  const deleteUser = async (id) => {
    if (!window.confirm("Delete this user?")) return;

    try {
      const res = await fetch(
        `http://localhost:8080/api/user/v1/users/${id}`,
        {
          method: "DELETE",
        }
      );

      if (res.ok) {
        setUsers(users.filter((user) => user.id !== id));
      }
    } catch (err) {
      console.log(err);
    }
  };

  // Update Status
  const updateStatus = async (id, status) => {
    try {
      const res = await fetch(
        `http://localhost:8080/api/user/v1/users/${id}/status`,
        {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({ status }),
        }
      );

      if (res.ok) {
        setUsers((prevUsers) =>
          prevUsers.map((user) =>
            user.id === id ? { ...user, status } : user
          )
        );
      }
    } catch (err) {
      console.log(err);
    }
  };

  // Search & Filter
  const filteredUsers = useMemo(() => {
    return users.filter((user) => {
      const fullName = `${user.firstName || ""} ${user.lastName || ""}`;

      const matchSearch =
        fullName.toLowerCase().includes(search.toLowerCase()) ||
        user.email?.toLowerCase().includes(search.toLowerCase());

      const matchStatus =
        statusFilter === "All Users"
          ? true
          : user.status === statusFilter;

      return matchSearch && matchStatus;
    });
  }, [users, search, statusFilter]);

  // Statistics
  const totalUsers = users.length;

  const activeUsers = users.filter(
    (user) => user.status === "Active"
  ).length;

  const inactiveUsers = users.filter(
    (user) => user.status === "Inactive"
  ).length;
return (
  <div className="flex-1 min-h-screen bg-[#F8F7FF] p-8">

    {/* Header */}
    <div className="flex justify-between items-center mb-8">

      <div>
        <h1 className="text-4xl font-bold text-purple-700">
          Manage Users
        </h1>

        <p className="text-gray-500 mt-1">
          Manage all registered users
        </p>
      </div>

      <button className="flex items-center gap-2 bg-purple-600 hover:bg-purple-700 text-white px-6 py-3 rounded-xl shadow-lg">
        <FaPlus />
        Add User
      </button>

    </div>

    {/* Statistics Cards */}

    <div className="grid md:grid-cols-3 gap-6 mb-8">

      <div className="bg-white p-6 rounded-3xl shadow-lg">
        <div className="flex justify-between items-center">

          <div>
            <p className="text-gray-500">Total Users</p>
            <h2 className="text-3xl font-bold mt-2">
              {totalUsers}
            </h2>
          </div>

          <FaUsers className="text-5xl text-purple-600" />

        </div>
      </div>

      <div className="bg-white p-6 rounded-3xl shadow-lg">
        <div className="flex justify-between items-center">

          <div>
            <p className="text-gray-500">Active Users</p>
            <h2 className="text-3xl font-bold mt-2">
              {activeUsers}
            </h2>
          </div>

          <FaUserCheck className="text-5xl text-green-500" />

        </div>
      </div>

      <div className="bg-white p-6 rounded-3xl shadow-lg">
        <div className="flex justify-between items-center">

          <div>
            <p className="text-gray-500">Inactive Users</p>
            <h2 className="text-3xl font-bold mt-2">
              {inactiveUsers}
            </h2>
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
        value={statusFilter}
        onChange={(e) => setStatusFilter(e.target.value)}
        className="border rounded-xl px-4 py-3"
      >
        <option>All Users</option>
        <option>Active</option>
        <option>Inactive</option>
      </select>

    </div>
    {/* Users Table */}

<div className="bg-white rounded-3xl shadow-lg overflow-hidden">

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

      {loading ? (

        <tr>
          <td colSpan="5" className="text-center py-8 text-gray-500">
            Loading users...
          </td>
        </tr>

      ) : filteredUsers.length === 0 ? (

        <tr>
          <td colSpan="5" className="text-center py-8 text-gray-500">
            No users found.
          </td>
        </tr>

      ) : (

        filteredUsers.map((user) => (

          <tr
            key={user.id}
            className="border-b hover:bg-purple-50 transition"
          >

            {/* User */}

            <td className="py-4 px-4">

              <div className="flex items-center gap-4">

                <img
                  src={
                    user.image ||
                    `https://ui-avatars.com/api/?name=${encodeURIComponent(
                      `${user.firstName || ""} ${user.lastName || ""}`
                    )}&background=7C3AED&color=fff`
                  }
                  alt="User"
                  className="w-12 h-12 rounded-full object-cover"
                />

                <div>

                  <h3 className="font-semibold">
                    {user.firstName} {user.lastName}
                  </h3>

                  <p className="text-gray-500 text-sm">
                    ID #{user.id}
                  </p>

                </div>

              </div>

            </td>

            {/* Email */}

            <td>{user.email}</td>

            {/* Role */}

            <td>

              <span className="bg-blue-100 text-blue-700 px-3 py-1 rounded-full">
                {user.role}
              </span>

            </td>

            {/* Status Dropdown */}

            <td>

              <select
                value={user.status}
                onChange={(e) =>
                  updateStatus(user.id, e.target.value)
                }
                className={`px-3 py-2 rounded-lg font-medium border ${
                  user.status === "Active"
                    ? "bg-green-100 text-green-700"
                    : "bg-red-100 text-red-700"
                }`}
              >
                <option value="Active">Active</option>
                <option value="Inactive">Inactive</option>
              </select>

            </td>

            {/* Actions */}

            <td>

              <div className="flex gap-3">

                <button className="bg-blue-500 hover:bg-blue-600 text-white p-3 rounded-lg">
                  <FaEdit />
                </button>

                <button
                  onClick={() => deleteUser(user.id)}
                  className="bg-red-500 hover:bg-red-600 text-white p-3 rounded-lg"
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

export default ManageUsers;
