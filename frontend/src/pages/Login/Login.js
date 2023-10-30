import React from 'react'
import { Link } from 'react-router-dom'
import './Login.css'

const Login = () => {
  return (
    <div className="login-container">
      <form action="/" method="post">
        <div className="img-container">
          <img src="img/img_avatar2.png" alt="Avatar" className="avatar" />
        </div>

        <div className="input-container">
          <label htmlFor="uname">
            <b>Username</b>
          </label>
          <input type="text" placeholder="Enter Username" name="uname" required />

          <label htmlFor="psw">
            <b>Password</b>
          </label>
          <input type="password" placeholder="Enter Password" name="psw" required />

          <button type="submit" className="login-button">
            Login
          </button>

          <div className="remember-me">
            <input type="checkbox" checked="checked" name="remember" />
            <label>Remember me</label>
          </div>
        </div>

        <div className="footer-container">
          <button type="button" className="cancel-button">
            Cancel
          </button>
          <span className="password-reset">
            Forgot <Link to="#">password?</Link>
          </span>
        </div>
      </form>
    </div>
  )
}

export default Login
