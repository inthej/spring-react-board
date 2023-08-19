import ValueUtils from '../utils/ValueUtils'
import HttpClient from './HttpClient'

const BASE_URL = 'http://localhost:8080/board'

const BoardService = {
  list: async (keyword) => {
    const url = ValueUtils.nonEmpty(keyword) ? `${BASE_URL}/list?keyword=${keyword}` : `${BASE_URL}/list`
    return await HttpClient.get(url)
  },
  get: async (id) => {
    const url = `${BASE_URL}/${id}`
    return await HttpClient.get(url)
  },
  create: async (form) => {
    const url = BASE_URL
    return await HttpClient.post(url, form)
  },
  update: async (id, form) => {
    const url = `${BASE_URL}/${id}`
    return await HttpClient.put(url, form)
  },
  delete: async (id) => {
    const url = BASE_URL
    return await HttpClient.delete(url)
  },
}

export default BoardService
