import {
  LOGIN_SUCCESS,
  LOGIN_FAIL,
  SET_MESSAGE,
  LOGOUT
} from '../constants/types';

import AuthService from '../services/authService';

export const login = (username, password) => (dispatch) => {
  return AuthService.login(username, password)
    .then((data) => {
      dispatch({
        type: LOGIN_SUCCESS,
        payload: {user: data}
      })

      return Promise.resolve()
    },
      (error) => {
        // const message = 
        //   (error.response &&
        //     error.response.data &&
        //     error.response.data.message) ||
        //   error.message ||
        //   error.toString()
        console.log(error.response.data);
        dispatch({
          type: LOGIN_FAIL
        })

        dispatch({
          type: SET_MESSAGE,
          payload: 'Username or password is incorrect'
        })

        return Promise.reject()
      }
    )
}

export const logout = () => (dispatch) => {
  AuthService.logout()

  dispatch({
    type: LOGOUT
  })
}