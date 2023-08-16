export default class PromiseUtils {
  static wait(milliSeconds = 1_000) {
    return new Promise((resolve) => {
      setTimeout(() => {
        resolve()
      }, milliSeconds)
    })
  }
}
