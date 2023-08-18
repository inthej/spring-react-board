import { useCallback } from 'react'
import { useNavigate } from 'react-router-dom'

const useAppNavigate = () => {
  const navigate = useNavigate()

  const navigateBack = useCallback(() => {
    navigate(-1)
  }, [navigate])

  const navigateTo = useCallback(
    (path) => {
      navigate(path)
    },
    [navigate],
  )

  return { navigateBack, navigateTo }
}

export default useAppNavigate
