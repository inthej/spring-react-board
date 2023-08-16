import React from 'react'
import { useForm } from 'react-hook-form'
import { useNavigate } from 'react-router-dom'
import PromiseUtils from '../../common/utils/PromiseUtils'
import './BorderAdd.css'

const BorderAdd = () => {
  const navigate = useNavigate()
  const {
    register,
    handleSubmit,
    formState: { isSubmitting, isSubmitted, errors },
  } = useForm()
  const handleCancelClick = (e) => {
    e.preventDefault()
    navigate(-1)
    // todo reset
  }

  const onSubmit = async (data) => {
    await PromiseUtils.wait(1_000)
    alert(JSON.stringify(data))
  }

  return (
    <div className="border-add-container">
      <h2>글쓰기</h2>
      <form noValidate onSubmit={handleSubmit(onSubmit)}>
        <div className="input-group">
          <label htmlFor="title">제목</label>
          <input
            type="text"
            id="title"
            placeholder="제목을 입력하세요..."
            {...register('title', {
              required: '제목은 필수 입력입니다.',
            })}
            aria-invalid={isSubmitted ? (errors.title ? 'true' : 'false') : undefined}
          />
          {errors.title && <small role="alert">{errors.title.message}</small>}
        </div>

        <div className="input-group">
          <label htmlFor="author">작성자</label>
          <input
            type="text"
            id="author"
            placeholder="작성자명"
            {...register('author', {
              required: '작성자는 필수 입력입니다.',
            })}
            aria-invalid={isSubmitted ? (errors.author ? 'true' : 'false') : undefined}
          />
          {errors.author && <small role="alert">{errors.author.message}</small>}
        </div>

        <div className="input-group">
          <label htmlFor="password">비밀번호</label>
          <input
            type="password"
            id="password"
            placeholder="비밀번호를 입력하세요..."
            {...register('password', {
              required: '비밀번호는 필수 입력입니다.',
            })}
            aria-invalid={isSubmitted ? (errors.password ? 'true' : 'false') : undefined}
          />
          {errors.password && <small role="alert">{errors.password.message}</small>}
        </div>

        <div className="input-group">
          <label htmlFor="content">내용</label>
          <textarea
            id="content"
            rows="10"
            placeholder="내용을 작성하세요..."
            {...register('content', {
              required: '글 내용이 작성되지 않았습니다.',
            })}
            aria-invalid={isSubmitted ? (errors.content ? 'true' : 'false') : undefined}
          ></textarea>
          {errors.content && <small role="alert">{errors.content.message}</small>}
        </div>

        <div className="form-actions">
          <button type="button" className="cancel-btn" onClick={handleCancelClick} disabled={isSubmitting}>
            취소
          </button>

          <button type="submit" className="submit-btn" disabled={isSubmitting}>
            등록
          </button>
        </div>
      </form>
    </div>
  )
}

BorderAdd.propTypes = {}

export default BorderAdd
