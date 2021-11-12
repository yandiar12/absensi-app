const initialState = {
  sidebarShow: true
}

export default function sidebar (state = initialState, { type, ...rest }) {
  switch (type) {
    case 'set':
      return { ...state, ...rest }
    default:
      return state
  }
}