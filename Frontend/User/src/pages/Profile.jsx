import "../styles/Profile.css";
import {
FaUser,
FaCreditCard,
FaSuitcase,
FaBell,
FaKey,
FaSignOutAlt,
FaChevronRight
} from "react-icons/fa";

export default function Profile(){

const menu=[

{
icon:<FaUser/>,
title:"Personal Information"
},

{
icon:<FaCreditCard/>,
title:"Payment Methods"
},

{
icon:<FaSuitcase/>,
title:"Travel Preferences"
},

{
icon:<FaBell/>,
title:"Notification Preferences"
},

{
icon:<FaKey/>,
title:"Change Password"
},

{
icon:<FaSignOutAlt/>,
title:"Logout"
}

];

return(

<div className="profile-page">

<div className="profile-card">

<div className="profile-header">

<img
src="https://i.pravatar.cc/150?img=32"
alt="profile"
/>

<div>

<h2>Anushka Shelar</h2>

<p>anushka@example.com</p>

</div>

</div>

<div className="profile-menu">

{

menu.map((item,index)=>(

<div
className="menu-item"
key={index}
>

<div className="menu-left">

<div className="menu-icon">

{item.icon}

</div>

<span>

{item.title}

</span>

</div>
<FaChevronRight className="arrow"/>

                    </div>

                ))

            }

            </div>

        </div>

    </div>

);

}