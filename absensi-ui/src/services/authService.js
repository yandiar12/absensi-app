import axios from 'axios';

const API_URL = 'https://rest-api-absensi.herokuapp.com/api/auth';

const login = (username, password) => {
  return axios.post(API_URL + '/signin', {
    username,
    password
  }).then (res => {
    console.log(res);
  })
}

export default {
  login
}