import {
  FaSearch,
  FaTrash,
  FaStar,
  FaEye,
  FaCheckCircle,
  FaClock,
} from "react-icons/fa";
const reviews = [
  {
    id: 1,
    name: "Sanika Akhade",
    image: "https://i.pravatar.cc/100?img=1",
    destination: "Bali",
    rating: 5,
    review: "Amazing experience! Everything was well organized.",
    status: "Approved",
    date: "10 Jul 2026",
  },
  {
    id: 2,
    name: "Rahul Sharma",
    image: "https://i.pravatar.cc/100?img=2",
    destination: "Dubai",
    rating: 4,
    review: "Great trip. Hotels were excellent.",
    status: "Pending",
    date: "12 Jul 2026",
  },
  {
    id: 3,
    name: "Priya Verma",
    image: "https://i.pravatar.cc/100?img=3",
    destination: "Goa",
    rating: 3,
    review: "Nice place but weather wasn't good.",
    status: "Approved",
    date: "15 Jul 2026",
  },
  {
    id: 4,
    name: "Amit Joshi",
    image: "https://i.pravatar.cc/100?img=4",
    destination: "Paris",
    rating: 5,
    review: "Best vacation ever!",
    status: "Approved",
    date: "18 Jul 2026",
  },
];
function ManageReviews() {
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
      <div className="grid md:grid-cols-4 gap-6 mb-8">

  <div className="bg-white rounded-3xl shadow-lg p-6">
    <h3 className="text-gray-500">Total Reviews</h3>
    <h1 className="text-4xl font-bold mt-2">245</h1>
  </div>

  <div className="bg-white rounded-3xl shadow-lg p-6">
    <h3 className="text-gray-500">Average Rating</h3>
    <h1 className="text-4xl font-bold text-yellow-500 mt-2">
      4.8 ⭐
    </h1>
  </div>

  <div className="bg-white rounded-3xl shadow-lg p-6">
    <h3 className="text-gray-500">Approved</h3>
    <h1 className="text-4xl font-bold text-green-600 mt-2">
      210
    </h1>
  </div>

  <div className="bg-white rounded-3xl shadow-lg p-6">
    <h3 className="text-gray-500">Pending</h3>
    <h1 className="text-4xl font-bold text-orange-500 mt-2">
      35
    </h1>
  </div>

</div>

      {/* Search */}

      <div className="bg-white rounded-3xl shadow-lg p-6 mb-8">

  <div className="flex flex-col md:flex-row gap-4">

    <div className="relative flex-1">

      <FaSearch className="absolute left-4 top-4 text-gray-400"/>

      <input
        type="text"
        placeholder="Search customer..."
        className="w-full pl-12 py-3 border rounded-xl"
      />

    </div>

    <select className="border rounded-xl px-5">

      <option>All Ratings</option>
      <option>⭐⭐⭐⭐⭐</option>
      <option>⭐⭐⭐⭐</option>
      <option>⭐⭐⭐</option>

    </select>

  </div>

</div>
      {/* Reviews Table */}

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

{reviews.map((review)=>(

<tr
key={review.id}
className="border-b hover:bg-purple-50 transition"
>

<td className="py-5">

<div className="flex items-center gap-3">

<img
src={review.image}
alt=""
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

{[...Array(5)].map((_,i)=>(

<FaStar
key={i}
className={
i<review.rating
?"text-yellow-500"
:"text-gray-300"
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
review.status==="Approved"
?"bg-green-100 text-green-700"
:"bg-orange-100 text-orange-700"
}`}
>

{review.status}

</span>

</td>

<td>{review.date}</td>

<td>

<div className="flex justify-center gap-3">

<button className="bg-blue-500 hover:bg-blue-600 text-white p-3 rounded-xl">

<FaEye/>

</button>

<button className="bg-red-500 hover:bg-red-600 text-white p-3 rounded-xl">

<FaTrash/>

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

export default ManageReviews; 