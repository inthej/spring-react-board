import React, { useCallback, useEffect, useRef, useState } from 'react'
import { AppConstants, AppTypes } from '../../common'
import { useAppNavigate, useErrorHandler } from '../../common/hooks'
import { BoardService } from '../../common/services'
import ValueUtils from '../../common/utils/ValueUtils'
import './Board.css'

const Board = () => {
  const { navigateTo } = useAppNavigate()
  const { error, handleError, clearError } = useErrorHandler()
  const [keyword, setKeyword] = useState('')
  const searchInputRef = useRef(null)
  const [page] = useState({
    page: 1,
    size: AppConstants.DEFAULT_PAGE_SIZE,
  })
  const [pageList, setPageList] = useState([])

  const search = useCallback(
    async (searchKeyword = '') => {
      try {
        const response = await BoardService.list(searchKeyword, page)
        return response.data
      } catch (e) {
        throw e
      }
    },
    [page],
  )

  // 첫 렌더링 때 한 번만 API 호출
  useEffect(() => {
    search()
      .then((data) => setPageList(data.list))
      .catch((err) => {
        setPageList([])
        handleError(err.response)
      })
      .finally(() => {
        if (searchInputRef.current) {
          searchInputRef.current.focus()
        }
      })
  }, [search, handleError])

  useEffect(() => {
    if (error && error.message) {
      alert(error.message)
      clearError()
    }
  }, [error, clearError])

  const searchKeyword = useCallback(
    async (inputKeyword) => {
      search(inputKeyword)
        .then((data) => setPageList(data.list))
        .catch((err) => {
          setPageList([])
          handleError(err.response)
        })
        .finally(() => {
          if (searchInputRef.current) {
            searchInputRef.current.focus()
          }
        })
    },
    [search, handleError],
  )

  const handleKeywordChange = useCallback((e) => {
    setKeyword(e.target.value)
  }, [])

  const handleSearchEnter = useCallback(
    (e) => {
      if (e.key === 'Enter') {
        searchKeyword(keyword)
      }
    },
    [keyword, searchKeyword],
  )

  const handleSearchClick = useCallback(async () => {
    searchKeyword(keyword)
  }, [keyword, searchKeyword])

  const handleAddClick = useCallback(() => {
    navigateTo(`/board/${AppTypes.PageMode.add}`)
  }, [navigateTo])

  const handleRowClick = useCallback(
    (id) => {
      navigateTo(`/board/view/${id}`)
    },
    [navigateTo],
  )

  return (
    <div className="board-container">
      <div className="board-actions">
        <input type="text" placeholder="검색어를 입력하세요..." className="search-input" onChange={handleKeywordChange} onKeyDown={handleSearchEnter} ref={searchInputRef} />
        <button className="search-btn" onClick={handleSearchClick}>
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
          {pageList.map((post) => (
            <tr onClick={() => handleRowClick(post.id)} key={post.id}>
              <td>{post.rownum}</td>
              <td className="title">{ValueUtils.nvl(post.title)}</td>
              <td>{ValueUtils.nvl(post.writer)}</td>
              <td>{ValueUtils.nvl(post.created_dt)}</td>
              <td>{ValueUtils.nvl(post.view_count, 0)}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}

export default Board
