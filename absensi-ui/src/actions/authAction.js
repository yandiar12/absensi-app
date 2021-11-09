import {
  LOGIN_SUCCESS,
  LOGIN_FAIL
} from '../constants/types';

import AuthService from '../services/authService';

export const login = (username, password) => (dispatch) => {
  return AuthService.login(username, password)
    .then((data) => {
      dispatch({
        type: LOGIN_SUCCESS,
        payload: {user, data}
      })

      return Promise.resolve();
    })
}