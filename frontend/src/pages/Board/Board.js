import React from 'react'
import './Board.css'

const Board = () => {
  return (
    <div className="board-container">
      <div className="board-actions">
        <input type="text" placeholder="검색어를 입력하세요..." className="search-input" />
        <button className="search-btn">검색</button>
        <button className="write-btn">글쓰기</button>
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
          <tr>
            <td>1</td>
            <td className="title">게시글1</td>
            <td>inthej</td>
            <td>2023-08-15</td>
            <td>10</td>
          </tr>
          <tr>
            <td>2</td>
            <td className="title">게시글2</td>
            <td>inthej</td>
            <td>2023-08-15</td>
            <td>10</td>
          </tr>
          <tr>
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
