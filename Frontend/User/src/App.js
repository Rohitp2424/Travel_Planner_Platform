import { BrowserRouter, Routes, Route } from "react-router-dom";

import Home from "./pages/Home";
import Login from "./pages/Login";
import Register from "./pages/Register";
import Dashboard from "./pages/Dashboard";
import Destinations from "./pages/Destinations";
import DestinationDetails from "./pages/DestinationDetails";
import PlanTrip from "./pages/PlanTrip";
import Itinerary from "./pages/Itinerary";
import Flights from "./pages/Flights";
import Hotels from "./pages/Hotels";
import ReviewBooking from "./pages/ReviewBooking";
import BookingSuccess from "./pages/BookingSuccess";
import MyBookings from "./pages/MyBookings";
import Wishlist from "./pages/Wishlist";
import Bookings from "./pages/Bookings";
import Budget from "./pages/Budget";
import BudgetTracker from "./pages/BudgetTracker";
import Recommendations from "./pages/Recommendations";
import Notifications from "./pages/Notifications";
import Profile from "./pages/Profile";
import Settings from "./pages/Settings";


function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/destinations" element={<Destinations />} />
        <Route path="/destination/:id" element={<DestinationDetails />}/>
        <Route path="/plan-trip" element={<PlanTrip />} />
        <Route path="/itinerary" element={<Itinerary />} />
        <Route path="/flights" element={<Flights />} />
        <Route path="/hotels" element={<Hotels />} />
        <Route path="/review-booking" element={<ReviewBooking />}/>
        <Route path="/booking-success"element={<BookingSuccess />}/>
        <Route path="/my-bookings" element={<MyBookings />} />
        <Route path="/bookings" element={<Bookings />} />
        <Route path="/wishlist" element={<Wishlist />} />
        <Route path="/budget" element={<Budget />} />
        <Route path="/budget-tracker" element={<BudgetTracker />} />
        <Route path="/recommendations" element={<Recommendations />} />
        <Route path="/notifications" element={<Notifications />} />
        <Route path="/profile" element={<Profile />} />
        <Route path="/settings" element={<Settings />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;