import React from 'react'
import { useNavigate } from 'react-router-dom'
import './BorderAdd.css'

const BorderAdd = () => {
  const navigate = useNavigate()
  const handleCancelClick = (e) => {
    e.preventDefault()
    navigate(-1)
    // todo reset
  }

  const handleSubmit = () => {
    // todo: api call
    navigate('/board')
  }

  return (
    <div className="border-add-container">
      <h2>글쓰기</h2>
      <form onSubmit={handleSubmit}>
        <div className="input-group">
          <label htmlFor="title">제목</label>
          <input type="text" id="title" placeholder="제목을 입력하세요..." />
        </div>

        <div className="input-group">
          <label htmlFor="author">작성자</label>
          <input type="text" id="author" placeholder="작성자명" />
        </div>

        <div className="input-group">
          <label htmlFor="password">비밀번호</label>
          <input type="password" id="password" placeholder="비밀번호를 입력하세요..." />
        </div>

        <div className="input-group">
          <label htmlFor="content">내용</label>
          <textarea id="content" rows="10" placeholder="내용을 작성하세요..."></textarea>
        </div>

        <div className="form-actions">
          <button type="button" className="cancel-btn" onClick={handleCancelClick}>
            취소
          </button>

          <button type="submit" className="submit-btn">
            등록
          </button>
        </div>
      </form>
    </div>
  )
}

BorderAdd.propTypes = {}

export default BorderAdd
