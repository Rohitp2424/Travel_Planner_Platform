import Navbar from "../components/Navbar";
import Hero from "../components/Hero";
import FeatureCards from "../components/FeatureCards";
import DestinationCards from "../components/DestinationCards";
import Footer from "../components/Footer";

function Home() {
  return (
    <>
      <Navbar />
      <Hero />
       
      <FeatureCards />
      <DestinationCards />
      <Footer/>
    </>
  );
}

export default Home;