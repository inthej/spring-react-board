import { useCallback, useState } from 'react'

const useErrorHandler = (initialState = null) => {
  const [error, setError] = useState(initialState)

  const handleError = useCallback((errorResponse) => {
    setError(errorResponse.data.error)
  }, [])

  const clearError = useCallback(() => setError(null), [])

  return { error, handleError, clearError }
}

export default useErrorHandler
