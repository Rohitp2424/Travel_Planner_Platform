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
