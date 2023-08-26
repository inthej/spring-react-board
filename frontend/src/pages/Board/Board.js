import _ from 'lodash'
import React, { useCallback, useEffect, useRef, useState } from 'react'
import { AppConstants, AppTypes } from '../../common'
import { useAppNavigate, useErrorHandler } from '../../common/hooks'
import { BoardService } from '../../common/services'
import ValueUtils from '../../common/utils/ValueUtils'
import './Board.css'

const Board = () => {
  const { navigateTo } = useAppNavigate()
  const { error, handleError, clearError } = useErrorHandler()
  const searchInputRef = useRef(null)
  const [page, setPage] = useState({
    page: 1,
    size: AppConstants.DEFAULT_PAGE_SIZE,
    keyword: '',
  })

  const [pageList, setPageList] = useState({
    total: 0,
    pages: 0,
    list: [],
  })

  const debounceKeywordChange = useCallback(_.debounce(changeKeyword, 300), [])
  function changeKeyword(keyword) {
    setPage((prevPage) => ({
      ...prevPage,
      page: 1,
      keyword,
    }))
  }

  const search = useCallback(async () => {
    try {
      const response = await BoardService.list(page)
      return response.data
    } catch (err) {
      throw err
    }
  }, [page])

  // 첫 렌더링 때 한 번만 API 호출
  useEffect(() => {
    search()
      .then((data) => setPageList(data))
      .catch((err) => {
        setPageList((prevPageList) => prevPageList)
        handleError(err)
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
  }, [clearError, error])

  const handleKeywordChange = useCallback(
    (e) => {
      const keyword = e.target.value
      debounceKeywordChange(keyword)
    },
    [debounceKeywordChange],
  )

  const handleSearchEnter = useCallback(
    (e) => {
      if (e.key === 'Enter') {
        search()
      }
    },
    [search],
  )

  const handleAddBtnClick = useCallback(() => {
    navigateTo(`/board/${AppTypes.PageMode.add}`)
  }, [navigateTo])

  const handleRowSelect = useCallback(
    (id) => {
      navigateTo(`/board/view/${id}`)
    },
    [navigateTo],
  )

  const handlePrevBtnClick = useCallback(() => {
    if (page.page > 1) {
      setPage((prevPage) => ({
        ...prevPage,
        page: prevPage.page - 1,
      }))
    }
  }, [page])

  const handleNextBtnClick = useCallback(() => {
    if (page.page < pageList.pages) {
      setPage((prevPage) => ({
        ...prevPage,
        page: prevPage.page + 1,
      }))
    }
  }, [page, pageList.pages])

  return (
    <div className="board-container">
      <div className="board-header">
        <div className="board-actions">
          <input type="text" placeholder="검색어를 입력하세요..." className="search-input" onChange={handleKeywordChange} onKeyDown={handleSearchEnter} ref={searchInputRef} />
          <button className="add-btn" onClick={handleAddBtnClick}>
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
            {pageList.list.map((post) => (
              <tr onClick={() => handleRowSelect(post.id)} key={post.id}>
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
      <div className="board-footer">
        <button className="page-btn" onClick={handlePrevBtnClick} disabled={page.page <= 1}>
          &lt;
        </button>
        <span>
          {page.page} / {pageList.pages}
        </span>
        <button className="page-btn" onClick={handleNextBtnClick} disabled={page.page >= pageList.pages}>
          &gt;
        </button>
      </div>
    </div>
  )
}

export default Board
