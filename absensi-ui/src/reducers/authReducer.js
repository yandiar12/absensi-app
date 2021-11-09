
const user = {}//JSON.parse(localStorage.getItem('user'))

const initialState = user
  ? { isLoggedIn: true, user }
  : { isLoggedIn: false, user: null }

export default function (state = initialState, action) {
  return state
}