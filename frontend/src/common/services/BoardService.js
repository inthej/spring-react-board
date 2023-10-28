import { AppConstants } from '../index'
import HttpClient from './HttpClient'

const SERVICE_URL = `${AppConstants.BASE_URL}/board`

const BoardService = {
  list: async (form) => {
    const url = `${SERVICE_URL}/list`
    return await HttpClient.get(url, form)
  },
  get: async (no) => {
    const url = `${SERVICE_URL}/${no}`
    return await HttpClient.get(url)
  },
  create: async (form) => {
    const url = SERVICE_URL
    return await HttpClient.post(url, form)
  },
  update: async (no, form) => {
    const url = `${SERVICE_URL}/${no}`
    return await HttpClient.put(url, form)
  },
  delete: async (no) => {
    const url = `${SERVICE_URL}/${no}`
    return await HttpClient.delete(url)
  },
}

export default BoardService
