import "../styles/Notifications.css";
import {
FaBell,
FaCheckCircle,
FaPlaneDeparture,
FaHotel,
FaTags
} from "react-icons/fa";

export default function Notifications(){

const notifications=[

{
title:"Booking Confirmed",
message:"Your Bali trip has been booked successfully.",
time:"2 mins ago",
icon:<FaCheckCircle/>,
type:"success"
},

{
title:"Flight Reminder",
message:"Your flight departs tomorrow at 8:00 AM.",
time:"1 hour ago",
icon:<FaPlaneDeparture/>,
type:"flight"
},

{
title:"Hotel Check-in",
message:"Check-in starts from 2:00 PM.",
time:"Yesterday",
icon:<FaHotel/>,
type:"hotel"
},

{
title:"Special Offer",
message:"Get 20% OFF on your next booking.",
time:"2 days ago",
icon:<FaTags/>,
type:"offer"
}

];

return(

<div className="notification-page">

<h1>

<FaBell/>

Notifications

</h1>

<div className="notification-container">

{

notifications.map((item,index)=>(

<div
className="notification-card"
key={index}
>

<div className={`notification-icon ${item.type}`}>

{item.icon}

</div>

<div className="notification-content">

<h3>

{item.title}

</h3>

<p>

{item.message}

</p>

<span>

{item.time}

</span>

</div>

</div>

))

}

</div>

</div>

);

}