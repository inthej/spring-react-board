import axios from 'axios'

class BoardService {
  static #instance
  #url = 'http://localhost:8080/board'

  constructor() {
    if (BoardService.#instance) {
      throw new Error('Use BoardService.getInstance()')
    }
    BoardService.#instance = this
  }

  static getInstance() {
    if (!this.#instance) {
      this.#instance = new BoardService()
    }
    return this.#instance
  }

  async get(id) {
    try {
      const path = `${this.#url}/${id}`
      const response = await axios.get(path)
      return response.data
    } catch (err) {
      throw err
    }
  }

  async create(form) {
    try {
      const path = `${this.#url}`
      const response = await axios.post(path, form)
      return response.data
    } catch (err) {
      throw err
    }
  }

  async update(form) {
    try {
      const path = `${this.#url}`
      const response = await axios.put(path, form)
      return response.data
    } catch (err) {
      throw err
    }
  }

  async delete(id) {
    try {
      const path = `${this.#url}/${id}`
      const response = await axios.delete(path)
      return response.data
    } catch (err) {
      throw err
    }
  }
}

export const boardService = BoardService.getInstance()
