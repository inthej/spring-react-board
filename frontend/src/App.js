import React from 'react'
import { Helmet } from 'react-helmet'
import { Navigate, Route, Routes } from 'react-router-dom'
import { createGlobalStyle } from 'styled-components'
import { LayoutWithComponent } from './layouts'
import EmptyLayout from './layouts/EmptyLayout'
import MainLayout from './layouts/MainLayout'
import { BoardList, BoardView } from './pages/Board'
import Home from './pages/Home'
import Login from './pages/Login'
import Signup from './pages/Signup'

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
        <Route path="/" element={<LayoutWithComponent layout={MainLayout} component={Home} />} />
        <Route path="/board/list" element={<LayoutWithComponent layout={MainLayout} component={BoardList} />} />
        <Route path="/board/view/:no" element={<LayoutWithComponent layout={MainLayout} component={BoardView} />} />
        <Route path="/board/:mode" element={<LayoutWithComponent layout={MainLayout} component={BoardView} />} />
        <Route path="/login" element={<LayoutWithComponent layout={EmptyLayout} component={Login} />} />
        <Route path="/signup" element={<LayoutWithComponent layout={EmptyLayout} component={Signup} />} />
        <Route path="/board" element={<Navigate to="/board/list" replace />} />
        <Route path="*" element={<Navigate to="/" replace />} />
      </Routes>
    </>
  )
}

export default App
