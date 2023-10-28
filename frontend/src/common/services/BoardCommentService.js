import { AppConstants } from '../index'
import HttpClient from './HttpClient'

const SERVICE_URL = `${AppConstants.BASE_URL}/board`

const BoardCommentService = {
  list: async (bno, form) => {
    const url = `${SERVICE_URL}/${bno}/comments`
    return await HttpClient.get(url, form)
  },
  get: async (bno, form) => {
    const url = `${SERVICE_URL}/${bno}/comment/${form.no}`
    return await HttpClient.get(url)
  },
  create: async (bno, form) => {
    const url = `${SERVICE_URL}/${bno}/comment`
    return await HttpClient.post(url, form)
  },
  update: async (bno, form) => {
    const url = `${SERVICE_URL}/${bno}/comment`
    return await HttpClient.put(url, form)
  },
  delete: async (bno, no) => {
    const url = `${SERVICE_URL}/${bno}/comment/${no}`
    return await HttpClient.delete(url)
  },
}

export default BoardCommentService
