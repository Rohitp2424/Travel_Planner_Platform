import { useState } from "react";
import {
  FaEnvelope,
  FaPhone,
  FaMapMarkerAlt,
  FaCamera,
  FaCalendarAlt,
} from "react-icons/fa";

function AdminProfile() {

  const defaultProfile = {
    name: "Admin",
    email: "admin@gmail.com",
    phone: "+91 9876543210",
    location: "Nagpur, India",
  };

  const [profile, setProfile] = useState(() => {
    const saved = localStorage.getItem("profile");
    return saved ? JSON.parse(saved) : defaultProfile;
  });

  const [image, setImage] = useState(() => {
    return (
      localStorage.getItem("profileImage") ||
      "https://i.pravatar.cc/200"
    );
  });

  const [selectedImage, setSelectedImage] = useState(null);
  const [success, setSuccess] = useState(false);

  const handleChange = (e) => {
    setProfile({
      ...profile,
      [e.target.name]: e.target.value,
    });
  };

  const handleImage = (e) => {
    const file = e.target.files[0];

    if (!file) return;

    const reader = new FileReader();

    reader.onloadend = () => {
      setSelectedImage(reader.result);
    };

    reader.readAsDataURL(file);
  };

  const handleSave = () => {
    localStorage.setItem("profile", JSON.stringify(profile));

    if (selectedImage) {
      setImage(selectedImage);
      localStorage.setItem("profileImage", selectedImage);
    }

    setSuccess(true);

    setTimeout(() => {
      setSuccess(false);
    }, 3000);
  };
  return (
    <div className="flex-1 min-h-screen bg-[#F8F7FF] p-8">

      <h1 className="text-4xl font-bold text-purple-700 mb-8">
        Admin Profile
      </h1>

      <div className="grid lg:grid-cols-3 gap-8">

        {/* Left Card */}

        <div className="bg-white rounded-3xl shadow-lg p-8 text-center">

          <div className="relative w-44 h-44 mx-auto">

            <img
              src={image}
              alt="Profile"
              className="w-44 h-44 rounded-full object-cover border-4 border-purple-500 shadow-lg"
            />

            <label
              htmlFor="profileImage"
              className="absolute bottom-3 right-3 bg-purple-600 hover:bg-purple-700 p-3 rounded-full cursor-pointer transition"
            >
              <FaCamera className="text-white text-lg" />
            </label>

            <input
              id="profileImage"
              type="file"
              accept="image/*"
              className="hidden"
              onChange={handleImage}
            />

          </div>

          <h2 className="text-3xl font-bold mt-5">
            {profile.name}
          </h2>

          <span className="bg-purple-100 text-purple-700 px-4 py-2 rounded-full">
            Administrator
          </span>

          <div className="mt-8 space-y-5 text-left">

            <p className="flex items-center gap-3">
              <FaEnvelope className="text-purple-600" />
              {profile.email}
            </p>

            <p className="flex items-center gap-3">
              <FaPhone className="text-purple-600" />
              {profile.phone}
            </p>

            <p className="flex items-center gap-3">
              <FaMapMarkerAlt className="text-purple-600" />
              {profile.location}
            </p>

            <p className="flex items-center gap-3">
              <FaCalendarAlt className="text-purple-600" />
              Joined 12 July 2026
            </p>

          </div>

        </div>

        {/* Right Card */}

        <div className="lg:col-span-2 bg-white rounded-3xl shadow-lg p-8">

          <h2 className="text-3xl font-bold mb-8">
            Edit Profile
          </h2>

          <div className="grid md:grid-cols-2 gap-6">

            <div>

              <label className="font-medium">
                Name
              </label>

              <input
                type="text"
                name="name"
                value={profile.name}
                onChange={handleChange}
                className="w-full mt-2 border rounded-xl p-4 focus:outline-none focus:border-purple-600"
              />

            </div>

            <div>

              <label className="font-medium">
                Email
              </label>

              <input
                type="email"
                name="email"
                value={profile.email}
                onChange={handleChange}
                className="w-full mt-2 border rounded-xl p-4 focus:outline-none focus:border-purple-600"
              />

            </div>

            <div>

              <label className="font-medium">
                Phone
              </label>

              <input
                type="text"
                name="phone"
                value={profile.phone}
                onChange={handleChange}
                className="w-full mt-2 border rounded-xl p-4 focus:outline-none focus:border-purple-600"
              />

            </div>

            <div>

              <label className="font-medium">
                Location
              </label>

              <input
                type="text"
                name="location"
                value={profile.location}
                onChange={handleChange}
                className="w-full mt-2 border rounded-xl p-4 focus:outline-none focus:border-purple-600"
              />

            </div>

          </div>

          {/* Success Message */}

          {success && (

            <div className="mt-8 bg-green-100 border border-green-300 text-green-700 rounded-xl px-5 py-4 font-medium">

              ✅ Profile updated successfully!

            </div>

          )}

          {/* Save Button */}

          <button
            onClick={handleSave}
            className="mt-8 bg-purple-600 hover:bg-purple-700 text-white px-8 py-3 rounded-xl font-semibold transition duration-300"
          >
            Save Changes
          </button>

        </div>

      </div>

    </div>
  );
}

export default AdminProfile;