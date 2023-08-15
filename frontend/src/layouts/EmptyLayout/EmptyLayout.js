import PropTypes from 'prop-types'
import React from 'react'
import './EmptyLayout.css'

const EmptyLayout = (props) => {
  const { children } = props
  return <div className="empty-layout-container">{children}</div>
}

EmptyLayout.propTypes = {
  children: PropTypes.node,
}

export default EmptyLayout
