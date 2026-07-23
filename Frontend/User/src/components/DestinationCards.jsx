import "./../styles/DestinationCards.css";
import { FaStar } from "react-icons/fa";

const destinations = [
  {
    name: "Bali, Indonesia",
    subtitle: "Tropical Paradise",
    rating: "4.8",
    image:
      "https://images.unsplash.com/photo-1537953773345-d172ccf13cf1?auto=format&fit=crop&w=700&q=80",
  },
  {
    name: "Santorini, Greece",
    subtitle: "Island of Dreams",
    rating: "4.9",
    image:
      "https://images.unsplash.com/photo-1570077188670-e3a8d69ac5ff?auto=format&fit=crop&w=700&q=80",
  },
  {
    name: "Switzerland",
    subtitle: "Alpine Beauty",
    rating: "4.8",
    image:
      "https://images.unsplash.com/photo-1506744038136-46273834b3fb?auto=format&fit=crop&w=700&q=80",
  },
  {
    name: "Dubai, UAE",
    subtitle: "Modern Wonderland",
    rating: "4.7",
    image:
      "https://images.unsplash.com/photo-1512453979798-5ea266f8880c?auto=format&fit=crop&w=700&q=80",
  },
  {
    name: "Maldives",
    subtitle: "Heaven on Earth",
    rating: "4.9",
    image:
      "https://images.unsplash.com/photo-1573843981267-be1999ff37cd?auto=format&fit=crop&w=700&q=80",
  },
];

export default function DestinationCards() {
  return (
    <section className="destination-section">

      <div className="destination-header">
        <h2>Popular Destinations</h2>
        <button>View All →</button>
      </div>

      <div className="destination-grid">
        {destinations.map((place, index) => (
          <div className="destination-card" key={index}>
            <img src={place.image} alt={place.name} />

            <div className="destination-content">
              <h3>{place.name}</h3>
              <p>{place.subtitle}</p>

              <span>
                <FaStar />
                {place.rating}
              </span>
            </div>
          </div>
        ))}
      </div>

    </section>
  );
}