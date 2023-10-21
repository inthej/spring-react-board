import { useCallback } from 'react'

const useWindowActions = () => {
  const windowReload = useCallback(() => {
    window.location.reload()
  }, [])

  const windowConfirm = useCallback((message) => {
    return window.confirm(message)
  }, [])

  const windowAlert = useCallback((message) => {
    return window.alert(message)
  }, [])

  const scrollToTop = useCallback(() => {
    window.scrollTo(0, 0)
  }, [])

  return { windowReload, windowConfirm, windowAlert, scrollToTop }
}

export default useWindowActions
