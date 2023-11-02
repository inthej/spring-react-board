import React from 'react'
import { Link } from 'react-router-dom'
import './Signup.css'

const Signup = () => {
  return (
    <div className="signup-container">
      <form className="signup-form">
        <h1>Sign Up</h1>
        <p>Please fill in this form to create an account.</p>
        <hr className="signup-hr" />

        <label htmlFor="email" className="signup-label">
          <b>Email</b>
        </label>
        <input type="text" placeholder="Enter Email" name="email" className="signup-input" required />

        <label htmlFor="psw" className="signup-label">
          <b>Password</b>
        </label>
        <input type="password" placeholder="Enter Password" name="psw" className="signup-input" required />

        <label htmlFor="psw-repeat" className="signup-label">
          <b>Repeat Password</b>
        </label>
        <input type="password" placeholder="Repeat Password" name="psw-repeat" className="signup-input" required />

        <label className="signup-remember">
          <input type="checkbox" checked name="remember" className="signup-checkbox" /> Remember me
        </label>

        <p className="signup-terms">
          By creating an account you agree to our{' '}
          <Link to="#" className="signup-terms-link">
            Terms & Privacy
          </Link>
          .
        </p>

        <div className="signup-button-container">
          <button type="button" className="signup-cancelbtn">
            Cancel
          </button>
          <button type="submit" className="signup-signupbtn">
            Sign Up
          </button>
        </div>
      </form>
    </div>
  )
}

export default Signup
