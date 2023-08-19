import axios from 'axios'
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
  create: async (form) => await axios.post(BASE_URL, form),
  update: async (form) => await axios.post(BASE_URL, form),
  delete: async (id) => await HttpClient.delete(BASE_URL),
}

export default BoardService
