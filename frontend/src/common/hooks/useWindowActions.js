import { useCallback } from 'react'

const useWindowActions = () => {
  const windowReload = useCallback(() => {
    window.location.reload()
  }, [])

  const windowConfirm = useCallback((message) => {
    return window.confirm(message)
  }, [])

  const scrollToTop = useCallback(() => {
    window.scrollTo(0, 0)
  }, [])

  return { windowReload, windowConfirm, scrollToTop }
}

export default useWindowActions
