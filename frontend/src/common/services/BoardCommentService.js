import { AppConstants } from '../index'
import HttpClient from './HttpClient'

const SERVICE_URL = `${AppConstants.BASE_URL}/board`

const BoardCommentService = {
  list: async (id) => {
    const url = `${SERVICE_URL}/${id}/comments`
    return await HttpClient.get(url)
  },
  get: async (id, form) => {
    const url = `${SERVICE_URL}/${id}/comment/${form.id}`
    return await HttpClient.get(url)
  },
  create: async (id, form) => {
    const url = `${SERVICE_URL}/${id}/comment`
    return await HttpClient.post(url, form)
  },
  update: async (id, form) => {
    const url = `${SERVICE_URL}/${id}/comment`
    return await HttpClient.put(url, form)
  },
  delete: async (id, cid) => {
    const url = `${SERVICE_URL}/${id}/comment/${cid}`
    return await HttpClient.delete(url)
  },
}

export default BoardCommentService
