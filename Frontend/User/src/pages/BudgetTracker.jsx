import "../styles/BudgetTracker.css";
import {
FaPlane,
FaHotel,
FaUtensils,
FaBus,
FaTicketAlt,
FaPlus
} from "react-icons/fa";

export default function BudgetTracker(){

const totalBudget = 70000;
const totalSpent = 52450;
const remaining = 17550;

const expenses=[

{
name:"Flights",
icon:<FaPlane/>,
amount:18450,
percent:26,
color:"flight"
},

{
name:"Stays",
icon:<FaHotel/>,
amount:20000,
percent:30,
color:"stay"
},

{
name:"Activities",
icon:<FaTicketAlt/>,
amount:7000,
percent:10,
color:"activity"
},

{
name:"Food",
icon:<FaUtensils/>,
amount:4000,
percent:6,
color:"food"
},

{
name:"Transport",
icon:<FaBus/>,
amount:3000,
percent:5,
color:"transport"
}

];

return(

<div className="budget-page">

<h1>Budget Tracker</h1>

<div className="budget-card">

<div className="summary">

<div className="summary-box">

<p>Total Budget</p>

<h2>₹70,000</h2>

</div>

<div className="summary-box">

<p>Total Spent</p>

<h2>₹52,450</h2>

</div>

<div className="summary-box">

<p>Remaining</p>

<h2>₹17,550</h2>

</div>

<div className="circle-box">

<div className="circle">

75%

</div>

</div>

</div>

<h2 className="expense-title">

Expense Breakdown

</h2>
{

                    expenses.map((item,index)=>(

                        <div
                            className="expense-row"
                            key={index}
                        >

                            <div className={`expense-icon ${item.color}`}>

                                {item.icon}

                            </div>

                            <div className="expense-name">

                                {item.name}

                            </div>

                            <div className="expense-progress">

                                <div
                                    className={`progress-fill ${item.color}`}
                                    style={{
                                        width:`${item.percent}%`
                                    }}
                                >

                                </div>

                            </div>

                            <div className="expense-price">

                                ₹{item.amount.toLocaleString()}

                                <span>

                                    ({item.percent}%)

                                </span>

                            </div>

                        </div>

                    ))

                }

                <button className="add-expense">

                    <FaPlus/>

                    Add Expense

                </button>

            </div>

        </div>

    );

}