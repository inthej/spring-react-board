import React, { useCallback, useEffect, useRef, useState } from 'react'
import { useForm } from 'react-hook-form'
import { useParams } from 'react-router-dom'
import { AppTypes } from '../../common'
import { useAppNavigate, useErrorHandler, useWindowActions } from '../../common/hooks'
import { BoardCommentService, BoardService } from '../../common/services'
import PromiseUtils from '../../common/utils/PromiseUtils'
import './BorderView.css'

const BorderView = () => {
  // params
  const { mode, id } = useParams() // mode: AppTypes.PageMode
  const [currentMode, setCurrentMode] = useState(mode || AppTypes.PageMode.view)
  // hooks
  const { windowReload, windowConfirm } = useWindowActions()
  const { navigateBack, navigateTo } = useAppNavigate()
  const { error, handleError, clearError } = useErrorHandler()
  // post
  const {
    register,
    handleSubmit,
    setValue,
    formState: { isSubmitting, isSubmitted, errors },
  } = useForm()
  // comment
  const [commentList, setCommentList] = useState({
    total: 0,
    pages: 0,
    list: [],
  })
  const {
    register: commentRegister,
    handleSubmit: handleCommentSubmit,
    formState: { isSubmitting: isCommentSubmitting, isSubmitted: isCommentSubmitted, errors: commentErrors },
  } = useForm()
  const commentAuthorInputRef = useRef(null)

  const search = useCallback(async () => {
    try {
      const response = await BoardService.get(id)
      return response.data
    } catch (err) {
      throw err
    }
  }, [id])

  const searchComments = useCallback(async () => {
    try {
      const response = await BoardCommentService.list(id)
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

  useEffect(() => {
    if (currentMode === AppTypes.PageMode.view) {
      searchComments()
        .then((data) => setCommentList(data))
        .catch((err) => {
          setCommentList((prevCommentList) => prevCommentList)
          handleError(err)
        })
    }
  }, [currentMode, handleError, searchComments])

  useEffect(() => {
    if (commentAuthorInputRef.current && currentMode === AppTypes.PageMode.view) {
      commentAuthorInputRef.current.focus()
    }
  }, [currentMode])

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
    if (windowConfirm('정말로 이 게시물을 삭제하시겠습니다?')) {
      try {
        await BoardService.delete(id)
        navigateBack()
      } catch (err) {
        handleError(err)
      }
    }
  }, [handleError, id, navigateBack, windowConfirm])

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
          if (!success) handleError(error)
          navigateTo('/board')
        }

        if (currentMode === AppTypes.PageMode.edit) {
          const { success, error } = await BoardService.update(id, payload)
          if (!success) {
            handleError(error)
            return false
          }
          navigateBack()
        }
      } catch (err) {
        handleError(err)
      }
    },
    [currentMode, id, navigateTo, navigateBack, handleError],
  )

  const onCommentSubmit = useCallback(
    async (data) => {
      const payload = {
        ...data,
      }
      try {
        await PromiseUtils.wait(1_000)
        const { success, error } = await BoardCommentService.create(id, payload)
        if (!success) handleError(error)
        windowReload()
      } catch (err) {
        handleError(err)
      }
    },
    [id, windowReload, handleError],
  )

  const checkError = useCallback(
    (fieldError) => {
      return isSubmitted ? !!fieldError : undefined
    },
    [isSubmitted],
  )

  const checkCommentError = useCallback(
    (fieldError) => {
      return isCommentSubmitted ? !!fieldError : undefined
    },
    [isCommentSubmitted],
  )

  return (
    <div className="board-view-container">
      <form noValidate onSubmit={handleSubmit(onSubmit)}>
        <div className="input-group">
          <label htmlFor="title">제목</label>
          <input
            type="text"
            id="title"
            placeholder="제목을 입력하세요..."
            readOnly={currentMode === AppTypes.PageMode.view}
            {...register('title', {
              required: '제목은 필수 입력입니다.',
            })}
            aria-invalid={checkError(errors.title)}
          />
          {errors.title && <small role="alert">{errors.title.message}</small>}
        </div>

        <div className="input-group">
          <label htmlFor="author">작성자</label>
          <input
            type="text"
            id="author"
            placeholder="작성자명"
            readOnly={currentMode === AppTypes.PageMode.view}
            {...register('author', {
              required: '작성자는 필수 입력입니다.',
            })}
            aria-invalid={checkError(errors.author)}
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
              readOnly={currentMode === AppTypes.PageMode.view}
              {...register('password', {
                required: '비밀번호는 필수 입력입니다.',
              })}
              aria-invalid={checkError(errors.password)}
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
            readOnly={currentMode === AppTypes.PageMode.view}
            {...register('content', {
              required: '글 내용이 작성되지 않았습니다.',
            })}
            aria-invalid={checkError(errors.content)}
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

      {currentMode === AppTypes.PageMode.view && (
        <div className="comments-section">
          <div className="comments-list">
            {commentList.list.map((comment) => (
              <div className="comment" key={comment.id}>
                <div className="comment-author">작성자: {comment.writer}</div>
                <div className="comment-text">{comment.content}</div>
              </div>
            ))}
          </div>

          <form noValidate onSubmit={handleCommentSubmit(onCommentSubmit)} className="comment-form">
            <div className="comment-user-group">
              <input
                className="comment-author-input"
                type="text"
                placeholder="작성자명"
                {...commentRegister('writer', {
                  required: '댓글 작성자는 필수 입력입니다.',
                })}
                aria-invalid={checkCommentError(commentErrors.writer)}
                ref={(e) => {
                  commentRegister('writer').ref(e) // react-hook-form 을 위한 ref
                  commentAuthorInputRef.current = e // 외부 ref
                }}
              />
              <input
                className="comment-password-input"
                type="password"
                placeholder="비밀번호"
                {...commentRegister('password', {
                  required: '비밀번호는 필수 입력입니다.',
                })}
                aria-invalid={checkCommentError(commentErrors.password)}
              />
            </div>
            <textarea
              placeholder="댓글을 입력하세요..."
              {...commentRegister('content', {
                required: '댓글 내용이 작성되지 않았습니다.',
              })}
              aria-invalid={checkCommentError(commentErrors.content)}
            ></textarea>
            <button className="submit-comment" disabled={isCommentSubmitting}>
              등록
            </button>
          </form>
        </div>
      )}
    </div>
  )
}

BorderView.propTypes = {}

export default BorderView
