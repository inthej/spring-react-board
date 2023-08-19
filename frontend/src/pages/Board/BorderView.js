import React, { useCallback, useEffect, useState } from 'react'
import { useForm } from 'react-hook-form'
import { useParams } from 'react-router-dom'
import { AppTypes } from '../../common'
import { useAppNavigate, useErrorHandler } from '../../common/hooks'
import { BoardService } from '../../common/services'
import PromiseUtils from '../../common/utils/PromiseUtils'
import './BorderView.css'

const BorderView = () => {
  // mode
  const { id, mode } = useParams() // mode: AppTypes.Mode
  const [currentMode, setCurrentMode] = useState(mode || AppTypes.Mode.view)
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
    } catch (e) {
      throw e
    }
  }, [id])

  useEffect(() => {
    if (id && currentMode === AppTypes.Mode.view) {
      search()
        .then((data) => {
          Object.keys(data).forEach((key) => {
            setValue(key, data[key])
          })
        })
        .catch((err) => {
          handleError(err.response)
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
        await BoardService.create(payload)
        navigateTo('/board')
      } catch (err) {
        handleError(err.response)
      }
    },
    [navigateTo, handleError],
  )

  const checkAriaInvalid = useCallback(
    (fieldError) => {
      return isSubmitted ? !!fieldError : undefined
    },
    [isSubmitted],
  )

  console.log('id:', id)
  console.log('currentMode:', currentMode)

  return (
    <div className="border-view-container">
      <h2>글쓰기</h2>
      <form noValidate onSubmit={handleSubmit(onSubmit)}>
        <div className="input-group">
          <label htmlFor="title">제목</label>
          <input
            type="text"
            id="title"
            placeholder="제목을 입력하세요..."
            value={values.title || ''}
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
            value={values.writer || ''}
            {...register('author', {
              required: '작성자는 필수 입력입니다.',
            })}
            aria-invalid={checkAriaInvalid(errors.author)}
          />
          {errors.author && <small role="alert">{errors.author.message}</small>}
        </div>

        {currentMode === AppTypes.Mode.edit && (
          <div className="input-group">
            <label htmlFor="password">비밀번호</label>
            <input
              type="password"
              id="password"
              placeholder="비밀번호를 입력하세요..."
              value={values.password || ''}
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
            {...register('content', {
              required: '글 내용이 작성되지 않았습니다.',
            })}
            aria-invalid={checkAriaInvalid(errors.content)}
          ></textarea>
          {errors.content && <small role="alert">{errors.content.message}</small>}
        </div>

        <div className="form-actions">
          {currentMode === AppTypes.Mode.view && (
            <button type="submit" className="list-btn" onClick={handleListClick}>
              목록
            </button>
          )}

          {currentMode === AppTypes.Mode.add && (
            <>
              <button type="button" className="cancel-btn" onClick={handleCancelClick} disabled={isSubmitting}>
                취소
              </button>

              <button type="submit" className="submit-btn" disabled={isSubmitting}>
                등록
              </button>
            </>
          )}
        </div>
      </form>
    </div>
  )
}

BorderView.propTypes = {}

export default BorderView
