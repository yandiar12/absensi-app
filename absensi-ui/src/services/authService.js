import axios from 'axios';

const API_URL = 'https://rest-api-absensi.herokuapp.com/api/auth';

const login = (username, password) => {
  return axios.post(API_URL + '/signin', {
    username,
    password
  }).then (res => {
    if (res.data.token) {
      localStorage.setItem('user', JSON.stringify(res.data));
    }

    return res.data;
  })
}

const logout = () => {
  localStorage.removeItem('user');
}

export default {
  login,
  logout
}