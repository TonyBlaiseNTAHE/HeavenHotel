import React, { useState } from "react";
import ApiService from "../../service/ApiService";

function ForgotPasswordPage() {
    const [email, setEmail] = useState('');
    const [message, setMessage] = useState('');
    const [error, setError] = useState('');

    const handleForgotPassword = async (e) => {
        e.preventDefault();

        if (!email) {
            setError('Please provide your email.');
            setTimeout(() => setError(''), 5000);
            return;
        }

        try {
            const response = await ApiService.requestPasswordReset({ email });
            setMessage(response.message);
        } catch (error) {
            setError(error.response?.data?.message || error.message);
            setTimeout(() => setError(''), 5000);
        }
    };

    return (
        <div className="auth-container">
            <h2>Forgot Password</h2>
            {error && <p className="error-message">{error}</p>}
            {message && <p className="success-message">{message}</p>}
            <form onSubmit={handleForgotPassword}>
                <div className="form-group">
                    <label>Email: </label>
                    <input
                        type="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                </div>
                <button type="submit">Submit</button>
            </form>
            <p className="back-to-login">
                <a href="/login">Back to Login</a>
            </p>
        </div>
    );
}

export default ForgotPasswordPage;
