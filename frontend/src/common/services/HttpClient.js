import axios from 'axios'

const HttpClient = {
  get: async (url, params = {}) => {
    try {
      const response = await axios.get(url, { params })
      return response.data
    } catch (e) {
      throw e
    }
  },
  post: async (url, data) => {
    try {
      const response = await axios.post(url, data)
      return response.data
    } catch (e) {
      throw e
    }
  },
  put: async (url, data) => {
    try {
      const response = await axios.put(url, data)
      return response.data
    } catch (e) {
      throw e
    }
  },
  delete: async (url) => {
    try {
      const response = await axios.delete(url)
      return response.data
    } catch (e) {
      throw e
    }
  },
}

export default HttpClient
