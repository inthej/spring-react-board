import React from 'react'

const Content = (props) => {
  const { className, children } = props
  return <div className={className}>{children}</div>
}

export default Content
