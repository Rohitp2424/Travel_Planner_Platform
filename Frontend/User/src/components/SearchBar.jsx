import "../styles/SearchBar.css";

function SearchBar() {
  return (
    <div className="search-wrapper">

      <div className="search-box">

        <div className="search-item">
          <span className="icon">📍</span>
          <div>
            <h4>Where to?</h4>
            <p>Destination</p>
          </div>
        </div>

        <div className="search-item">
          <span className="icon">📅</span>
          <div>
            <h4>Check-in</h4>
            <input type="date" />
          </div>
        </div>

        <div className="search-item">
          <span className="icon">📅</span>
          <div>
            <h4>Check-out</h4>
            <input type="date" />
          </div>
        </div>

        <div className="search-item">
          <span className="icon">👤</span>
          <div>
            <h4>Travellers</h4>
            <select>
              <option>1 Adult</option>
              <option>2 Adults</option>
              <option>3 Adults</option>
              <option>4 Adults</option>
            </select>
          </div>
        </div>

        <button className="search-btn">
          Search
        </button>

      </div>

    </div>
  );
}

export default SearchBar;