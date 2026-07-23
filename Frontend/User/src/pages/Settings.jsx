import "../styles/Settings.css";
import { useState } from "react";

export default function Settings() {

    const [settings, setSettings] = useState({
        notifications: true,
        flightUpdates: true,
        weatherAlerts: false,
        groupUpdates: true,
        promotions: false,
        darkMode: false
    });

    const handleToggle = (name) => {
        setSettings({
            ...settings,
            [name]: !settings[name]
        });
    };

    return (

        <div className="settings-page">

            <div className="settings-card">

                <h1>Settings</h1>

                <h3>Notification Preferences</h3>
                <div className="setting-row">

                    <span>Flight Updates</span>

                    <label className="switch">

                        <input
                            type="checkbox"
                            checked={settings.flightUpdates}
                            onChange={() => handleToggle("flightUpdates")}
                        />

                        <span className="slider"></span>

                    </label>

                </div>

                <div className="setting-row">

                    <span>Weather Alerts</span>

                    <label className="switch">

                        <input
                            type="checkbox"
                            checked={settings.weatherAlerts}
                            onChange={() => handleToggle("weatherAlerts")}
                        />

                        <span className="slider"></span>

                    </label>

                </div>

                <div className="setting-row">

                    <span>Group Updates</span>

                    <label className="switch">

                        <input
                            type="checkbox"
                            checked={settings.groupUpdates}
                            onChange={() => handleToggle("groupUpdates")}
                        />

                        <span className="slider"></span>

                    </label>

                </div>

                <div className="setting-row">

                    <span>Promotions & Offers</span>

                    <label className="switch">

                        <input
                            type="checkbox"
                            checked={settings.promotions}
                            onChange={() => handleToggle("promotions")}
                        />

                        <span className="slider"></span>

                    </label>

                </div>

                <h3>Other Settings</h3>

                <div className="setting-row">

                    <span>Dark Mode</span>

                    <label className="switch">

                        <input
                            type="checkbox"
                            checked={settings.darkMode}
                            onChange={() => handleToggle("darkMode")}
                        />

                        <span className="slider"></span>

                    </label>

                </div>

                <div className="setting-row">

                    <span>Language</span>

                    <select>

                        <option>English</option>
                        <option>Hindi</option>
                        <option>Marathi</option>

                    </select>

                </div>

                <div className="setting-row">

                    <span>Currency</span>

                    <select>

                        <option>INR (₹)</option>
                        <option>USD ($)</option>
                        <option>EUR (€)</option>

                    </select>

                </div>

            </div>

        </div>

    );

}