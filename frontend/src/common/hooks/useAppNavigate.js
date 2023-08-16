import { useNavigate } from 'react-router-dom'

const useAppNavigate = () => {
  const navigate = useNavigate()

  const navigateBack = () => {
    navigate(-1)
  }

  const navigateTo = (path) => {
    navigate(path)
  }

  return { navigateBack, navigateTo }
}

export default useAppNavigate
