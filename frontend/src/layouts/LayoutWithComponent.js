import React from 'react'
import PropTypes from 'prop-types'

const LayoutWithComponent = (props) => {
  const { layout: Layout, component: Component, ...rest } = props
  return (
    <Layout {...rest}>
      <Component />
    </Layout>
  )
}

LayoutWithComponent.propTypes = {
  layout: PropTypes.elementType.isRequired,
  component: PropTypes.elementType.isRequired,
}

export default LayoutWithComponent
