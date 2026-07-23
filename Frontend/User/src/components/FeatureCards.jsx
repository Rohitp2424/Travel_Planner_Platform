import "./../styles/FeatureCards.css";

import {
  FaHotel,
  FaMoneyBillWave,
  FaHeadset,
  FaPlaneDeparture,
} from "react-icons/fa";

const features = [
  {
    icon: <FaHotel />,
    title: "Handpicked Stays",
    text: "Curated stays for every traveller."
  },
  {
    icon: <FaMoneyBillWave />,
    title: "Best Price Guarantee",
    text: "Get the best deals and save more."
  },
  {
    icon: <FaHeadset />,
    title: "24/7 Support",
    text: "We're here anytime."
  },
  {
    icon: <FaPlaneDeparture />,
    title: "Easy Booking",
    text: "Book your trip in minutes."
  }
];

export default function FeatureCards() {
  return (
    <section className="feature-section">

      <div className="feature-grid">

        {features.map((item, index) => (

          <div className="feature-card" key={index}>

            <div className="feature-icon">
              {item.icon}
            </div>

            <h3>{item.title}</h3>

            <p>{item.text}</p>

          </div>

        ))}

      </div>

    </section>
  );
}