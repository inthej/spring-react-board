import React from 'react'
import './BoardView.css'

const BoardView = () => {
  const post = {
    title: '게시글 제목',
    author: 'inthej',
    date: '2023-08-15',
    views: 25,
    content: '이곳에 게시글의 상세 내용이 표시됩니다.',
  }

  return (
    <div className="board-view-container">
      <h2 className="board-view-title">{post.title}</h2>
      <div className="board-view-meta">
        작성자: {post.author} | 작성일: {post.date} | 조회수: {post.views}
      </div>
      <div className="board-view-content">{post.content}</div>
      <button className="board-view-back-btn">글목록으로 가기</button>
    </div>
  )
}

BoardView.propTypes = {}

export default BoardView
