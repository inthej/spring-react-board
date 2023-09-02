import React, { useCallback, useEffect, useState } from 'react'
import { useForm } from 'react-hook-form'
import { useParams } from 'react-router-dom'
import { AppTypes } from '../../common'
import { useAppNavigate, useErrorHandler } from '../../common/hooks'
import { BoardService } from '../../common/services'
import PromiseUtils from '../../common/utils/PromiseUtils'
import './BorderView.css'

const BorderView = () => {
  // params
  const { mode, id } = useParams() // mode: AppTypes.PageMode
  const [currentMode, setCurrentMode] = useState(mode || AppTypes.PageMode.view)
  // hooks
  const { navigateBack, navigateTo } = useAppNavigate()
  const { error, handleError, clearError } = useErrorHandler()
  // form
  const {
    register,
    handleSubmit,
    setValue,
    watch,
    formState: { isSubmitting, isSubmitted, errors },
  } = useForm()
  const values = watch()

  const search = useCallback(async () => {
    try {
      const response = await BoardService.get(id)
      return response.data
    } catch (err) {
      throw err
    }
  }, [id])

  useEffect(() => {
    if (id && currentMode === AppTypes.PageMode.view) {
      search()
        .then((data) => {
          Object.keys(data).forEach((key) => {
            if (key === 'writer') {
              setValue('author', data[key])
            } else {
              setValue(key, data[key])
            }
          })
        })
        .catch((err) => {
          handleError(err)
        })
    }
  }, [id, currentMode, search, setValue, handleError])

  useEffect(() => {
    if (error && error.message) {
      alert(error.message)
      clearError()
    }
  }, [error, clearError])

  const handleListClick = useCallback(
    (e) => {
      e.preventDefault()
      navigateBack()
    },
    [navigateBack],
  )

  const handleEditClick = useCallback((e) => {
    e.preventDefault()
    setCurrentMode(AppTypes.PageMode.edit)
  }, [])

  const handleDeleteClick = useCallback(async () => {
    if (window.confirm('정말로 이 게시물을 삭제하시겠습니다?')) {
      try {
        await BoardService.delete(id)
        navigateBack()
      } catch (err) {
        handleError(err)
      }
    }
  }, [handleError, id, navigateBack])

  const handleCancelClick = useCallback(
    (e) => {
      e.preventDefault()

      if (currentMode === AppTypes.PageMode.edit) {
        setCurrentMode(AppTypes.PageMode.view)
        return false
      }

      navigateBack()
    },
    [currentMode, navigateBack],
  )

  const onSubmit = useCallback(
    async (data) => {
      const payload = {
        title: data.title,
        writer: data.author,
        password: data.password,
        content: data.content,
      }

      try {
        await PromiseUtils.wait(1_000)

        if (currentMode === AppTypes.PageMode.add) {
          const { success, error } = await BoardService.create(payload)
          if (!success) throw error
          navigateTo('/board')
        }

        if (currentMode === AppTypes.PageMode.edit) {
          const { success, error } = await BoardService.update(id, payload)
          if (!success) throw error
          navigateBack()
        }
      } catch (err) {
        handleError(err)
      }
    },
    [currentMode, id, navigateTo, navigateBack, handleError],
  )

  const checkAriaInvalid = useCallback(
    (fieldError) => {
      return isSubmitted ? !!fieldError : undefined
    },
    [isSubmitted],
  )

  return (
    <div className="board-view-container">
      <h2>글쓰기</h2>
      <form noValidate onSubmit={handleSubmit(onSubmit)}>
        <div className="input-group">
          <label htmlFor="title">제목</label>
          <input
            type="text"
            id="title"
            placeholder="제목을 입력하세요..."
            value={values.title || ''}
            readOnly={currentMode === AppTypes.PageMode.view}
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
            value={values.author || ''}
            readOnly={currentMode === AppTypes.PageMode.view}
            {...register('author', {
              required: '작성자는 필수 입력입니다.',
            })}
            aria-invalid={checkAriaInvalid(errors.author)}
          />
          {errors.author && <small role="alert">{errors.author.message}</small>}
        </div>

        {currentMode !== AppTypes.PageMode.view && (
          <div className="input-group">
            <label htmlFor="password">비밀번호</label>
            <input
              type="password"
              id="password"
              placeholder="비밀번호를 입력하세요..."
              value={values.password || ''}
              readOnly={currentMode === AppTypes.PageMode.view}
              {...register('password', {
                required: '비밀번호는 필수 입력입니다.',
              })}
              aria-invalid={checkAriaInvalid(errors.password)}
            />
            {errors.password && <small role="alert">{errors.password.message}</small>}
          </div>
        )}

        <div className="input-group">
          <label htmlFor="content">내용</label>
          <textarea
            id="content"
            rows="10"
            placeholder="내용을 작성하세요..."
            value={values.content || ''}
            readOnly={currentMode === AppTypes.PageMode.view}
            {...register('content', {
              required: '글 내용이 작성되지 않았습니다.',
            })}
            aria-invalid={checkAriaInvalid(errors.content)}
          ></textarea>
          {errors.content && <small role="alert">{errors.content.message}</small>}
        </div>

        <div className="form-actions">
          {currentMode === AppTypes.PageMode.view && (
            <>
              <button type="button" className="list-btn" onClick={handleListClick}>
                목록
              </button>

              <div className="form-modify-actions">
                <button type="button" className="delete-btn" onClick={handleDeleteClick}>
                  삭제
                </button>

                <button type="button" className="edit-btn" onClick={handleEditClick}>
                  편집
                </button>
              </div>
            </>
          )}

          {currentMode !== AppTypes.PageMode.view && (
            <>
              <button type="button" className="cancel-btn" onClick={handleCancelClick} disabled={isSubmitting}>
                취소
              </button>

              {currentMode === AppTypes.PageMode.add && (
                <button type="submit" className="submit-btn" disabled={isSubmitting}>
                  등록
                </button>
              )}

              {currentMode === AppTypes.PageMode.edit && (
                <button type="submit" className="submit-btn" disabled={isSubmitting}>
                  수정
                </button>
              )}
            </>
          )}
        </div>
      </form>

      <div className="comments-section">
        <div className="comments-list">
          <div className="comment">
            <div className="comment-author">작성자: 김나리</div>
            <div className="comment-text">This is a comment.</div>
          </div>
        </div>

        <div className="comment-form">
          <div className="comment-user-group">
            <input type="text" placeholder="작성자명" className="comment-author-input" />
            <input type="password" placeholder="비밀번호" className="comment-password-input" />
          </div>
          <textarea placeholder="댓글을 입력하세요..."></textarea>
          <button className="submit-comment">등록</button>
        </div>
      </div>
    </div>
  )
}

BorderView.propTypes = {}

export default BorderView
