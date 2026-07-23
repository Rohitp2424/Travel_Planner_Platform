import "./ProgressBar.css";

export default function ProgressBar({ currentStep }) {

    const steps = [
        "Details",
        "Itinerary",
        "Flights",
        "Hotels",
        "Review",
        "Confirmation"
    ];

    return (

        <div className="progress-bar">

            {steps.map((step, index) => (

                <div className="progress-item" key={index}>

                    <div
                        className={`progress-circle
                        ${currentStep === index + 1 ? "active" : ""}
                        ${currentStep > index + 1 ? "completed" : ""}`}
                    >
                        {index + 1}
                    </div>

                    <span>{step}</span>

                    {index !== steps.length - 1 && (
                        <div
                            className={`progress-line
                            ${currentStep > index + 1 ? "line-completed" : ""}`}
                        ></div>
                    )}

                </div>

            ))}

        </div>

    );

}