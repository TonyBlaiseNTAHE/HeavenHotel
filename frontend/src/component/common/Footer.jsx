import React from "react";
import { FaFacebook, FaTwitter, FaInstagram } from "react-icons/fa";

const FooterComponent = () => {
    return (
        <footer className="footer">
            <div className="footer-content">
                <div className="footer-section">
                    <h3>Contact Us</h3>
                    <p>Email: info@heavenhotel.com</p>
                    <p>Phone: +123-456-7890</p>
                    <p>Address: 123 Paradise Lane, City, Country</p>
                </div>
                <div className="footer-section">
                    <h3>Quick Links</h3>
                    <ul>
                        <li><a href="/about">About Us</a></li>
                        <li><a href="/services">Services</a></li>
                        <li><a href="/rooms">Rooms</a></li>
                        <li><a href="/contact">Contact</a></li>
                    </ul>
                </div>
                <div className="footer-section">
                    <h3>Follow Us</h3>
                    <div className="social-icons">
                        <a href="https://facebook.com" target="_blank" rel="noopener noreferrer">
                            <FaFacebook size={25} style={{ color: "#4267B2", margin: "0 10px" }} />
                        </a>
                        <a href="https://twitter.com" target="_blank" rel="noopener noreferrer">
                            <FaTwitter size={25} style={{ color: "#1DA1F2", margin: "0 10px" }} />
                        </a>
                        <a href="https://instagram.com" target="_blank" rel="noopener noreferrer">
                            <FaInstagram size={25} style={{ color: "#E4405F", margin: "0 10px" }} />
                        </a>
                    </div>
                </div>
            </div>
            <div className="footer-bottom">
                <span>
                    Heaven Hotel | All Rights Reserved &copy; {new Date().getFullYear()}
                </span>
            </div>
        </footer>
    );
};

export default FooterComponent;
