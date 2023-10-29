import PropTypes from 'prop-types'
import React from 'react'
import { Link, NavLink } from 'react-router-dom'
import { Content, Topnav } from './components'
import './MainLayout.css'

const MainLayout = (props) => {
  const { children } = props
  return (
    <div className="main-layout-container">
      <Topnav className="topnav">
        <NavLink to="/" activeclassname="active">
          Home
        </NavLink>
        <NavLink to="/board" activeclassname="active">
          Board
        </NavLink>
        <Link to="/login" className="split">
          Login
        </Link>
      </Topnav>

      <Content className="content">{children}</Content>
    </div>
  )
}

MainLayout.propTypes = {
  children: PropTypes.node,
}

export default MainLayout
