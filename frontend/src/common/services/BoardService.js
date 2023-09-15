import { AppConstants } from '../index'
import HttpClient from './HttpClient'

const SERVICE_URL = `${AppConstants.BASE_URL}/board`

const BoardService = {
  list: async (form) => {
    const url = `${SERVICE_URL}/list?page=${form.page}&size=${form.size}&keyword=${form.keyword}`
    return await HttpClient.get(url)
  },
  get: async (id) => {
    const url = `${SERVICE_URL}/${id}`
    return await HttpClient.get(url)
  },
  create: async (form) => {
    const url = SERVICE_URL
    return await HttpClient.post(url, form)
  },
  update: async (id, form) => {
    const url = `${SERVICE_URL}/${id}`
    return await HttpClient.put(url, form)
  },
  delete: async (id) => {
    const url = `${SERVICE_URL}/${id}`
    return await HttpClient.delete(url)
  },
}

export default BoardService
