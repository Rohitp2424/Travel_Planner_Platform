import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { FaUpload } from "react-icons/fa";

function AddDestination() {
  const navigate = useNavigate();

  const [destination, setDestination] = useState({
    name: "",
    country: "",
    price: "",
  });

  const [image, setImage] = useState("");
  const [loading, setLoading] = useState(false);

  const handleChange = (e) => {
    setDestination({
      ...destination,
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

    const newDestination = {
      ...destination,
      image,
    };

    try {
      const response = await fetch(
        "http://localhost:8080/api/destinations", // Change API if needed
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(newDestination),
        }
      );

      if (!response.ok) {
        throw new Error("Failed to add destination");
      }

      alert("Destination Added Successfully!");

      navigate("/destinations");
    } catch (error) {
      console.error(error);
      alert("Error while adding destination");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="flex-1 min-h-screen bg-[#F8F7FF] p-8">

      <div className="max-w-4xl mx-auto bg-white rounded-3xl shadow-xl p-8">

        <h1 className="text-4xl font-bold text-purple-700 mb-8">
          Add Destination
        </h1>

        <form
          onSubmit={handleSubmit}
          className="grid md:grid-cols-2 gap-6"
        >

          {/* Destination Name */}
          <div>
            <label className="font-semibold">
              Destination Name
            </label>

            <input
              type="text"
              name="name"
              value={destination.name}
              onChange={handleChange}
              required
              className="w-full mt-2 border rounded-xl p-3"
            />
          </div>

          {/* Country */}
          <div>
            <label className="font-semibold">
              Country
            </label>

            <input
              type="text"
              name="country"
              value={destination.country}
              onChange={handleChange}
              required
              className="w-full mt-2 border rounded-xl p-3"
            />
          </div>

          {/* Price */}
          <div>
            <label className="font-semibold">
              Price
            </label>

            <input
              type="number"
              name="price"
              value={destination.price}
              onChange={handleChange}
              required
              className="w-full mt-2 border rounded-xl p-3"
            />
          </div>

          {/* Image Upload */}
          <div className="md:col-span-2">

            <label className="font-semibold flex items-center gap-2">
              <FaUpload className="text-purple-600" />
              Destination Image
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
                className="mt-4 h-56 rounded-xl object-cover"
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
              {loading ? "Saving..." : "Save Destination"}
            </button>

            <button
              type="button"
              onClick={() => navigate("/destinations")}
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

export default AddDestination;
