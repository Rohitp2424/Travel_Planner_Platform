import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { FaUpload } from "react-icons/fa";

function AddPackage() {
  const navigate = useNavigate();

  const [packageData, setPackageData] = useState({
    title: "",
    destination: "",
    duration: "",
    price: "",
    currency: "₹",
    rating: "",
    status: "Active",
  });

  const [image, setImage] = useState("");
  const [loading, setLoading] = useState(false);

  const handleChange = (e) => {
    setPackageData({
      ...packageData,
      [e.target.name]: e.target.value,
    });
  };

  const handleImage = (e) => {
    const file = e.target.files[0];

    if (!file) return;

    const reader = new FileReader();

    reader.onloadend = () => {
      setImage(reader.result);
    };

    reader.readAsDataURL(file);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    setLoading(true);

    const newPackage = {
      title: packageData.title,
      destination: packageData.destination,
      duration: packageData.duration,
      price: packageData.price,
      currency: packageData.currency,
      rating: packageData.rating,
      status: packageData.status,
      image: image,
    };

    try {
      const response = await fetch(
        "http://localhost:8080/api/packages", // Change your API here
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(newPackage),
        }
      );

      if (!response.ok) {
        throw new Error("Failed to add package");
      }

      alert("Package Added Successfully!");

      navigate("/packages");
    } catch (error) {
      console.error(error);
      alert("Failed to add package");
    } finally {
      setLoading(false);
    }
  };
    return (
    <div className="flex-1 min-h-screen bg-[#F8F7FF] p-8">

      <div className="max-w-4xl mx-auto bg-white rounded-3xl shadow-xl p-8">

        <h1 className="text-4xl font-bold text-purple-700 mb-8">
          Add Package
        </h1>

        <form onSubmit={handleSubmit} className="grid md:grid-cols-2 gap-6">

          {/* Package Name */}
          <div>
            <label className="font-semibold">Package Name</label>
            <input
              type="text"
              name="title"
              value={packageData.title}
              onChange={handleChange}
              required
              className="w-full mt-2 border rounded-xl p-3"
            />
          </div>

          {/* Destination */}
          <div>
            <label className="font-semibold">Destination</label>
            <input
              type="text"
              name="destination"
              value={packageData.destination}
              onChange={handleChange}
              required
              className="w-full mt-2 border rounded-xl p-3"
            />
          </div>

          {/* Duration */}
          <div>
            <label className="font-semibold">Duration</label>
            <input
              type="text"
              name="duration"
              placeholder="5 Days / 4 Nights"
              value={packageData.duration}
              onChange={handleChange}
              required
              className="w-full mt-2 border rounded-xl p-3"
            />
          </div>

          {/* Rating */}
          <div>
            <label className="font-semibold">Rating</label>
            <input
              type="number"
              min="1"
              max="5"
              step="0.1"
              name="rating"
              value={packageData.rating}
              onChange={handleChange}
              className="w-full mt-2 border rounded-xl p-3"
            />
          </div>

          {/* Currency */}
          <div>
            <label className="font-semibold">Currency</label>
            <select
              name="currency"
              value={packageData.currency}
              onChange={handleChange}
              className="w-full mt-2 border rounded-xl p-3"
            >
              <option value="₹">INR (₹)</option>
              <option value="$">USD ($)</option>
              <option value="€">EUR (€)</option>
              <option value="£">GBP (£)</option>
              <option value="AED">AED</option>
            </select>
          </div>

          {/* Price */}
          <div>
            <label className="font-semibold">Price</label>
            <input
              type="number"
              name="price"
              value={packageData.price}
              onChange={handleChange}
              required
              className="w-full mt-2 border rounded-xl p-3"
            />
          </div>

          {/* Status */}
          <div>
            <label className="font-semibold">Status</label>
            <select
              name="status"
              value={packageData.status}
              onChange={handleChange}
              className="w-full mt-2 border rounded-xl p-3"
            >
              <option value="Active">Active</option>
              <option value="Inactive">Inactive</option>
            </select>
          </div>

          {/* Image Upload */}
          <div className="md:col-span-2">
            <label className="font-semibold flex items-center gap-2">
              <FaUpload className="text-purple-600" />
              Package Image
            </label>

            <input
              type="file"
              accept="image/*"
              onChange={handleImage}
              className="w-full mt-2 border rounded-xl p-3"
            />

            {image && (
              <img
                src={image}
                alt="Preview"
                className="mt-4 h-52 rounded-xl object-cover"
              />
            )}
          </div>

          {/* Buttons */}
          <div className="md:col-span-2 flex gap-4 mt-4">

            <button
              type="submit"
              disabled={loading}
              className="bg-purple-600 hover:bg-purple-700 disabled:bg-gray-400 text-white px-8 py-3 rounded-xl"
            >
              {loading ? "Saving..." : "Save Package"}
            </button>

            <button
              type="button"
              onClick={() => navigate("/packages")}
              className="bg-gray-500 hover:bg-gray-600 text-white px-8 py-3 rounded-xl"
            >
              Cancel
            </button>

          </div>

        </form>

      </div>

    </div>
  );
}

export default AddPackage;
