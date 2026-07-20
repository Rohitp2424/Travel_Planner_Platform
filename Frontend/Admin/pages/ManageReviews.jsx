import { useEffect, useMemo, useState } from "react";
import {
  FaSearch,
  FaTrash,
  FaStar,
  FaEye,
} from "react-icons/fa";

function ManageReviews() {
  const [reviews, setReviews] = useState([]);
  const [search, setSearch] = useState("");
  const [ratingFilter, setRatingFilter] = useState("All Ratings");
  const [loading, setLoading] = useState(true);

  // Fetch Reviews
  useEffect(() => {
    fetch("http://localhost:8080/api/reviews")
      .then((res) => {
        if (!res.ok) {
          throw new Error("Failed to fetch reviews");
        }
        return res.json();
      })
      .then((data) => {
        setReviews(data);
        setLoading(false);
      })
      .catch((err) => {
        console.log(err);
        setLoading(false);
      });
  }, []);

  // Delete Review
  const deleteReview = async (id) => {
    if (!window.confirm("Delete this review?")) return;

    try {
      const res = await fetch(`http://localhost:8080/api/reviews/${id}`, {
        method: "DELETE",
      });

      if (res.ok) {
        setReviews(reviews.filter((review) => review.id !== id));
      }
    } catch (err) {
      console.log(err);
    }
  };

  // Filter Reviews
  const filteredReviews = useMemo(() => {
    return reviews.filter((review) => {
      const matchSearch =
        review.name?.toLowerCase().includes(search.toLowerCase()) ||
        review.destination?.toLowerCase().includes(search.toLowerCase());

      const matchRating =
        ratingFilter === "All Ratings"
          ? true
          : review.rating === Number(ratingFilter);

      return matchSearch && matchRating;
    });
  }, [reviews, search, ratingFilter]);

  // Dashboard Data
  const totalReviews = reviews.length;

  const approvedReviews = reviews.filter(
    (r) => r.status === "Approved"
  ).length;

  const pendingReviews = reviews.filter(
    (r) => r.status === "Pending"
  ).length;

  const averageRating =
    reviews.length > 0
      ? (
          reviews.reduce((sum, r) => sum + Number(r.rating), 0) /
          reviews.length
        ).toFixed(1)
      : 0;

  return (
    <div className="flex-1 min-h-screen bg-[#F8F7FF] p-8">
      {/* Header */}
      <div className="flex justify-between items-center mb-8">
        <h1 className="text-3xl font-bold text-purple-700">
          Manage Reviews
        </h1>

        <button className="bg-purple-600 text-white px-5 py-3 rounded-xl hover:bg-purple-700">
          Export Reviews
        </button>
      </div>

      {/* Dashboard Cards */}
      <div className="grid md:grid-cols-4 gap-6 mb-8">
        <div className="bg-white rounded-3xl shadow-lg p-6">
          <h3 className="text-gray-500">Total Reviews</h3>
          <h1 className="text-4xl font-bold mt-2">
            {totalReviews}
          </h1>
        </div>

        <div className="bg-white rounded-3xl shadow-lg p-6">
          <h3 className="text-gray-500">Average Rating</h3>
          <h1 className="text-4xl font-bold text-yellow-500 mt-2">
            {averageRating} ⭐
          </h1>
        </div>

        <div className="bg-white rounded-3xl shadow-lg p-6">
          <h3 className="text-gray-500">Approved</h3>
          <h1 className="text-4xl font-bold text-green-600 mt-2">
            {approvedReviews}
          </h1>
        </div>

        <div className="bg-white rounded-3xl shadow-lg p-6">
          <h3 className="text-gray-500">Pending</h3>
          <h1 className="text-4xl font-bold text-orange-500 mt-2">
            {pendingReviews}
          </h1>
        </div>
      </div>

      {/* Search */}
      <div className="bg-white rounded-3xl shadow-lg p-6 mb-8">
        <div className="flex flex-col md:flex-row gap-4">
          <div className="relative flex-1">
            <FaSearch className="absolute left-4 top-4 text-gray-400" />

            <input
              type="text"
              placeholder="Search customer..."
              value={search}
              onChange={(e) => setSearch(e.target.value)}
              className="w-full pl-12 py-3 border rounded-xl"
            />
          </div>

          <select
            className="border rounded-xl px-5"
            value={ratingFilter}
            onChange={(e) => setRatingFilter(e.target.value)}
          >
            <option>All Ratings</option>
            <option value="5">⭐⭐⭐⭐⭐</option>
            <option value="4">⭐⭐⭐⭐</option>
            <option value="3">⭐⭐⭐</option>
            <option value="2">⭐⭐</option>
            <option value="1">⭐</option>
          </select>
        </div>
      </div>

      {/* Table */}
      <div className="bg-white rounded-2xl shadow overflow-hidden">
        <table className="w-full">
          <thead className="bg-purple-100">
            <tr>
              <th className="py-5">Customer</th>
              <th>Destination</th>
              <th>Rating</th>
              <th>Review</th>
              <th>Status</th>
              <th>Date</th>
              <th>Actions</th>
            </tr>
          </thead>

          <tbody>
            {loading ? (
              <tr>
                <td colSpan="7" className="text-center py-8">
                  Loading...
                </td>
              </tr>
            ) : filteredReviews.length === 0 ? (
              <tr>
                <td colSpan="7" className="text-center py-8">
                  No reviews found.
                </td>
              </tr>
            ) : (
              filteredReviews.map((review) => (
                <tr
                  key={review.id}
                  className="border-b hover:bg-purple-50 transition"
                >
                  <td className="py-5">
                    <div className="flex items-center gap-3">
                      <img
                        src={review.image}
                        alt={review.name}
                        className="w-14 h-14 rounded-full object-cover"
                      />

                      <div>
                        <h3 className="font-semibold">
                          {review.name}
                        </h3>

                        <p className="text-gray-500 text-sm">
                          Customer
                        </p>
                      </div>
                    </div>
                  </td>

                  <td>
                    <span className="bg-purple-100 text-purple-700 px-4 py-2 rounded-full">
                      {review.destination}
                    </span>
                  </td>

                  <td>
                    <div className="flex justify-center">
                      {[...Array(5)].map((_, i) => (
                        <FaStar
                          key={i}
                          className={
                            i < review.rating
                              ? "text-yellow-500"
                              : "text-gray-300"
                          }
                        />
                      ))}
                    </div>
                  </td>

                  <td>
                    <div className="max-w-xs text-gray-600">
                      {review.review}
                    </div>
                  </td>

                  <td>
                    <span
                      className={`px-4 py-2 rounded-full text-sm font-semibold ${
                        review.status === "Approved"
                          ? "bg-green-100 text-green-700"
                          : "bg-orange-100 text-orange-700"
                      }`}
                    >
                      {review.status}
                    </span>
                  </td>

                  <td>{review.date}</td>

                  <td>
                    <div className="flex justify-center gap-3">
                      <button
                        onClick={() => alert(review.review)}
                        className="bg-blue-500 hover:bg-blue-600 text-white p-3 rounded-xl"
                      >
                        <FaEye />
                      </button>

                      <button
                        onClick={() => deleteReview(review.id)}
                        className="bg-red-500 hover:bg-red-600 text-white p-3 rounded-xl"
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

export default ManageReviews;
