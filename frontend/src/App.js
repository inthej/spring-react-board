import React from 'react'
import { Helmet } from 'react-helmet'
import { Navigate, Route, Routes } from 'react-router-dom'
import { createGlobalStyle } from 'styled-components'
import { LayoutWithComponent } from './layouts'
import EmptyLayout from './layouts/EmptyLayout'
import { Board, BoardView } from './pages/Board'

const GlobalStyle = createGlobalStyle`
  * {
    box-sizing: border-box;
  }

  body {
    margin: 0;
    font-size: small;
    font-family: Arial, Helvetica, sans-serif;
  }
`

const App = () => {
  return (
    <>
      <GlobalStyle />
      <Helmet>
        <title>Spring-React-Board</title>
        <meta name="description" content="스프링 리액트 게시판"></meta>
      </Helmet>
      <Routes>
        <Route path="/board/list" element={<LayoutWithComponent layout={EmptyLayout} component={Board} />} />
        <Route path="/board/view/:id" element={<LayoutWithComponent layout={EmptyLayout} component={BoardView} />} />
        <Route path="/board/:mode" element={<LayoutWithComponent layout={EmptyLayout} component={BoardView} />} />
        <Route path="*" element={<Navigate to="/board/list" replace />} />
      </Routes>
    </>
  )
}

export default App
