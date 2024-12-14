import React, { useState } from "react";
import Slider from "react-slick";
import RoomResult from "../common/RoomResult";
import RoomSearch from "../common/RoomSearch";

const HomePage = () => {
    const [roomSearchResults, setRoomSearchResults] = useState([]);

    // Function to handle search results
    const handleSearchResult = (results) => {
        setRoomSearchResults(results);
    };

    // Slider settings
    const sliderSettings = {
        dots: true,
        infinite: true,
        speed: 500,
        slidesToShow: 1,
        slidesToScroll: 1,
        autoplay: true,
        autoplaySpeed: 3000,
        arrows: false, // Show or hide navigation arrows
    };

    return (
        <div className="home">
            {/* HEADER / SLIDING BANNER ROOM SECTION */}
            <section className="slider-container">
    <header className="header-banner">
        <Slider {...sliderSettings}>
            <div>
                <img
                    src="./assets/images/dal1.webp"
                    alt="Heaven Hotel 1"
                    className="header-image"
                />
            </div>
            <div>
                <img
                    src="./assets/images/dal2.webp"
                    alt="Heaven Hotel 2"
                    className="header-image"
                />
            </div>
            <div>
                <img
                    src="./assets/images/royal-kona-resort (2).jpg"
                    alt="Heaven Hotel 3"
                    className="header-image"
                />
            </div>
        </Slider>
        <div className="room-search-overlay">
            <RoomSearch handleSearchResult={handleSearchResult} />
        </div>
    </header>
</section>
            {/* NEW SERVICES AS CARDS SECTION */}
            <h2 className="home-services">Explore More Services</h2>
            <section className="service-cards">
                <div className="card">
                    <img src="./assets/images/gym.jpeg" alt="Gym Facility" />
                    <div className="card-content">
                        <h3>State-of-the-Art Gym</h3>
                        <p>Stay fit and energized with our fully equipped gym facilities, open 24/7 for your convenience.</p>
                    </div>
                </div>
                <div className="card">
                    <img src="./assets/images/spa.jpeg" alt="Spa" />
                    <div className="card-content">
                        <h3>Relaxing Spa</h3>
                        <p>Unwind and rejuvenate with our range of luxurious spa treatments tailored for your relaxation.</p>
                    </div>
                </div>
                <div className="card">
                    <img src="./assets/images/pool.jpeg" alt="Swimming Pool" />
                    <div className="card-content">
                        <h3>Infinity Pool</h3>
                        <p>Take a refreshing dip in our rooftop infinity pool with breathtaking views of the city.</p>
                    </div>
                </div>
                <div className="card">
                    <img src="./assets/images/bar.jpeg" alt="Bar" />
                    <div className="card-content">
                        <h3>Rooftop Bar</h3>
                        <p>Enjoy handcrafted cocktails with stunning city views at our rooftop bar.</p>
                    </div>
                </div>
            </section>
        </div>
    );
};

export default HomePage;
