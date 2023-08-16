import React from 'react'
import { useNavigate } from 'react-router-dom'
import './Board.css'

const Board = () => {
  const navigate = useNavigate()

  const handleSearchClick = () => {
    // todo api call
    // navigate('/board')
  }
  const handleAddClick = () => {
    navigate('/board/add')
  }

  const handlePostClick = (id) => {
    navigate(`/board/${id}`)
  }

  return (
    <div className="board-container">
      <div className="board-actions">
        <input type="text" placeholder="검색어를 입력하세요..." className="search-input" />
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
          <tr onClick={() => handlePostClick(1)}>
            <td>1</td>
            <td className="title">게시글1</td>
            <td>inthej</td>
            <td>2023-08-15</td>
            <td>10</td>
          </tr>
          <tr onClick={() => handlePostClick(2)}>
            <td>2</td>
            <td className="title">게시글2</td>
            <td>inthej</td>
            <td>2023-08-15</td>
            <td>10</td>
          </tr>
          <tr onClick={() => handlePostClick(3)}>
            <td>3</td>
            <td className="title">게시글3</td>
            <td>inthej</td>
            <td>2023-08-15</td>
            <td>10</td>
          </tr>
        </tbody>
      </table>
    </div>
  )
}

export default Board
