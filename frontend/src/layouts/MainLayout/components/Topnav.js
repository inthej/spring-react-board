import React from 'react'

const Topnav = (props) => {
  const { className, children } = props
  return <div className={className}>{children}</div>
}

export default Topnav
