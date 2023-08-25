import { useCallback, useState } from 'react'

const useErrorHandler = (initialState = null) => {
  const [error, setError] = useState(initialState)

  const handleError = useCallback((error) => {
    setError(error?.response?.data?.error || error)
  }, [])

  const clearError = useCallback(() => setError(null), [])

  return { error, handleError, clearError }
}

export default useErrorHandler
