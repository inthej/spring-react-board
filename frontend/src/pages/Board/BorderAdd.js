import React, { useCallback, useEffect } from 'react'
import { useForm } from 'react-hook-form'
import { useAppNavigate, useErrorHandler } from '../../common/hooks'
import { boardService } from '../../common/services/BoardService'
import PromiseUtils from '../../common/utils/PromiseUtils'
import './BorderAdd.css'

const BorderAdd = () => {
  const { navigateBack, navigateTo } = useAppNavigate()
  const { error, handleError, clearError } = useErrorHandler()
  const {
    register,
    handleSubmit,
    formState: { isSubmitting, isSubmitted, errors },
  } = useForm()

  useEffect(() => {
    if (error && error.message) {
      alert(error.message)
      clearError()
    }
  }, [error, clearError])

  const handleCancelClick = useCallback(
    (e) => {
      e.preventDefault()
      navigateBack()
    },
    [navigateBack],
  )

  const onSubmit = useCallback(
    async (data) => {
      const payload = {
        ...data,
        writer: data.author,
      }
      delete payload.author

      try {
        await PromiseUtils.wait(1_000)
        await boardService.create(payload)
        navigateTo('/board')
      } catch (err) {
        handleError(err.response)
      }
    },
    [navigateTo, handleError],
  )

  const checkAriaInvalid = useCallback((fieldError) => {
    return isSubmitted ? !!fieldError : undefined
  }, [])

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
            aria-invalid={checkAriaInvalid(errors.title)}
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
            aria-invalid={checkAriaInvalid(errors.author)}
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
            aria-invalid={checkAriaInvalid(errors.password)}
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
            aria-invalid={checkAriaInvalid(errors.content)}
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
