import React, { useState } from 'react'
import './Login.css'

const Login = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleChange = (e) => {
    let input = e.target;
    if (input.name === 'username') {
      setUsername(input.value);
    } else if (input.name === 'password') {
      setPassword(input.value);
    }
  }

  const handleSubmit = (e) => {
    e.preventDefault()
    // if (validate()) {
    //   alert('form valid');
    // }
  }

  // const validate = () => {
  //   let errors = {}
  //   let isValid = true
  //   if (username) {
  //     isValid = false
  //     errors['username'] = 'Please enter your username.'
  //   }

  //   if (typeof input['username'] !== 'undefined') {
  //     isValid = false
  //     const re = /^\S*$/
  //     if (input['username'].length < 6 || !re.test(input['username'])) {
  //       isValid = false
  //       errors['username'] = 'Please add at least 6 characher'
  //     }
  //   }

  //   if (!input['password']) {
  //     isValid = false
  //     errors['password'] = 'Please enter your password'
  //   }

  //   if (typeof input['password'] !== 'undefined') {
  //     isValid = false
  //     if (input['password'].length < 6) {
  //       isValid = false
  //       errors['password'] = 'Please add at least 6 charachter.'
  //     }
  //   }

  //   return isValid
  // }

  return (
    <div className='container'>
      <div className='login-wrapper fadeInDown'>
        <h1>Please Log In</h1>
        <form onSubmit={handleSubmit}>
          <label>
            <p>Username</p>
            <input
              className='form-control'
              type='text'
              name='username'
              value={username}
              onChange={handleChange}
              placeholder='Enter Username'
              id='username'
            />
            {/* <div className='text-danger'>{state.errors.username}</div> */}
          </label>
          <label>
            <p>Password</p>
            <input
              type='password'
              name='password'
              value={password}
              onChange={handleChange}
              className='form-control'
              placeholder='Enter password'
              id='password'
            />
            {/* <div className='text-danger'>{state.errors.password}</div> */}
          </label>
          <div>
            <button id="btnSubmit" className='btn btn-outline-secondary' type='submit'>
              Submit
            </button>
          </div>
        </form>
      </div>
    </div>
  )
}

export default Login
