import React, { useCallback, useEffect, useState } from 'react'
import { AppTypes } from '../../common'
import { useAppNavigate, useErrorHandler } from '../../common/hooks'
import { BoardService } from '../../common/services'
import ValueUtils from '../../common/utils/ValueUtils'
import './Board.css'

const Board = () => {
  const { navigateTo } = useAppNavigate()
  const { error, handleError, clearError } = useErrorHandler()
  const [keyword, setKeyword] = useState('')
  const [list, setList] = useState([])

  const search = useCallback(async (searchKeyword = '') => {
    try {
      const response = await BoardService.list(searchKeyword)
      return response.data
    } catch (err) {
      throw err
    }
  }, [])

  // 첫 렌더링 때 한 번만 API 호출
  useEffect(() => {
    search()
      .then((data) => setList(data))
      .catch((err) => {
        setList([])
        handleError(err.response)
      })
  }, [search, handleError])

  useEffect(() => {
    if (error && error.message) {
      alert(error.message)
      clearError()
    }
  }, [error, clearError])

  const handleKeywordChange = useCallback((e) => {
    setKeyword(e.target.value)
  }, [])

  const handleSearch = useCallback(async () => {
    search(keyword)
      .then((data) => setList(data))
      .catch((err) => {
        setList([])
        handleError(err.response)
      })
  }, [keyword, search, handleError])

  const handleAddClick = useCallback(() => {
    navigateTo('/board/add')
  }, [navigateTo])

  const handleRowClick = useCallback(
    (id) => {
      navigateTo(`/board/${id}`)
    },
    [navigateTo],
  )
  return (
    <div className="board-container">
      <div className="board-actions">
        <input type="text" placeholder="검색어를 입력하세요..." className="search-input" onChange={handleKeywordChange} />
        <button className="search-btn" onClick={handleSearch}>
          검색
        </button>
        <button className="add-btn" onClick={handleAddClick}>
          글쓰기
        </button>
      </div>
      <table>
        <thead>
          <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
            <th>조회수</th>
          </tr>
        </thead>
        <tbody>
          {list.map((post) => (
            <tr onClick={() => handleRowClick(post.id)} key={post.id}>
              <td>{post.id}</td>
              <td className="title">{ValueUtils.nvl(post.title)}</td>
              <td>{post.writer || ''}</td>
              <td>{post.created_dt || ''}</td>
              <td>{post.view_count || 0}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}

export default Board
